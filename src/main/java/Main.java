import java.util.Scanner;

/**
 * Created by Zacht on 6/25/2017.
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("Hello World");





        // Start Scanning for Commands
        Scanner scanner = new Scanner(System.in);
        System.out.println("Accepting Commands, type \"help\" for help:");
        String command = "";
        String response = "";

        // Scan Line
        command = scanner.nextLine();

        // Keep scanning for new commands until quit
        while (!command.equals("quit")) {

            // Check if Command
            switch (command) {
                case "help":
                    response = "Command List: \n" +
                            "help: Show list of commands \n" +
                            "quit: Stop and close server \n";
                    break;

                default:
                    response = command+" is not a suitable command, type \"help\" for help";

            }

            // Show response to command
            System.out.println(response);

            // Scan Line
            command = scanner.nextLine();

        }

        // Closing Server
        System.out.println("Closing down server.");
        scanner.close();

    }


}
