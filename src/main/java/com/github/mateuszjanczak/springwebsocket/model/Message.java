package com.github.mateuszjanczak.springwebsocket.model;

import lombok.Data;

import static org.apache.commons.lang.StringEscapeUtils.escapeHtml;

@Data
public class Message {
    String username;
    String content;

    public static Message Bot() {
        Message message = new Message();
        message.setUsername("[B0T]");
        return message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = escapeHtml(username);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = escapeHtml(content);
    }
}
