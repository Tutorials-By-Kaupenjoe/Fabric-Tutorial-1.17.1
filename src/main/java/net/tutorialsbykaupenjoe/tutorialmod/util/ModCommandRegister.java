package net.tutorialsbykaupenjoe.tutorialmod.util;

import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.tutorialsbykaupenjoe.tutorialmod.command.ReturnHomeCommand;
import net.tutorialsbykaupenjoe.tutorialmod.command.SetHomeCommand;

public class ModCommandRegister {
    public static void registerCommands() {
        CommandRegistrationCallback.EVENT.register(SetHomeCommand::register);
        CommandRegistrationCallback.EVENT.register(ReturnHomeCommand::register);
    }
}
