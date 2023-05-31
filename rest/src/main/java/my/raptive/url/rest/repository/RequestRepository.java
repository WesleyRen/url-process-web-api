package my.raptive.url.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
//
//    @Query("SELECT u FROM UrlRequest r WHERE r.id = ?1")
//    Request findByRequestId(Long requestId);
}