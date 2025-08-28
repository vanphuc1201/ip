public class EventTask extends Task {
    private final String startdate;
    private final String enddate;

    public EventTask(String description, String startdate, String enddate) {
        super(description);
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public EventTask(String description, String startdate, String enddate, boolean isDone) {
        super(description, isDone);
        this.startdate = startdate;
        this.enddate = enddate;
    }

    @Override
    public String writeToFile() {
        return "E | " + isDone + " | " + description + " | " + startdate + " | " + enddate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startdate + " to: " + enddate + ")";
    }
}
