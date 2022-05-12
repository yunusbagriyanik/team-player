package com.api.teamplayer.resolver;

import com.api.teamplayer.dto.player.Player;
import com.api.teamplayer.service.player.PlayerService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlayerQueryResolver implements GraphQLQueryResolver {

    private final PlayerService playerService;

    @Autowired
    public PlayerQueryResolver(PlayerService playerService) {
        this.playerService = playerService;
    }

    public List<Player> findAllPlayers(String teamTp) {
        return this.playerService.findAllPlayers(teamTp);
    }
}
