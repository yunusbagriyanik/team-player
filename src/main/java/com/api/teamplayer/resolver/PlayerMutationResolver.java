package com.api.teamplayer.resolver;

import com.api.teamplayer.base.type.GenericResult;
import com.api.teamplayer.base.type.ProcessResult;
import com.api.teamplayer.dto.player.CreatePlayerRequest;
import com.api.teamplayer.dto.player.Player;
import com.api.teamplayer.service.player.PlayerService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerMutationResolver implements GraphQLMutationResolver {

    private final PlayerService playerService;

    @Autowired
    public PlayerMutationResolver(PlayerService playerService) {
        this.playerService = playerService;
    }

    public GenericResult<Player> addPlayer(CreatePlayerRequest request) {
        return this.playerService.addPlayer(request);
    }

    public ProcessResult removePlayer(Long playerId) {
        return this.playerService.removePlayer(playerId);
    }
}
