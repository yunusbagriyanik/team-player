package com.api.teamplayer.dto.mapper;

import com.api.teamplayer.dto.general.Type;
import com.api.teamplayer.dto.player.Player;
import com.api.teamplayer.dto.position.Position;
import com.api.teamplayer.dto.team.Team;
import com.api.teamplayer.dto.user.User;
import com.api.teamplayer.entity.general.TypeEntity;
import com.api.teamplayer.entity.player.PlayerEntity;
import com.api.teamplayer.entity.position.PositionEntity;
import com.api.teamplayer.entity.team.TeamEntity;
import com.api.teamplayer.entity.user.UserEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Mapper {

    public User convertUserEntityToUser(UserEntity userEntity) {
        return User.init()
                .userId(userEntity.getUserId())
                .email(userEntity.getEmail())
                .lastName(userEntity.getLastName())
                .firstName(userEntity.getFirstName())
                .build();
    }

    public List<Player> convertPlayerEntityListToPlayerList(List<PlayerEntity> sourceList) {
        List<Player> targetList = new ArrayList<>();
        for (PlayerEntity each : sourceList) {
            targetList.add(Player.init()
                    .playerId(each.getPlayerId())
                    .name(each.getName())
                    .surname(each.getSurname())
                    .team(this.convertTeamEntityToTeam(each.getTeamEntity()))
                    .position(this.convertPositionEntityToPosition(each.getPositionEntity()))
                    .build());
        }

        return targetList;
    }

    public Player convertPlayerEntityToPlayer(PlayerEntity playerEntity) {
        return Player.init()
                .playerId(playerEntity.getPlayerId())
                .name(playerEntity.getName())
                .surname(playerEntity.getSurname())
                .team(this.convertTeamEntityToTeam(playerEntity.getTeamEntity()))
                .position(this.convertPositionEntityToPosition(playerEntity.getPositionEntity()))
                .build();
    }

    public Position convertPositionEntityToPosition(PositionEntity positionEntity) {
        if (positionEntity != null)
            return Position.init()
                    .positionId(positionEntity.getPositionId())
                    .code(positionEntity.getCode())
                    .teamType(this.convertTypeEntityToType(positionEntity.getTypeEntity()))
                    .name(positionEntity.getName());

        return null;
    }

    public Type convertTypeEntityToType(TypeEntity typeEntity) {
        if (typeEntity != null)
            return Type.init()
                    .typeId(typeEntity.getTypeId())
                    .code(typeEntity.getCode())
                    .entityName(typeEntity.getEntityName())
                    .code(typeEntity.getCode())
                    .name(typeEntity.getName())
                    .build();

        return null;
    }

    public Team convertTeamEntityToTeam(TeamEntity teamEntity) {
        if (teamEntity != null)
            return Team.init()
                    .teamId(teamEntity.getTeamId())
                    .type(this.convertTypeEntityToType(teamEntity.getTypeEntity()))
                    .maxCapacity(teamEntity.getMaxCapacity())
                    .availableCapacity(teamEntity.getAvailableCapacity())
                    .name(teamEntity.getName())
                    .build();

        return null;
    }
}
