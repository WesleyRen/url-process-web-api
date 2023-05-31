package my.raptive.url.processor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface ProcessInfoRepository extends JpaRepository<ProcessInfo, Long> {

        @Query("SELECT p FROM ProcessInfo p join Url u on p.urlId = u.id WHERE u.id = :urlId")
        ProcessInfo findByUrlId(@Param("urlId") long urlId);
}