package com.github.mateuszjanczak.springwebsocket.model;

import lombok.Data;

import java.util.List;

@Data
public class Today {
    public int newInfections;
    public int newDeaths;
    public List<DeathAgeRanx> deathAgeRanges;
    public DeathGender deathGender;
}
