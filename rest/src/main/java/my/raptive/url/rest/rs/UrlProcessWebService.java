package my.raptive.url.rest.rs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

	@PostMapping(
			value = "",
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Long saveRequest(@RequestBody List<String> urls) {
		return urlProcessWebServiceHandler.saveRequest(urls);
	}

}