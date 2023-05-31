package my.raptive.url.rest.rs;

import jakarta.persistence.EntityManager;
import my.raptive.url.rest.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class UrlProcessWebServiceHandler {

	@Autowired
	RequestRepository urlRequestRepository;
	@Autowired
	ProcessInfoRepository urlProcessInfoRepository;
	@Autowired
	UrlRepository urlListRepository;
	@Autowired
	EntityManager entityManager;


	public List<WebContentDto> getResult(String processId) {
//		return urlProcessInfoRepository.findByProcessId(processId).stream()
//				.map(urlProcessInfo -> new WebContentDto(urlProcessInfo.getUrl(), urlProcessInfo.getTitle(), urlProcessInfo.getDescription(), urlProcessInfo.getBody(), urlProcessInfo.getErrorMessage(), urlProcessInfo.getProcessStatus()))
//				.toList();
		return Collections.emptyList();
	}


	public long saveRequest(List<String> urls) {
//		String processId = UUID.randomUUID().toString();
//		// Store the urls in the database.
//		List<UrlProcessInfo> urlProcessEntities = new ArrayList<>();
//		for (String url : urls) {
//			UrlProcessInfo urlProcessEntity = new UrlProcessInfo();
//			urlProcessEntity.setProcessId(processId);
//			urlProcessEntity.setUrl(url);
//			urlProcessEntity.setProcessStatus(ProcessStatus.IN_PROGRESS);
//			urlProcessEntities.add(urlProcessEntity);
//		}
//		urlProcessInfoRepository.saveAll(urlProcessEntities);
//		return processId;
//		long requestId = entityManager.persist(new UrlRequest()).getId();
//		UrlRequest urlRequest = urlListRepository.save(urls);

		return 0L;
	}

}