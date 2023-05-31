package my.raptive.url.rest.rs;

import my.raptive.url.rest.repository.*;
import my.raptive.url.rest.shard.Sharding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UrlProcessWebServiceHandler {

	@Autowired
	RequestRepository requestRepository;
	@Autowired
	ProcessInfoRepository processInfoRepository;
	@Autowired
	UrlRepository urlRepository;

	@Autowired
	Sharding sharding;


	public List<WebContentDto> getResult(long requestId) {
		return processInfoRepository.findByRequestId(requestId);
	}


	public long saveRequest(List<String> urls) {
		Request request = new Request();

		// Store the urls in the database.
		Set<Url> urlSet = new HashSet<>();
		for (String url : urls) {
			Url urlEntity = urlRepository.findByUrl(url);
			if (urlEntity == null) {
				urlEntity = new Url();
				urlEntity.setUrl(url);
				urlEntity.setProcessStatus(ProcessStatus.RECEIVED);
				urlEntity.setShardId(sharding.getShard(url));
			}
			urlSet.add(urlEntity);
		}
		request.setUrls(urlSet);
		urlRepository.saveAll(urlSet);
		requestRepository.save(request);

		return request.getId();
	}

}