package com.github.mateuszjanczak.springwebsocket.model;

import lombok.Data;

import java.util.Date;

@Data
public class Covid {
    public Date reportDate;
    public General general;
    public Today today;
}


