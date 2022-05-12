package com.api.teamplayer.service.player;

import com.api.teamplayer.base.exception.ProcessResultException;
import com.api.teamplayer.base.type.GenericResult;
import com.api.teamplayer.base.type.ProcessResult;
import com.api.teamplayer.dto.general.Lov;
import com.api.teamplayer.dto.mapper.Mapper;
import com.api.teamplayer.dto.player.CreatePlayerRequest;
import com.api.teamplayer.dto.player.Player;
import com.api.teamplayer.dto.team.TeamCapacity;
import com.api.teamplayer.dto.team.TeamParameter;
import com.api.teamplayer.entity.player.PlayerEntity;
import com.api.teamplayer.repository.player.PlayerEntityRepository;
import com.api.teamplayer.service.team.TeamService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerEntityRepository playerEntityRepository;
    private final TeamService teamService;
    private final Mapper mapper;

    @Autowired
    public PlayerServiceImpl(PlayerEntityRepository playerEntityRepository, TeamService teamService, Mapper mapper) {
        this.playerEntityRepository = playerEntityRepository;
        this.teamService = teamService;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Player> findAllPlayers(String teamTp) {
        this.validateRequest(teamTp);
        return Optional.ofNullable(this.playerEntityRepository.findAllBasketballPlayers(teamTp))
                .map(this.mapper::convertPlayerEntityListToPlayerList)
                .orElse(null);
    }

    @Override
    @Transactional
    public GenericResult<Player> addPlayer(CreatePlayerRequest request) {
        TeamParameter teamParameter = request.getTeamParams().stream()
                .filter(TeamParameter::isSelected)
                .findFirst()
                .orElseThrow(() -> new ProcessResultException(ProcessResult.noContent("TEAM_TYPE_MUST_BE_SELECTED"), true));

        PlayerEntity playerEntity = this.buildPlayerEntity(request, teamParameter);
        Player result = this.mapper.convertPlayerEntityToPlayer(playerEntity);

        GenericResult<Player> response = new GenericResult<>();
        response.setResult(result);
        response.setProcessResult(ProcessResult.success("Add Player API"));

        return response;
    }

    public void validaTeamCapacity(TeamParameter request) {
        TeamCapacity teamCapInfo = this.teamService.findTeamCapacity(this.findTeamId(request));

        if (teamCapInfo.getMaxCapacity() == teamCapInfo.getAvailableCapacity())
            throw new ProcessResultException(ProcessResult.badRequest("TEAM_CAPACITY_HAS_REACHED_THE_MAX_CAPACITY"), true);
    }

    @Override
    @Transactional
    public ProcessResult removePlayer(Long playerId) {
        if (playerId == null)
            throw new ProcessResultException(ProcessResult.badRequest("PLAYER_ID_CANNOT_BE_NULL"), false);

        PlayerEntity playerEntity = this.playerEntityRepository.findByPlayerId(playerId);
        if (playerEntity == null)
            throw new ProcessResultException(ProcessResult.noContent("PLAYER_NOT_FOUND player id: " + playerId), false);

        Long teamId = playerEntity.getTeamId();
        this.playerEntityRepository.deleteById(playerId);
        this.updateTeamCapacityAfterRemoveTeamMember(teamId);

        return ProcessResult.success("USER_WITH_ID_" + playerId + "_HAS_BEEN_DELETED");
    }

    public void updateTeamCapacityAfterRemoveTeamMember(Long teamId) {
        this.teamService.updateTeamCapacityAfterRemoveTeamMember(teamId);
    }

    public Long findPositionId(TeamParameter request) {
        return request.getPositions().stream()
                .filter(Lov::isSelected)
                .map(Lov::getId)
                .findFirst()
                .orElseThrow(() -> new ProcessResultException(ProcessResult.noContent("POSITION_ID_CANNOT_BE_NULL"), true));
    }

    public Long findTeamId(TeamParameter request) {
        return request.getTeams().stream()
                .filter(Lov::isSelected)
                .map(Lov::getId)
                .findFirst()
                .orElseThrow(() -> new ProcessResultException(ProcessResult.noContent("TEAM_ID_CANNOT_BE_NULL"), true));
    }

    public PlayerEntity buildPlayerEntity(CreatePlayerRequest request, TeamParameter teamParameter) {
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setName(request.getName());
        playerEntity.setSurname(request.getSurname());
        playerEntity.setPositionId(this.findPositionId(teamParameter));
        playerEntity.setTeamId(this.findTeamId(teamParameter));

        this.validaTeamCapacity(teamParameter);
        this.playerEntityRepository.save(playerEntity);
        this.teamService.updateTeamCapacity(this.findTeamId(teamParameter));

        return this.playerEntityRepository.findByPlayerId(playerEntity.getPlayerId());
    }

    private void validateRequest(String teamTp) {
        if (StringUtils.isBlank(teamTp))
            throw new ProcessResultException(ProcessResult.badRequest("TEAM_TYPE_MUST_BE_SELECTED"), false);
    }
}
