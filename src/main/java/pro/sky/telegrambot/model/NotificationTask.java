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
    @GeneratedValue
    private long id;

    @Column(name = "chat_id")
    private String chatId;

    private String text;

    @Column (name = "data_time")
    private LocalDateTime localDateTime;

    public NotificationTask() {
    }

    public NotificationTask(String text, LocalDateTime localDateTime) {
        this.text = text;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
        return id == that.id && Objects.equals(chatId, that.chatId) && Objects.equals(text, that.text) && Objects.equals(localDateTime, that.localDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, text, localDateTime);
    }

    @Override
    public String toString() {
        return "NotificationTask{" +
                "id=" + id +
                ", chatId='" + chatId + '\'' +
                ", text='" + text + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
