package com.github.mateuszjanczak.springwebsocket.service;

import com.github.mateuszjanczak.springwebsocket.model.Coin;
import com.github.mateuszjanczak.springwebsocket.model.Covid;
import com.github.mateuszjanczak.springwebsocket.model.Message;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class BotService {

    private int randomNumber;
    private final RestTemplate restTemplate;

    public BotService() {
        generateNumber();
        restTemplate = new RestTemplate();
    }

    private void generateNumber() {
        randomNumber = (int) (Math.random() * 1000);
    }

    public List<Message> handleActionNumbers(Message user, String action) {
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

    public List<Message> handleActionJoin(Message user) {
        Message bot = Message.Bot();
        bot.setContent(user.getUsername() + " joined to chat!");
        return Collections.singletonList(bot);
    }

    public List<Message> handleActionBitcoin(Message user) {
        Message bot = Message.Bot();

        String url = "https://api.coinpaprika.com/v1/coins/btc-bitcoin/ohlcv/today";
        Coin[] response = restTemplate.getForObject(url, Coin[].class);

        assert response != null;
        bot.setContent(response[0].toString());

        return Arrays.asList(user, bot);
    }

    public List<Message> handleActionCovid(Message user) {
        Message bot = Message.Bot();

        String url = "https://koronawirus-api.herokuapp.com/api/covid19/daily";
        Covid response = restTemplate.getForObject(url, Covid.class);

        assert response != null;
        bot.setContent("Covid - Today { infections = " + response.getToday().getNewInfections() + ", deaths = " + response.getToday().getNewDeaths() + " }, General { infections = " + response.getGeneral().getInfections() + ", deaths = " + response.getGeneral().getDeaths() + ", recovered = " + response.getGeneral().getRecovered() + " }");

        return Arrays.asList(user, bot);
    }
}
