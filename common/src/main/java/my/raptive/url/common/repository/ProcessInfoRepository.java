package my.raptive.url.common.repository;

import my.raptive.url.common.rs.WebContentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface ProcessInfoRepository extends JpaRepository<ProcessInfo, Long> {

        @Query("SELECT p FROM ProcessInfo p join Url u on p.urlId = u.id WHERE u.id = :urlId")
        ProcessInfo findByUrlId(@Param("urlId") long urlId);

        @Query("SELECT new my.raptive.url.common.rs.WebContentDto(u.url, p.title, p.description, p.body, p.errorMessage, u.processStatus) " +
                "FROM ProcessInfo p join Url u on p.urlId = u.id " +
                "join Request r " +
                "WHERE r.id = :requestId")
        List<WebContentDto> findByRequestId(@Param("requestId") Long requestId);
}