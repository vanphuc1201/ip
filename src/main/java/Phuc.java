import java.util.Scanner;

public class Phuc {
    private final ProcessInput pi;

    public Phuc() {
        this.pi = new ProcessInput();
    }

    public void run() {
        pi.start();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            pi.process(input);
            input = sc.nextLine();
        }

        pi.end();
        sc.close();
    }
}
