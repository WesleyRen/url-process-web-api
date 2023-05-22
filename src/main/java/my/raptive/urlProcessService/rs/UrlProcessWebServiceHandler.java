package my.raptive.urlProcessService.rs;

import my.raptive.urlProcessService.ProcessStatus;
import my.raptive.urlProcessService.process.WebContentProcessService;
import my.raptive.urlProcessService.repository.UrlProcessInfo;
import my.raptive.urlProcessService.repository.UrlProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class UrlProcessWebServiceHandler {

	UrlProcessRepository urlProcessRepository;
	WebContentProcessService webContentProcessService;

	@Autowired
	UrlProcessWebServiceHandler(UrlProcessRepository urlProcessRepository, WebContentProcessService webContentProcessService) {
		this.urlProcessRepository = urlProcessRepository;
		this.webContentProcessService = webContentProcessService;
	}

	public List<WebContentDto> getResult(String processId) {
		return urlProcessRepository.findByProcessId(processId).stream()
				.map(urlProcessInfo -> new WebContentDto(urlProcessInfo.getUrl(), urlProcessInfo.getTitle(), urlProcessInfo.getDescription(), urlProcessInfo.getBody(), urlProcessInfo.getErrorMessage(), urlProcessInfo.getProcessStatus()))
				.toList();
	}


	public String processUrl(List<String> urls) {
		String processId = storeUrls(urls);
		// Async call to process the urls.
		webContentProcessService.process(processId);
		return processId;
	}

	private String storeUrls(List<String> urls) {
		String processId = UUID.randomUUID().toString();
		// Store the urls in the database.
		List<UrlProcessInfo> urlProcessEntities = new ArrayList<>();
		for (String url : urls) {
			UrlProcessInfo urlProcessEntity = new UrlProcessInfo();
			urlProcessEntity.setProcessId(processId);
			urlProcessEntity.setUrl(url);
			urlProcessEntity.setProcessStatus(ProcessStatus.IN_PROGRESS);
			urlProcessEntities.add(urlProcessEntity);
		}
		urlProcessRepository.saveAll(urlProcessEntities);
		return processId;
	}

}