package pro.sky.telegrambot.command;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repository.NotificationRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import static pro.sky.telegrambot.utils.CommandSupportUtils.chatId;
import static pro.sky.telegrambot.utils.CommandSupportUtils.text;

@Component
public class NotificationCommand implements TelegramCommand {


    private final Pattern pattern = Pattern.compile("(\\d{2}\\.\\d{2}\\.\\d{4}\\s\\d{2}:\\d{2})(\\s+)(.+)");
    private final NotificationRepository repository;


    public NotificationCommand(NotificationRepository repository) {
        this.repository = repository;
    }


    @Override
    public boolean support(Update update) {
        Optional<String> text = text(update);
        return text
                .map(it -> it.matches(pattern.pattern()))
                .orElse(false);
    }

    @Override
    public SendMessage handle(Update update) {
        Optional<String> text = text(update);
        if(text.isPresent()) {
            Matcher matcher = pattern.matcher(text.get());
            if(matcher.find()){
                String date = matcher.group(1);
                String item = matcher.group(3);
                NotificationTask notificationTask = new NotificationTask(
                        String.valueOf(chatId(update)),
                        item,
                        LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
                );
                repository.save(notificationTask);
                return new SendMessage(chatId(update), "Запись добавлена " + date);
            }

        }
        return new SendMessage(chatId(update), "Ошибка");
    }
}
