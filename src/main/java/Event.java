public class Event extends Task {
    private final String identifier = "[E]";
    private String start = null; // 何時から、何時までですか
    private String end = null;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return this.identifier + super.toString() + " (from: " + this.start + " to: " + this.end +")";
    }
}
