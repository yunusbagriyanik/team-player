package com.api.teamplayer.dto.position;

import com.api.teamplayer.dto.general.Type;

public class Position {
    private Long positionId;
    private String name;
    private String code;
    private Type teamType;

    public static Position init() {
        return new Position();
    }

    public Position positionId(Long positionId) {
        this.positionId = positionId;
        return this;
    }

    public Position name(String name) {
        this.name = name;
        return this;
    }

    public Position code(String code) {
        this.code = code;
        return this;
    }

    public Position teamType(Type type) {
        this.teamType = type;
        return this;
    }

    public Position build() {
        return this;
    }

    public Long getPositionId() {
        return positionId;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Type getTeamType() {
        return teamType;
    }
}
