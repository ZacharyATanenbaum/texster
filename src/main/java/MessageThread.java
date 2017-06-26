import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;

/**
 * Created by Zacht on 6/25/2017.
 */
public class MessageThread implements Runnable {

    List<PhoneNumber> numbers;
    PhoneNumber twilioNumber;

    public MessageThread(List<PhoneNumber> numbers, PhoneNumber twilioNumber) {
        this.numbers = numbers;
        this.twilioNumber = twilioNumber;
    }

    public void run() {

        // TODO: Finish
        // Create Timer to send message on the hour
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND, 0);
 //       Timer timer = new Timer();
 //       timer.schedule(sendMessages(numbers, twilioNumber), );

    }

    /**
     * Send a message to each number in the given list from the provided twilio number.
     * @param numbersList a list of numbers to send the test message
     * @param twilioNumber the number to send the test message from, must be a twilio number
     */
    private static void sendMessages(List<PhoneNumber> numbersList, PhoneNumber twilioNumber) {
        // Set Timezone to EST
        ZoneId zoneId = ZoneId.of("Canada/Eastern");

        for (PhoneNumber destinationNumber: numbersList) {
            Message message = Message
                    .creator( destinationNumber, twilioNumber,
                            "Test at: "+ ZonedDateTime.now(zoneId).toLocalTime() + " EST")
                    .create();
        }
    }


}
