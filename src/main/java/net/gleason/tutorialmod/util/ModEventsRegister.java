package net.gleason.tutorialmod.util;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.gleason.tutorialmod.events.PlayerEvents;

public class ModEventsRegister {
    public static void registerEvents() {
        ServerPlayerEvents.COPY_FROM.register(new PlayerEvents());
    }
}
