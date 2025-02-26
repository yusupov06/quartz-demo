package uz.md.quartzdemo.builder;

import lombok.RequiredArgsConstructor;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobTriggerBuilder {

    private final SchedulerBuilder schedulerBuilder;

    public Trigger build() throws SchedulerException {
        return TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "group1")
                .startNow()
                .withSchedule(schedulerBuilder.buildSchedulerBuilder())
                .build();
    }
}
