package info.lazycompiler.awssqssubtest.service;

import info.lazycompiler.awssqssubtest.config.SQSConfigurer;
import info.lazycompiler.awssqssubtest.subscriber.Subscriber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class SQSSubscriberService {

    private SQSConfigurer sqsConfigurer;
    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);

    public SQSSubscriberService(final SQSConfigurer sqsConfigurer){
        this.sqsConfigurer = sqsConfigurer;
    }

    @PostConstruct
    public void startSQSPolling() {
        log.info("Polling to SQS started");
        scheduledThreadPoolExecutor.scheduleAtFixedRate(new Subscriber(sqsConfigurer), 0, 2000, TimeUnit.MILLISECONDS);
    }

}
