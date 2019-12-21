package parser;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Parser {

    public SendMessage parseInput(Update update) {
        long chat_id = getChatId(update);
        String message_text = mockifyText(update);
        SendMessage message = new SendMessage() // Create a message object object
                .setChatId(chat_id)
                .setText(message_text);

        return message;
    }

    public boolean updateHasParsableText(Update update) {
        return update.hasMessage() && update.getMessage().hasText();
    }

    private long getChatId(Update update) {
        return update.getMessage().getChatId();
    }

    private String mockifyText(Update update) {
        String receivedText = update.getMessage().getText().toLowerCase();
        int textLength = receivedText.length();
        StringBuilder stringBuilder = new StringBuilder(textLength);
        boolean uppercase = false;

        for(int i = 0; i < textLength; i++) {
            char currentCharacter = receivedText.charAt(i);
            if (currentCharacter >= 97 && currentCharacter <= 122) {
                if (uppercase) {
                    currentCharacter -= 32;
                    uppercase = false;
                } else {
                    uppercase = true;
                }
            }
            stringBuilder.append(currentCharacter);
        }

        return stringBuilder.toString();
    }
}
