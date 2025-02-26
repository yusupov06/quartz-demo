package uz.md.quartzdemo.repository;

import org.springframework.stereotype.Service;
import uz.md.quartzdemo.domain.Setting;

import java.util.Optional;

@Service
public class SettingsRepository {

    public Optional<Setting> getSettingByKey(String key) {
        Setting setting = new Setting();
        setting.setId(1L);
        setting.setName("test");
        setting.setValue("0/15 * * * * ?");
        return Optional.of(setting);
    }

}
