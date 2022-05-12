package com.api.teamplayer.service.team;

import com.api.teamplayer.dto.team.TeamCapacity;

public interface TeamService {
    TeamCapacity findTeamCapacity(Long teamId);

    void updateTeamCapacity(Long teamId);

    void updateTeamCapacityAfterRemoveTeamMember(Long teamId);
}
