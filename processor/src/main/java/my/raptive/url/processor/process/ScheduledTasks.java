package my.raptive.url.processor.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks {


	@Autowired
	WebContentProcessService webContentProcessService;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"MM/dd/yyyy HH:mm:ss");

	@Scheduled(fixedRate = 5000)
	public void performTask() {

		System.out.println("Regular task performed at "
				+ dateFormat.format(new Date()));

	}

	@Scheduled(fixedRate = 5000)
	public void process() {
		//TODO use shard info to determine the shards to process.
		for (int i = 0; i < 10; i++) {
			webContentProcessService.process(i);
		}
	}

}
