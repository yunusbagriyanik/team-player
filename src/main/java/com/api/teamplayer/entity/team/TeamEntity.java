package com.api.teamplayer.entity.team;

import com.api.teamplayer.entity.base.BaseEntity;
import com.api.teamplayer.entity.general.TypeEntity;

import javax.persistence.*;

@Entity
@Table(name = "team")
public class TeamEntity extends BaseEntity {
    private Long teamId;
    private String name;
    private TypeEntity typeEntity;
    private Long typeId;
    private int maxCapacity;
    private int availableCapacity;
    private Integer isActv;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    @Basic
    @Column(name = "team_name")
    public String getName() {
        return name;
    }

    public void setName(String teamName) {
        this.name = teamName;
    }

    @OneToOne(targetEntity = TypeEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id", referencedColumnName = "type_id", insertable = false, updatable = false)
    public TypeEntity getTypeEntity() {
        return typeEntity;
    }

    public void setTypeEntity(TypeEntity generalTypeEntity) {
        this.typeEntity = generalTypeEntity;
    }

    @Basic
    @Column(name = "type_id")
    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long gnlTpId) {
        this.typeId = gnlTpId;
    }

    @Basic
    @Column(name = "max_cap")
    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    @Basic
    @Column(name = "available_cap")
    public int getAvailableCapacity() {
        return availableCapacity;
    }

    public void setAvailableCapacity(int availableCapacity) {
        this.availableCapacity = availableCapacity;
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
