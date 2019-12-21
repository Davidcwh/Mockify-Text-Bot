import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import parser.Parser;

public class MockifyTextBot extends TelegramLongPollingBot {
    private Parser parser = new Parser();

    public void onUpdateReceived(Update update) {
        if (parser.updateHasParsableText(update)) {
            SendMessage message = parser.parseInput(update);
            try {
                execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public String getBotUsername() {
        return "MockifyTextBot";
    }

    public String getBotToken() {
        return System.getenv("TOKEN");
    }
}
