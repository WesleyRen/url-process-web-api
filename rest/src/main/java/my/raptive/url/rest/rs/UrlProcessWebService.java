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
	public List<WebContentDto> getResult(@RequestParam("processId") String processId) {
		return urlProcessWebServiceHandler.getResult(processId);
	}

	@PostMapping(
			value = "",
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public String save(@RequestBody List<String> urls) {
		return urlProcessWebServiceHandler.save(urls);
	}

}