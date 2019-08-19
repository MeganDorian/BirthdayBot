package phoenix.telegram;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import phoenix.entities.Birthday;
import phoenix.services.BirthdayService;

@Component
public class HappyBirthdayBot extends TelegramLongPollingBot {
    private BirthdayService birthService;
    private static long myChatId=431874820;
    private SendMessage sendMessage;
    private MessageConcat messageConcat;

    public HappyBirthdayBot(BirthdayService birthdayService, MessageConcat msg) {
        birthService=birthdayService;
        sendMessage = new SendMessage();
        messageConcat = msg;
    }

    @Override
    public void onUpdateReceived(Update update) {
        sendMessage.setChatId(myChatId);
        if (update.hasMessage() && update.getMessage().getText().equals("/start"))
            sendMessage.setText("Welcome");
        else if(update.hasMessage() && update.getMessage().hasText()) {
            try {
                Birthday messageText = birthService.selectById((Integer.parseInt(update.getMessage().getText())));
                sendMessage.setText(messageConcat.birthdayAndTemplateConcat(messageText.toString()));

            } catch (NumberFormatException e) {
                sendMessage.setText("Incorrect input");
            } catch (IllegalArgumentException e) {
                sendMessage.setText("No templates found");
            } catch (NullPointerException e ) {
                sendMessage.setText("Row not found");
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "phoenixBirthdaysBot";
    }

    @Override
    public String getBotToken() {
        return "818079284:AAGUQ1a7mCm07GUHGc2WJXbBt9ydrij9KZg";
    }

    public void birthdayNotification(String msg) {
        sendMessage.setChatId(myChatId);
        sendMessage.setText(msg);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e1) {
            e1.printStackTrace();
        }
    }
}
