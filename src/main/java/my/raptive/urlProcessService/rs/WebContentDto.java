package my.raptive.urlProcessService.rs;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import my.raptive.urlProcessService.ProcessStatus;

@Getter
@Setter
@Accessors(chain = true)
public class WebContentDto {
    String url;
    ProcessStatus status;
    String title;
    String description;
    String body;
    String errorMessage;

    public WebContentDto(String url, String title, String description, String body, String errorMessage, ProcessStatus processStatus) {
        this.url = url;
        this.title = title;
        this.description = description;
        this.body = body;
        this.errorMessage = errorMessage;
        this.status = processStatus;
    }
}
