package com.github.mateuszjanczak.springwebsocket.controller;

import com.github.mateuszjanczak.springwebsocket.model.Message;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
public class MessageResource {

    private int randomNumber;

    public MessageResource() {
        generateNumber();
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public List<Message> get(Message user) {
        if (user.getContent().startsWith("[") && user.getContent().endsWith("]")) {
            return handleAction(user);
        } else {
            return Collections.singletonList(user);
        }
    }

    private void generateNumber() {
        randomNumber = (int) (Math.random() * 1000);
    }

    private List<Message> handleAction(Message user) {
        String action = user.getContent().substring(1, user.getContent().length() - 1);

        if(action.equals("joined")) {
            return handleActionJoin(user);
        }

        if(NumberUtils.isDigits(action)) {
            return handleActionNumbers(user, action);
        }

        return Collections.emptyList();
    }

    private List<Message> handleActionJoin(Message user) {
        Message bot = Message.Bot();
        bot.setContent(user.getUsername() + " joined to chat!");
        return Collections.singletonList(bot);
    }

    private List<Message> handleActionNumbers(Message user, String action) {
        Message bot = Message.Bot();
        int number = Integer.parseInt(action);

        if(number == randomNumber) {
            bot.setContent(user.getUsername() + " guessed the correct number");
            generateNumber();
        } else if(randomNumber > number) {
            bot.setContent("Wrong number: " + "x > " + number);
        } else {
            bot.setContent("Wrong number: " + "x < " + number);
        }

        return Arrays.asList(user, bot);
    }
}
