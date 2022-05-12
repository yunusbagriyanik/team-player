package com.api.teamplayer.dto.player;

import com.api.teamplayer.dto.position.Position;
import com.api.teamplayer.dto.team.Team;

import java.util.Objects;

public class Player {
    private Long playerId;
    private String name;
    private String surname;
    private Team team;
    private Position position;

    public static Player init() {
        return new Player();
    }


    public Player playerId(Long playerId) {
        this.playerId = playerId;
        return this;
    }

    public Player name(String name) {
        this.name = name;
        return this;
    }

    public Player surname(String surname) {
        this.surname = surname;
        return this;
    }

    public Player team(Team team) {
        this.team = team;
        return this;
    }

    public Player position(Position position) {
        this.position = position;
        return this;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Team getTeam() {
        return team;
    }

    public Position getPosition() {
        return position;
    }

    public Player build() {
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(playerId, player.playerId) && Objects.equals(name, player.name) && Objects.equals(surname, player.surname) && Objects.equals(team, player.team) && Objects.equals(position, player.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId, name, surname, team, position);
    }
}
