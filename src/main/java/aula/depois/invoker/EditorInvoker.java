package depois.invoker;

import depois.command.Command;

import java.util.ArrayList;
import java.util.List;

public class EditorInvoker {
    private final List<Command> undoStack = new ArrayList<>();
    private final List<Command> redoStack = new ArrayList<>();

    public void run(Command command) {
        command.execute();
        undoStack.add(command);
        redoStack.clear();
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
        command.execute();
        undoStack.add(command);
    }
}
