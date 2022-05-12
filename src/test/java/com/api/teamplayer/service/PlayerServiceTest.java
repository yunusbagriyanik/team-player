package com.api.teamplayer.service;

import com.api.teamplayer.base.type.GenericResult;
import com.api.teamplayer.base.type.ProcessResult;
import com.api.teamplayer.dto.general.Lov;
import com.api.teamplayer.dto.general.Type;
import com.api.teamplayer.dto.mapper.Mapper;
import com.api.teamplayer.dto.player.CreatePlayerRequest;
import com.api.teamplayer.dto.player.Player;
import com.api.teamplayer.dto.position.Position;
import com.api.teamplayer.dto.team.Team;
import com.api.teamplayer.dto.team.TeamCapacity;
import com.api.teamplayer.dto.team.TeamParameter;
import com.api.teamplayer.entity.general.TypeEntity;
import com.api.teamplayer.entity.player.PlayerEntity;
import com.api.teamplayer.entity.position.PositionEntity;
import com.api.teamplayer.entity.team.TeamEntity;
import com.api.teamplayer.repository.player.PlayerEntityRepository;
import com.api.teamplayer.repository.team.TeamEntityRepository;
import com.api.teamplayer.service.player.PlayerServiceImpl;
import com.api.teamplayer.service.team.TeamServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.Silent.class)
public class PlayerServiceTest {

    private static final String typeOfTeam = "basket";
    private static final Long id = 1L;
    private static final Integer TRUE_FLAG = 1;
    private static final String positionCode = "SF";
    private static final String positionName = "Small forward";
    private static final String teamName = "Los Angeles Lakers";
    private static final int maxCapacity = 6;
    private static final int availableCap = 1;

    TypeEntity gnlType = new TypeEntity();
    TeamEntity teamEntity = new TeamEntity();
    PositionEntity positionEntity = new PositionEntity();
    PlayerEntity playerEntity = new PlayerEntity();
    List<PlayerEntity> playerEntityList = new ArrayList<>();
    Player player;
    List<Player> playerList = new ArrayList<>();

    CreatePlayerRequest request = new CreatePlayerRequest();
    List<TeamParameter> teamParameters = new ArrayList<>();
    TeamParameter teamParameter = new TeamParameter();
    List<Lov> teams = new ArrayList<>();
    List<Lov> positions = new ArrayList<>();
    Lov teamLov = new Lov();
    Lov position = new Lov();
    GenericResult<Player> result = new GenericResult<>();
    TeamCapacity teamCapacity = new TeamCapacity(maxCapacity, availableCap);

    private PlayerServiceImpl playerService;
    private PlayerEntityRepository playerEntityRepository;
    private TeamEntityRepository teamEntityRepository;
    private Mapper playerMapper;

    TeamServiceImpl teamService;

    @Before
    public void init() {
        this.playerEntityRepository = Mockito.mock(PlayerEntityRepository.class);
        this.playerMapper = Mockito.mock(Mapper.class);
        this.teamEntityRepository = Mockito.mock(TeamEntityRepository.class);
        this.teamService = Mockito.spy(new TeamServiceImpl(teamEntityRepository));
        this.playerService = Mockito.spy(new PlayerServiceImpl(this.playerEntityRepository, teamService, this.playerMapper));
    }

    @Test
    public void whenFindAllPlayersCalledWithValidTypeOfTeam_itShouldReturnPlayers() {

        this.initializeFindAllBasketballPlayersObjects();

        Mockito.when(this.playerEntityRepository.findAllBasketballPlayers(typeOfTeam)).thenReturn(playerEntityList);
        Mockito.when(this.playerMapper.convertPlayerEntityListToPlayerList(playerEntityList)).thenReturn(playerList);
        Mockito.when(this.playerService.findAllPlayers(typeOfTeam)).thenReturn(playerList);

        List<Player> result = this.playerService.findAllPlayers(typeOfTeam);
        Assert.assertNotNull("CANNOT_FIND_PLAYER with this team type: " + typeOfTeam, result.stream().findAny().orElse(null));
        Assert.assertEquals("TEST_FAILURE!", result, playerList);

        Mockito.verify(this.playerEntityRepository, Mockito.times(2)).findAllBasketballPlayers(typeOfTeam);
        Mockito.verify(this.playerMapper).convertPlayerEntityListToPlayerList(playerEntityList);
        Mockito.verify(this.playerService, Mockito.times(2)).findAllPlayers(typeOfTeam);
    }

