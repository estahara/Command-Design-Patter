package depois.concreteCommand;

import depois.command.Command;
import depois.receiver.TextDocument;

public class DeleteTextCommand implements Command {

    private final TextDocument document;
    private final int position;
    private final int length;
    private String removedText;

    public DeleteTextCommand(TextDocument document, int position, int length) {
        this.document = document;
        this.position = position;
        this.length = length;
    }
    @Override
    public void execute() {
        removedText = document.delete(position, length);
    }

    @Override
    public void undo() {
        document.insert(position, removedText);
    }
}
