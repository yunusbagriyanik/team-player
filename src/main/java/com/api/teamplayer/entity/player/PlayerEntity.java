package com.api.teamplayer.entity.player;

import com.api.teamplayer.entity.base.BaseEntity;
import com.api.teamplayer.entity.position.PositionEntity;
import com.api.teamplayer.entity.team.TeamEntity;

import javax.persistence.*;

import java.util.Objects;

import static com.api.teamplayer.base.util.Constants.TRUE_FLAG;

@Entity
@Table(name = "player")
public class PlayerEntity extends BaseEntity {
    private Long playerId;
    private String name;
    private String surname;
    private TeamEntity teamEntity;
    private Long teamId;
    private PositionEntity positionEntity;
    private Long positionId;
    private Integer isActv;

    @PrePersist
    public void onPrePersist() {
        this.isActv = TRUE_FLAG;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
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
    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @OneToOne(targetEntity = TeamEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id", referencedColumnName = "team_id", insertable = false, updatable = false)
    public TeamEntity getTeamEntity() {
        return teamEntity;
    }

    public void setTeamEntity(TeamEntity teamEntity) {
        this.teamEntity = teamEntity;
    }

    @Basic
    @Column(name = "team_id")
    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    @OneToOne(targetEntity = PositionEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id", referencedColumnName = "position_id", insertable = false, updatable = false)
    public PositionEntity getPositionEntity() {
        return positionEntity;
    }

    public void setPositionEntity(PositionEntity positionEntity) {
        this.positionEntity = positionEntity;
    }

    @Basic
    @Column(name = "position_id")
    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    @Basic
    @Column(name = "is_actv")
    public Integer getIsActv() {
        return isActv;
    }

    public void setIsActv(Integer isActv) {
        this.isActv = isActv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerEntity that = (PlayerEntity) o;
        return Objects.equals(playerId, that.playerId) && Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(teamEntity, that.teamEntity) && Objects.equals(teamId, that.teamId) && Objects.equals(positionEntity, that.positionEntity) && Objects.equals(positionId, that.positionId) && Objects.equals(isActv, that.isActv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId, name, surname, teamEntity, teamId, positionEntity, positionId, isActv);
    }
}
