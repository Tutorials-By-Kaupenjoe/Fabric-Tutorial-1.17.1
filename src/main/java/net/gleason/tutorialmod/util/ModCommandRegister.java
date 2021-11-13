package net.gleason.tutorialmod.util;

import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.gleason.tutorialmod.command.ReturnHomeCommand;
import net.gleason.tutorialmod.command.SetHomeCommand;

public class ModCommandRegister {
    public static void registerCommands() {
        CommandRegistrationCallback.EVENT.register(SetHomeCommand::register);
        CommandRegistrationCallback.EVENT.register(ReturnHomeCommand::register);
    }
}
