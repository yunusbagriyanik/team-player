package com.api.teamplayer.entity.position;

import com.api.teamplayer.entity.base.BaseEntity;
import com.api.teamplayer.entity.general.TypeEntity;

import javax.persistence.*;

@Entity
@Table(name = "position")
public class PositionEntity extends BaseEntity {
    private Long positionId;
    private String name;
    private String code;
    private TypeEntity typeEntity;
    private Long typeId;
    private Integer isActv;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id")
    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", referencedColumnName = "type_id", insertable = false, updatable = false)
    public TypeEntity getTypeEntity() {
        return typeEntity;
    }

    public void setTypeEntity(TypeEntity typeEntity) {
        this.typeEntity = typeEntity;
    }

    @Basic
    @Column(name = "type_id")
    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
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
