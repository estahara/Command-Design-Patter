package exercicio.invoker;

import exercicio.command.Command;

import java.util.ArrayList;
import java.util.List;

public class TransactionInvoker {
    private final List<Command> undoStack = new ArrayList<>();
    private final List<Command> redoStack = new ArrayList<>();

    public boolean run(Command command) {
        if (!command.execute()) return false;
        undoStack.add(command);
        redoStack.clear();
        return true;
    }

    public void undo() {
        if (undoStack.isEmpty()) return;
        Command command = undoStack.remove(undoStack.size() - 1);
        command.undo();
        redoStack.add(command);
    }

    public void redo() {
        if (redoStack.isEmpty()) return;
        Command command = redoStack.remove(redoStack.size() - 1);
        if (command.execute()) undoStack.add(command);
    }

    public List<String> getStatement() {
        return undoStack.stream().map(Command::getDescription).toList();
    }
}
