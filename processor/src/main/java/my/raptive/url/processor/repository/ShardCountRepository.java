package my.raptive.url.processor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface ShardCountRepository extends JpaRepository<ShardCount, Integer> {
}