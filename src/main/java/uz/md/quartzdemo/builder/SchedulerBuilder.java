package uz.md.quartzdemo.builder;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;
import uz.md.quartzdemo.domain.Setting;
import uz.md.quartzdemo.repository.SettingsRepository;

import java.util.Optional;

@Service
public class SchedulerBuilder {

    private final SettingsRepository settingsRepository;

    public SchedulerBuilder(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    public CronScheduleBuilder buildSchedulerBuilder() {
        Optional<Setting> settingByKey = settingsRepository.getSettingByKey("");
        return CronScheduleBuilder.cronSchedule(settingByKey.get().getValue());
    }

    public Scheduler buildScheduler() throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        return schedulerFactory.getScheduler();
    }
}
