package com.github.mateuszjanczak.springwebsocket.model;

import lombok.Data;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Data
public class Coin {
    public Date time_open;
    public Date time_close;
    public double open;
    public double high;
    public double low;
    public double close;
    public long volume;
    public long market_cap;

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);

        return "Bitcoin { " +
                "time_open = " + dateFormat.format(time_open) +
                ", time_close = " + dateFormat.format(time_close) +
                ", open = " + currencyFormat.format(open) +
                ", high = " + currencyFormat.format(high) +
                ", low = " + currencyFormat.format(low) +
                ", close = " + currencyFormat.format(close) +
                ", volume = " + currencyFormat.format(volume) +
                ", market_cap = " + currencyFormat.format(market_cap) +
                " }";
    }
}