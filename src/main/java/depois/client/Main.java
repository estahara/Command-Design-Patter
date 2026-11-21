package depois.client;

import depois.concreteCommand.DeleteTextCommand;
import depois.concreteCommand.InsertTextCommand;
import depois.invoker.EditorInvoker;
import depois.receiver.TextDocument;

public class Main {
    public static void main(String[] args) {
        TextDocument document = new TextDocument();
        EditorInvoker invoker = new EditorInvoker();

        invoker.run(new InsertTextCommand(document, 0, "Hello"));
        invoker.run(new InsertTextCommand(document, 5, " World"));
        invoker.run(new DeleteTextCommand(document, 0, 6));
        System.out.println(document.getContent());

        invoker.undo();
        System.out.println(document.getContent());

        invoker.redo();
        System.out.println(document.getContent());
    }
}
