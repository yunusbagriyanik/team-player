package com.api.teamplayer.service.team;

import com.api.teamplayer.dto.team.TeamCapacity;
import com.api.teamplayer.entity.team.TeamEntity;
import com.api.teamplayer.repository.team.TeamEntityRepository;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamEntityRepository teamEntityRepository;

    public TeamServiceImpl(TeamEntityRepository teamEntityRepository) {
        this.teamEntityRepository = teamEntityRepository;
    }

    @Override
    public TeamCapacity findTeamCapacity(Long teamId) {
        return this.teamEntityRepository.findTeamCapacity(teamId);
    }

    @Override
    public void updateTeamCapacity(Long teamId) {
        TeamEntity teamEntity = this.teamEntityRepository.findByTeamId(teamId);
        int availableCapacity = teamEntity.getAvailableCapacity();
        teamEntity.setAvailableCapacity(availableCapacity + 1);
    }

    @Override
    public void updateTeamCapacityAfterRemoveTeamMember(Long teamId) {
        TeamEntity teamEntity = this.teamEntityRepository.findByTeamId(teamId);
        int availableCapacity = teamEntity.getAvailableCapacity();
        teamEntity.setAvailableCapacity(availableCapacity - 1);
    }
}
