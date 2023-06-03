package my.raptive.url.rest.rs;

import my.raptive.url.common.repository.ProcessStatus;
import my.raptive.url.common.rs.WebContentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/url/process")
public class UrlProcessWebService {

	UrlProcessWebServiceHandler urlProcessWebServiceHandler;
	@Autowired
	UrlProcessWebService(UrlProcessWebServiceHandler urlProcessWebServiceHandler) {
		this.urlProcessWebServiceHandler = urlProcessWebServiceHandler;
	}

	@GetMapping("")
	public List<WebContentDto> getResult(@RequestParam("requestId") long requestId) {
		return urlProcessWebServiceHandler.getResult(requestId);
	}

	@GetMapping("/status")
	public Map<String, ProcessStatus> getStatus(@RequestParam("requestId") long requestId) {
		return urlProcessWebServiceHandler.getStatus(requestId);
	}

	@PostMapping(
			value = "",
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Long saveRequest(@RequestBody List<String> urls) {
		return urlProcessWebServiceHandler.saveRequest(urls);
	}

}