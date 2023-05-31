package my.raptive.url.processor.process;

import my.raptive.url.processor.repository.ShardCount;
import my.raptive.url.processor.repository.ShardCountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTasks {

	private int shardCount = 0;

	@Autowired
	ShardCountRepository shardCountRepository;

	@Autowired
	WebContentProcessService webContentProcessService;

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"MM/dd/yyyy HH:mm:ss");

	@Scheduled(fixedRate = 5000)
	public void process() {
		if (shardCount == 0) {
			List<ShardCount> shardCountList = shardCountRepository.findAll();
			if (shardCountList.size() > 0) {
				shardCount = shardCountList.get(0).getShardCount();
			}
		}
		for (int i = 0; i < shardCount; i++) {
			System.out.printf("Processing shard %d at %s\n", i, dateFormat.format(new Date()));
			webContentProcessService.process(i);
		}
	}

}
