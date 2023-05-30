package my.raptive.url.rest.repository;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import my.raptive.url.rest.ProcessStatus;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@Entity
public class UrlProcessInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String processId;

    @Column(nullable = false)
    private ProcessStatus processStatus;

    @Column(nullable = false)
    private String url;

    @Column
    private String title;

    @Column(length = 4000)
    private String description;

    @Column(length = 4000)
    private String body;

    @Column(length = 1000)
    private String errorMessage;

}
