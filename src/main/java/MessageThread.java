import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Zacht on 6/25/2017.
 */
public class MessageThread implements Runnable {

    List<PhoneNumber> numbers;
    PhoneNumber twilioNumber;
    int duration;

    public MessageThread(List<PhoneNumber> numbers, PhoneNumber twilioNumber, int duration) {
        this.numbers = numbers;
        this.twilioNumber = twilioNumber;
        this.duration = duration;
    }

    public void run() {

        // Create Timer to send a message every X seconds
        Timer timer = new Timer( );
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                sendMessages(numbers, twilioNumber);
            }
        }, 0,duration*1000);

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
