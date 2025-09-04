package phuc;

import java.util.Scanner;

import phuc.exception.PhucException;
import phuc.gui.OutputCapture;
import phuc.parser.ProcessInput;
import phuc.ui.UserInterface;

/**
 * Main application class for Phuc task manager.
 * Handles the main application loop and user input processing.
 */
public class Phuc {
    private final ProcessInput pi;
    private Boolean haveLoadData = false;

    /**
     * Constructs a new Phuc application instance.
     * Initializes the input processor.
     */
    public Phuc() {
        this.pi = new ProcessInput();
    }

    /**
     * Starts the main application loop.
     * Loads saved tasks, processes user input, and handles open, shutdown.
     *
     * @throws PhucException if any error occurs during application execution
     */
    public void run() throws PhucException {
        pi.start();
        pi.load();

        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                String input = sc.nextLine();
                if (input.equals("bye")) {
                    pi.end();
                    break;
                }
                pi.process(input);
            } catch (PhucException e) {
                UserInterface.showError(e.getMessage());
            }
        }
    }

    /**
     * Processes user input and returns the chatbot's response by capturing console output.
     * Handles both normal commands and the exit command ("bye"). Errors are captured and
     * formatted using the standard error display format.
     *
     * @param input the user input command to process
     * @return the formatted response string captured from console output
     * @throws PhucException for many
     */
    public String getResponse(String input) throws PhucException {
        if (!haveLoadData) {
            pi.load();
            haveLoadData = true;
        }
        return OutputCapture.captureOutput(() -> {
            try {
                if (input.equals("bye")) {
                    pi.end();
                } else {
                    pi.process(input);
                }
            } catch (PhucException e) {
                UserInterface.showError(e.getMessage());
            }
        });
    }
}
