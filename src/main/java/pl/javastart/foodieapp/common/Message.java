package pl.javastart.foodieapp.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {

    private String title;
    private String content;

    public Message(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
