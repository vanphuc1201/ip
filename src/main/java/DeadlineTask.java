public class DeadlineTask extends Task {
    String deadline;

    public DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String printTask() {
        return "[D]" + super.printTask() + " (by: " + deadline + ")";
    }
}
