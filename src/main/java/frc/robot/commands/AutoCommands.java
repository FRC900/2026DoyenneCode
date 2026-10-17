package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.intake.Intake;

public class AutoCommands extends Command {
    public static Command shootAuto(Command shootCommand, double time) {
        return shootCommand.withTimeout(time);
    }

    public static Command extendIntakeAuto(Intake intake) {
        return Commands.runOnce(intake::extendIntake, intake)
            .andThen(Commands.idle(intake))
            .withTimeout(0.2)
            .finallyDo((interrupted) -> intake.stopIntake());
    }
}
