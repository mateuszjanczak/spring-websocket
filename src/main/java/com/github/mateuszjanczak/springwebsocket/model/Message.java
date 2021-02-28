package com.github.mateuszjanczak.springwebsocket.model;

import lombok.Data;

@Data
public class Message {
    String username;
    String content;

    public static Message Bot() {
        Message message = new Message();
        message.setUsername("<b>BOT</b>");
        return message;
    }
}
