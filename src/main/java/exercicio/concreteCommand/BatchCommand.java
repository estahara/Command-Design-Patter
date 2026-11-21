package exercicio.concreteCommand;

import exercicio.command.Command;

import java.util.ArrayList;
import java.util.List;

public class BatchCommand implements Command {
    private final List<Command> commands;

    public BatchCommand(List<Command> commands) {
        this.commands = List.copyOf(commands);
    }

    @Override
    public boolean execute() {
        List<Command> executedCommands = new ArrayList<>();
        for (Command command : commands) {
            if (!command.execute()) {
                rollback(executedCommands);
                return false;
            }
            executedCommands.add(command);
        }
        return true;
    }

    @Override
    public void undo() {
        rollback(commands);
    }

    private void rollback(List<Command> executedCommands) {
        for (int i = executedCommands.size() - 1; i >= 0; i--) {
            executedCommands.get(i).undo();
        }
    }

    @Override
    public String getDescription() {
        return "Batch with " + commands.size() + " operations";
    }
}
