package com.api.teamplayer.service.player;

import com.api.teamplayer.base.type.GenericResult;
import com.api.teamplayer.base.type.ProcessResult;
import com.api.teamplayer.dto.player.CreatePlayerRequest;
import com.api.teamplayer.dto.player.Player;

import java.util.List;

public interface PlayerService {
    List<Player> findAllPlayers(String teamTp);

    GenericResult<Player> addPlayer(CreatePlayerRequest request);

    ProcessResult removePlayer(Long playerId);
}
