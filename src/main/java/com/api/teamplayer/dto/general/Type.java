package com.api.teamplayer.dto.general;

public class Type {
    private Long typeId;
    private String name;
    private String code;
    private String entityName;

    public static Type init() {
        return new Type();
    }

    public Type typeId(Long typeId) {
        this.typeId = typeId;
        return this;
    }

    public Type name(String name) {
        this.name = name;
        return this;
    }

    public Type code(String code) {
        this.code = code;
        return this;
    }

    public Type entityName(String entityName) {
        this.entityName = entityName;
        return this;
    }

    public Long getTypeId() {
        return typeId;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getEntityName() {
        return entityName;
    }

    public Type build() {
        return this;
    }
}
