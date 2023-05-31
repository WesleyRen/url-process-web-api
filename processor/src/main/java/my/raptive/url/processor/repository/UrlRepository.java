package my.raptive.url.processor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

    @Query("SELECT u FROM Url u WHERE u.url = :url")
    Url findByUrl(@Param("url") String url);

    @Query("SELECT u FROM Url u WHERE u.processStatus <> 1 and u.processStatus <> 4 AND u.shardId = :shardId")
    List<Url> findByStatusAndShardId(@Param("shardId") int shardId);
}