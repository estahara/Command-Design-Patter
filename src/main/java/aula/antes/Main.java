package antes;

public class Main {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();

        editor.handle("INSERT", 0, "Hello");
        editor.handle("INSERT", 5, " World");
        editor.handle("DELETE", 0, "6");
        System.out.println(editor.getContent());

        editor.handle("UNDO", 0, null);
        System.out.println(editor.getContent());
    }
}
