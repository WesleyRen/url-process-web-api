package my.raptive.url.rest.shard;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Sharding {

        @Value("${shard.count:10}")
        private int SHARD_COUNT;

        public int getShard(String url) {
            int hash = url.hashCode();
            return Math.abs(hash % SHARD_COUNT);
        }

        public int getShardCount() {
            return SHARD_COUNT;
        }
}
