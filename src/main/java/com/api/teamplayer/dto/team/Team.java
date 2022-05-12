package com.api.teamplayer.dto.team;

import com.api.teamplayer.dto.general.Type;

public class Team {
    private Long teamId;
    private String name;
    private Type type;
    private int maxCapacity;
    private int availableCapacity;

    public static Team init() {
        return new Team();
    }

    public Team teamId(Long teamId) {
        this.teamId = teamId;
        return this;
    }

    public Team name(String name) {
        this.name = name;
        return this;
    }

    public Team type(Type type) {
        this.type = type;
        return this;
    }

    public Team maxCapacity(int capacity) {
        this.maxCapacity = capacity;
        return this;
    }

    public Team availableCapacity(int capacity) {
        this.maxCapacity = capacity;
        return this;
    }

    public Long getTeamId() {
        return teamId;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getAvailableCapacity() {
        return availableCapacity;
    }

    public Team build() {
        return this;
    }
}
