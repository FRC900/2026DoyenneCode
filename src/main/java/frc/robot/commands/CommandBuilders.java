package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class CommandBuilders extends Command{

    public static Command createShootCommand(
        Command flywheelCommand, 
        Command feederCommand, 
        Command spindexerCommand, 
        Trigger spindexerStart) {
        return Commands.parallel(
            flywheelCommand,
            feederCommand,
            Commands.waitUntil(spindexerStart)
                    .andThen(spindexerCommand)
        );
    }
    
}