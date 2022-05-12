package com.api.teamplayer.repository.team;

import com.api.teamplayer.dto.team.TeamCapacity;
import com.api.teamplayer.entity.team.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeamEntityRepository extends JpaRepository<TeamEntity, Long> {

    @Query("select new com.api.teamplayer.dto.team.TeamCapacity(te.maxCapacity, te.availableCapacity)" +
            "from TeamEntity te " +
            "where te.teamId = ?1")
    TeamCapacity findTeamCapacity(Long teamId);

    TeamEntity findByTeamId(Long teamId);
}
