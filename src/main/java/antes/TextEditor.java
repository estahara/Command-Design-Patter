package antes;

import java.util.ArrayList;
import java.util.List;

public class TextEditor {

    private final StringBuilder content = new StringBuilder();
    private final List<String[]> history = new ArrayList<>();

    public void handle(String action, int position, String value) {
        switch (action) {
            case "INSERT" -> {
                content.insert(position, value);
                history.add(new String[]{"INSERT", String.valueOf(position), value});
            }

            case "DELETE" -> {
                int length = Integer.parseInt(value);
                String removed = content.substring(position, position + length);
                content.delete(position, position + length);
                history.add(new String[]{"DELETE", String.valueOf(position), removed});
            }

            case "UNDO" -> undoLast();

            default -> throw new IllegalArgumentException("Unknown action: " + action);
        }
    }

    private void undoLast() {
        if (history.isEmpty()) return;

        String[] last = history.remove(history.size() - 1);
        int position = Integer.parseInt(last[1]);

        switch (last[0]) {
            case "INSERT" -> content.delete(position, position + last[2].length());

            case "DELETE" -> content.insert(position, last[2]);
        }
    }

    public String getContent() {
        return content.toString();
    }
}
