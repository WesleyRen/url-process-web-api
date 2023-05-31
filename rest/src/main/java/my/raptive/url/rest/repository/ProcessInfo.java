package my.raptive.url.rest.repository;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@Entity
public class ProcessInfo {

    @Id
    @Column(name="url_id")
    private long urlId;

    @OneToOne
    @PrimaryKeyJoinColumn(name="url_id", referencedColumnName="id")
    private Url url;

    @Column
    private String title;

    @Column(length = 4000)
    private String description;

    @Column(length = 4000)
    private String body;

    @Column(length = 1000)
    private String errorMessage;

}
