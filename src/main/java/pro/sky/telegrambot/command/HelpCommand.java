package pro.sky.telegrambot.command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.utils.CommandSupportUtils;

import static pro.sky.telegrambot.utils.CommandSupportUtils.chatId;

@Component
public class HelpCommand implements TelegramCommand{

    private static final String HELP = "/help";

    @Override
    public boolean support(Update update) {
        return CommandSupportUtils.isStringEqualsCommand(update, HELP);
    }

    @Override
    public SendMessage handle(Update update) {
        var text = """
                Справочная информация
                /start - начать работу
                
                /help - информация по командам
                """;
        return new SendMessage(chatId(update), text);
    }
}
