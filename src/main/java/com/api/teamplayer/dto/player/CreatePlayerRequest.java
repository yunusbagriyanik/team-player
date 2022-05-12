package com.api.teamplayer.dto.player;

import com.api.teamplayer.dto.team.TeamParameter;

import java.util.List;

public class CreatePlayerRequest {
    private String name;
    private String surname;
    private List<TeamParameter> teamParams;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<TeamParameter> getTeamParams() {
        return teamParams;
    }

    public void setTeamParams(List<TeamParameter> teamParams) {
        this.teamParams = teamParams;
    }
}
