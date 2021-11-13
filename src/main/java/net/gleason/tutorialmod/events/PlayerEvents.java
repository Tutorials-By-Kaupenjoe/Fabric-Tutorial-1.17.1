package net.gleason.tutorialmod.events;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.gleason.tutorialmod.util.IEntityDataSaver;

public class PlayerEvents implements ServerPlayerEvents.CopyFrom {
    @Override
    public void copyFromPlayer(ServerPlayerEntity oldPlayer, ServerPlayerEntity newPlayer, boolean alive) {
        IEntityDataSaver original = ((IEntityDataSaver) oldPlayer);
        IEntityDataSaver player = ((IEntityDataSaver) newPlayer);

        player.getPersistentData().putIntArray("homepos", original.getPersistentData().getIntArray("homepos"));
    }
}
