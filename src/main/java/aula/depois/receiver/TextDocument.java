package depois.receiver;

public class TextDocument {
    private final StringBuilder content = new StringBuilder();

    public void insert(int position, String text) {
        content.insert(position, text);
    }

    public String delete(int position, int length) {
        String removed = content.substring(position, position + length);
        content.delete(position, position + length);
        return removed;
    }

    public String getContent() { return content.toString(); }
}
