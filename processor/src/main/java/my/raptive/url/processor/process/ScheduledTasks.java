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
		webContentProcessService.process("bca27eac-979a-40d8-acff-2e6961f9dde3");
	}

}
