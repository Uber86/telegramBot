package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private static final String START = "/start";
    private static final String HELP = "/help";

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;


    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            if(update.message() == null|| update.message().text() == null ){
                return;
            }
            String textMessage = update.message().text();
            Long chatId = update.message().chat().id();
            switch (textMessage) {
                case START ->{
                    String user = update.message().chat().username();
                    comandBotStart(chatId, textMessage);
                }
                case HELP -> comandBotHelp(chatId);

            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void comandBotStart(Long chatId, String user) {
        String text = """
                Привет. Это мой бот, %s!

                Я только учусь писать на Java.

                Давай выберем команду:
                /start - активировать бота
                """;
        String format = String.format(text, user);
        sendMessage(chatId, text);
    }

    private void comandBotHelp(Long chatId){
        var text = """
                Справочная информация
                """;
        sendMessage(chatId , text);
    }

    private void sendMessage(Long chatId, String text) {
        SendMessage sendMessage = new SendMessage(String.valueOf(chatId), text);
        try {
            telegramBot.execute(sendMessage);
        } catch (RuntimeException e){
            logger.error("Ошибка отправки");

        }

    }



}
