package pro.sky.telegrambot.command;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.utils.CommandSupportUtils;

import java.util.Optional;

import static pro.sky.telegrambot.utils.CommandSupportUtils.chatId;

@Component
public class StartCommand implements TelegramCommand{

    private static final String START = "/start";

    @Override
    public boolean support(Update update) {
        return CommandSupportUtils.isStringEqualsCommand(update, START);
    }

    @Override
    public SendMessage handle(Update update) {
        String notificationTask = update.message().chat().username();
        String text = """
                Привет. Это мой бот, %s!

                Я только учусь писать на Java.

                Давай выберем команду:
                /start - активировать бота
                /help - справка
                """;
        String format = String.format(text, notificationTask);
        return new SendMessage(chatId(update), format);
    }


}
