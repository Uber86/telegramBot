package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repository.NotificationRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationScheduler {

    private final NotificationRepository repository;
    private final TelegramBot bot;


    public NotificationScheduler(NotificationRepository repository, TelegramBot bot) {
        this.repository = repository;
        this.bot = bot;
    }

    @Scheduled(fixedDelay = 15000)
    public void sendNotification() {
        List <NotificationTask> task = repository.findAllByLocalDateTimeBefore(LocalDateTime.now());

        task.stream().forEach(notificationTask -> {
            bot.execute(new SendMessage(notificationTask.getChatId(),notificationTask.getNotification()));
            repository.delete(notificationTask);
        });

    }
}
