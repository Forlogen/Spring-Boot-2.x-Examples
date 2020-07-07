package dyliang.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {

    @Scheduled(cron = "0-4 * * * * MON-FRI")
    public void hello(){
        System.out.println("hello world...");
    }
}
