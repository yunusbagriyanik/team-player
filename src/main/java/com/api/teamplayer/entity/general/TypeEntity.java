package com.api.teamplayer.entity.general;

import com.api.teamplayer.entity.base.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "type")
public class TypeEntity extends BaseEntity {
    private Long typeId;
    private String name;
    private String code;
    private String entityName;
    private Integer isActv;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "entity_name")
    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    @Basic
    @Column(name = "is_actv")
    public Integer getIsActv() {
        return isActv;
    }

    public void setIsActv(Integer isActv) {
        this.isActv = isActv;
    }
}
