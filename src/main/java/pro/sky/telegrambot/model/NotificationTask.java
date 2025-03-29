package pro.sky.telegrambot.model;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name ="notification_task")
public class NotificationTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "chat_id")
    private String chatId;

    @Column(name = "notification")
    private String notification;

    @Column(name = "data_time")
    private LocalDateTime localDateTime;

    public NotificationTask() {
    }

    public NotificationTask(String chatId, String notification, LocalDateTime localDateTime) {
        this.chatId = chatId;
        this.notification = notification;
        this.localDateTime = localDateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationTask that = (NotificationTask) o;
        return id == that.id && Objects.equals(chatId, that.chatId) && Objects.equals(notification, that.notification) && Objects.equals(localDateTime, that.localDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, notification, localDateTime);
    }

    @Override
    public String toString() {
        return "NotificationTask{" +
                "id=" + id +
                ", chatId='" + chatId + '\'' +
                ", notification='" + notification + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }
}