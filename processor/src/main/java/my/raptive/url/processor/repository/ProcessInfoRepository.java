package my.raptive.url.processor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface ProcessInfoRepository extends JpaRepository<ProcessInfo, Long> {

        @Query("SELECT p FROM ProcessInfo p join Url u WHERE u.id = ?1")
        ProcessInfo findByUrlId(long urlId);
}