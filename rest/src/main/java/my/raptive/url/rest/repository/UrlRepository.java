package my.raptive.url.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

//    @Query("SELECT u FROM UrlList u WHERE u.url = ?1")
//    Url findByUrl(String url);
}