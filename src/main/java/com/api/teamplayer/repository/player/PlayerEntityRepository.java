package com.api.teamplayer.repository.player;

import com.api.teamplayer.entity.player.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlayerEntityRepository extends JpaRepository<PlayerEntity, Long> {

    @Query("select pe from PlayerEntity pe, TeamEntity team, TypeEntity type " +
            "where pe.teamId = team.teamId " +
            "and team.typeId = type.typeId " +
            "and pe.isActv = 1 " +
            "and type.code = ?1 ")
    List<PlayerEntity> findAllBasketballPlayers(String type);

    PlayerEntity findByPlayerId(Long playerId);
}
