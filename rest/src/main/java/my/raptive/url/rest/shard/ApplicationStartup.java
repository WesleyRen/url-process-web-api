package my.raptive.url.rest.shard;

import my.raptive.url.rest.repository.ShardCount;
import my.raptive.url.rest.repository.ShardCountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

  @Autowired
  private Sharding sharding;
  @Autowired
  private ShardCountRepository shardCountRepository;

  /**
   * This event is executed as late as conceivably possible to indicate that 
   * the application is ready to service requests.
   */
  @Override
  public void onApplicationEvent(final ApplicationReadyEvent event) {

    ShardCount shardCount = new ShardCount();
    shardCount.setShardCount(sharding.getShardCount());
    shardCountRepository.save(shardCount);

    return;
  }
}
