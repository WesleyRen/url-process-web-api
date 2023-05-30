package my.raptive.url.processor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface UrlProcessRepository extends JpaRepository<UrlProcessInfo, Integer> {

    @Query("SELECT u FROM UrlProcessInfo u WHERE u.processId = ?1")
    List<UrlProcessInfo> findByProcessId(String processId);

}