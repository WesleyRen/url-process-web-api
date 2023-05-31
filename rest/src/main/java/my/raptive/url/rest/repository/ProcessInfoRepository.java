package my.raptive.url.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface ProcessInfoRepository extends JpaRepository<ProcessInfo, Long> {

//        @Query("SELECT u FROM UrlProcessInfo u WHERE u.url_list_id = ?1")
//        List<ProcessInfo> findByProcessId(String url);
}