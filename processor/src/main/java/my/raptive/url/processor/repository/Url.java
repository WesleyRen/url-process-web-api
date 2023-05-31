package my.raptive.url.processor.repository;

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
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private long id;

    @Column(nullable = false, unique = true)
    private String url;

    @Column
    private int shardId;

    @Column(nullable = false)
    private ProcessStatus processStatus;
}
