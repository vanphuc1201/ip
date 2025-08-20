public class Phuc {
    private final Userinterface Ui;

    public Phuc() {
        this.Ui = new Userinterface();
    }

    public void run() {
        Ui.sayHello();
        Ui.sayGoodbye();
    }
}
