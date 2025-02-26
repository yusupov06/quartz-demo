package uz.md.quartzdemo;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uz.md.quartzdemo.builder.JobTriggerBuilder;
import uz.md.quartzdemo.builder.SchedulerBuilder;
import uz.md.quartzdemo.job.SimpleJob;

@SpringBootApplication
public class QuartzDemoApplication implements ApplicationRunner {

    private final SchedulerBuilder schedulerBuilder;
    private final JobTriggerBuilder jobTriggerBuilder;

    public QuartzDemoApplication(SchedulerBuilder schedulerBuilder, JobTriggerBuilder jobTriggerBuilder) {
        this.schedulerBuilder = schedulerBuilder;
        this.jobTriggerBuilder = jobTriggerBuilder;
    }

    public static void main(String[] args) {
        SpringApplication.run(QuartzDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Scheduler scheduler = schedulerBuilder.buildScheduler();

        JobDetail job = JobBuilder.newJob(SimpleJob.class)
                .withIdentity("myJob", "group1")
                .usingJobData("jobSays", "Hello World!")
                .usingJobData("myFloatValue", 3.141f)
                .build();

        Trigger trigger = jobTriggerBuilder.build();

        scheduler.scheduleJob(job, trigger);
        scheduler.start();
    }
}
