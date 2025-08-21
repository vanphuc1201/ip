import java.util.Scanner;

public class Phuc {
    private final Processinput pi;

    public Phuc() {
        this.pi = new Processinput();
    }

    public void run() {
        pi.start();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            pi.Process(input);
            input = sc.nextLine();
        }

        pi.end();
        sc.close();
    }
}
