package multithreading.messeger;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Message implements Serializable,AutoCloseable {
    private String clientName;
    private String text;
    private final int id;
    private LocalDateTime localDateTime;
    public Message(String clientName, String text,int id) {
        this.clientName = clientName;
        this.text = text;
        this.id = id;
        localDateTime=LocalDateTime.now();

    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return  clientName +": \""+ text+"\" [Отправленно в " +  localDateTime.getHour()+":"+localDateTime.getMinute()+":"+localDateTime.getSecond()+"]";//// TODO: 14.12.2019 исправить отображение времени (меньше 10 минут)
    }

    public String getClient() {
        return clientName;
    }

    public void setClient(String clientName) {
        this.clientName = clientName;
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
    public void close() throws Exception {

    }
}
