package com.github.mateuszjanczak.springwebsocket.controller;

import com.github.mateuszjanczak.springwebsocket.model.Message;
import com.github.mateuszjanczak.springwebsocket.service.BotService;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Collections;
import java.util.List;

@Controller
public class MessageResource {

    private final BotService botService;

    public MessageResource(BotService botService) {
        this.botService = botService;
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

    private List<Message> handleAction(Message user) {
        String action = user.getContent().substring(1, user.getContent().length() - 1);

        if(action.equals("joined")) {
            return botService.handleActionJoin(user);
        }

        if(action.equals("bitcoin")) {
            return botService.handleActionBitcoin(user);
        }

        if(NumberUtils.isDigits(action)) {
            return botService.handleActionNumbers(user, action);
        }

        return Collections.emptyList();
    }






}
