package com.example.finalstudent;

import java.io.Serializable;

public class MessageDTO   implements Serializable {

    String to;
    String content;

    @Override
    public String toString() {
        return "MessageDTO{" +
                "to='" + to + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageDTO(String to, String content) {
        this.to = to;
        this.content = content;
    }
}
