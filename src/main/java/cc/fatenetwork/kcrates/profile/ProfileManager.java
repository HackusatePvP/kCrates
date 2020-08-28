package cc.fatenetwork.kcrates.profile;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class ProfileManager {
    private Map<UUID, Profile> profiles = new HashMap<>();

    public Profile getProfile(UUID uuid) {
        return profiles.get(uuid);
    }
}