    @Test
    public void whenAddPlayerCalledWithValidRequest_itShouldReturnPlayer() {

        this.initializeAddPlayerObjects();

        Mockito.when(this.playerEntityRepository.save(playerEntity)).thenReturn(playerEntity);
        Mockito.when(this.playerEntityRepository.findByPlayerId(playerEntity.getPlayerId())).thenReturn(playerEntity);
        Mockito.when(this.playerMapper.convertPlayerEntityToPlayer(playerEntity)).thenReturn(player);
        Mockito.when(teamEntityRepository.findByTeamId(teamLov.getId())).thenReturn(teamEntity);
        Mockito.when(this.teamService.findTeamCapacity(this.playerService.findTeamId(teamParameter))).thenReturn(teamCapacity);
        Mockito.when(this.playerService.buildPlayerEntity(request, teamParameter)).thenReturn(playerEntity);
        doReturn(result).when(playerService).addPlayer(request);
        Mockito.when(this.playerService.addPlayer(request)).thenReturn(result);

        GenericResult<Player> svcResponse = this.playerService.addPlayer(request);
        Assert.assertEquals("TEST_FAILURE!", result, svcResponse);

        this.playerEntityRepository.findByPlayerId(playerEntity.getPlayerId());
        this.playerMapper.convertPlayerEntityToPlayer(playerEntity);

        Mockito.verify(this.playerEntityRepository).save(Mockito.any(PlayerEntity.class));
        Mockito.verify(this.playerEntityRepository).findByPlayerId(playerEntity.getPlayerId());
        Mockito.verify(this.playerMapper).convertPlayerEntityToPlayer(Mockito.any(PlayerEntity.class));
        Mockito.verify(this.teamEntityRepository).findByTeamId(teamLov.getId());
        Mockito.verify(this.playerService).buildPlayerEntity(request, teamParameter);
        Mockito.verify(this.playerService).addPlayer(request);
    }

    @Test
    public void whenRemovePlayerCalledWithValidRequest_removePlayerInRequest() {
        this.initializeAddPlayerObjects();

        Mockito.when(this.playerEntityRepository.findByPlayerId(id)).thenReturn(playerEntity);
        Mockito.when(this.teamEntityRepository.findByTeamId(id)).thenReturn(teamEntity);

        doReturn(ProcessResult.success("USER_WITH_ID_" + id + "_HAS_BEEN_DELETED")).when(playerService).removePlayer(id);
        Mockito.when(this.playerService.removePlayer(id)).thenReturn(ProcessResult.success("USER_WITH_ID_" + id + "_HAS_BEEN_DELETED"));

        ProcessResult processResult = this.playerService.removePlayer(id);
        Assert.assertEquals(processResult, ProcessResult.success("USER_WITH_ID_" + id + "_HAS_BEEN_DELETED"));

        this.playerEntityRepository.findByPlayerId(id);
        this.teamEntityRepository.findByTeamId(teamEntity.getTeamId());
        Mockito.verify(this.playerEntityRepository).findByPlayerId(id);
        Mockito.verify(this.teamEntityRepository).findByTeamId(teamEntity.getTeamId());
        Mockito.verify(this.playerService).removePlayer(id);
    }

    public void initializeAddPlayerObjects() {
        this.buildPlayerEntity();

        teamParameter.setSelected(true);
        teamParameter.setTeamType(typeOfTeam);

        teamLov.setId(id);
        teamLov.setKey(typeOfTeam);
        teamLov.setSelected(true);
        teamLov.setValue(teamName);

        teams.add(teamLov);

        position.setId(id);
        position.setValue(positionName);
        position.setKey(positionCode);
        position.setSelected(true);
        positions.add(position);

        teamParameter.setTeams(teams);
        teamParameters.add(teamParameter);
        teamParameter.setPositions(positions);

        request.setTeamParams(teamParameters);

        this.buildPlayer();

        result.setResult(player);
        result.setProcessResult(ProcessResult.success("Add Player API"));
    }

    public void initializeFindAllBasketballPlayersObjects() {
        this.buildPlayerEntity();
        playerEntityList.add(playerEntity);
        this.buildPlayer();
        playerList.add(player);
    }

    public void buildPlayerEntity() {
        gnlType.setTypeId(id);
        gnlType.setCode(typeOfTeam);

        teamEntity.setTeamId(id);
        teamEntity.setTypeId(id);
        teamEntity.setTypeEntity(gnlType);
        teamEntity.setAvailableCapacity(availableCap);
        teamEntity.setMaxCapacity(maxCapacity);

        positionEntity.setPositionId(id);

        playerEntity.setPlayerId(id);
        playerEntity.setTeamId(id);
        playerEntity.setPositionId(id);
        playerEntity.setIsActv(TRUE_FLAG);
        playerEntity.setPositionEntity(positionEntity);
        playerEntity.setTeamEntity(teamEntity);
    }

    private void buildPlayer() {
        Type type = Type.init()
                .typeId(gnlType.getTypeId())
                .code(gnlType.getCode())
                .entityName(gnlType.getEntityName())
                .code(gnlType.getCode())
                .name(gnlType.getName())
                .build();

        Team team = Team.init()
                .teamId(teamEntity.getTeamId())
                .type(type)
                .name(teamEntity.getName())
                .build();

        Position position = Position.init()
                .positionId(positionEntity.getPositionId())
                .code(positionEntity.getCode())
                .name(positionEntity.getName());

        player = Player.init()
                .playerId(1L)
                .team(team)
                .position(position)
                .build();
    }
}
