import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Using Twilio this program sends an automated test text message on every hour (12:00, 1:00, etc). The info file
 * should be structures to the below, starting at the first line:
 *
 * Twilio Account ID
 * Twilio Auth Token
 * Twilio Number (No dashes, parenthesis or anything)
 * Destination # (Any number of destination numbers)
 *
 */


public class Main {

    public static void main(String[] args) throws URISyntaxException {

        final String path = "info";
        List<String> lines;

        // Read info file into lines list.
        try {
            lines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);

        } catch (IOException e) {
            throw new RuntimeException("Error! Unable to read from info file.");
        }

        // Get Twilio info from lines list
        final String twilioAccountSid = lines.get(0);
        lines.remove(0);
        final String twilioAuthToken = lines.get(0);
        lines.remove(0);
        final PhoneNumber twilioNumber = new PhoneNumber(lines.get(0));
        lines.remove(0);

        // Create destination numbers list
        ArrayList<PhoneNumber> destinationNumbers = new ArrayList<>();
        for (String destination: lines) {
            destinationNumbers.add(new PhoneNumber(destination));
        }

        // Authenticate with Twilio
        Twilio.init(twilioAccountSid, twilioAuthToken);

        // Create new thread to send a message on the hour
        Runnable messageThread = new MessageThread(destinationNumbers, twilioNumber);
        new Thread(messageThread).start();

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
