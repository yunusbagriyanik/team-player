package com.api.teamplayer.dto.team;

import com.api.teamplayer.dto.general.Lov;

import java.util.List;

public class TeamParameter {
    private String teamType;
    private boolean selected;
    private List<Lov> teams;
    private List<Lov> positions;

    public String getTeamType() {
        return teamType;
    }

    public void setTeamType(String teamType) {
        this.teamType = teamType;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public List<Lov> getTeams() {
        return teams;
    }

    public void setTeams(List<Lov> teams) {
        this.teams = teams;
    }

    public List<Lov> getPositions() {
        return positions;
    }

    public void setPositions(List<Lov> positions) {
        this.positions = positions;
    }
}
