package my.raptive.url.rest.rs;

import my.raptive.url.rest.ProcessStatus;
import my.raptive.url.rest.repository.UrlProcessInfo;
import my.raptive.url.rest.repository.UrlProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class UrlProcessWebServiceHandler {

	UrlProcessRepository urlProcessRepository;

	@Autowired
	UrlProcessWebServiceHandler(UrlProcessRepository urlProcessRepository) {
		this.urlProcessRepository = urlProcessRepository;
	}

	public List<WebContentDto> getResult(String processId) {
		return urlProcessRepository.findByProcessId(processId).stream()
				.map(urlProcessInfo -> new WebContentDto(urlProcessInfo.getUrl(), urlProcessInfo.getTitle(), urlProcessInfo.getDescription(), urlProcessInfo.getBody(), urlProcessInfo.getErrorMessage(), urlProcessInfo.getProcessStatus()))
				.toList();
	}


	public String save(List<String> urls) {
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