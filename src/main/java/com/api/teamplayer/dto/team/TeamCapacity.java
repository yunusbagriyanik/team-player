package com.api.teamplayer.dto.team;

public class TeamCapacity {
    private int maxCapacity;
    private int availableCapacity;

    public TeamCapacity(int maxCapacity, int availableCapacity) {
        this.maxCapacity = maxCapacity;
        this.availableCapacity = availableCapacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getAvailableCapacity() {
        return availableCapacity;
    }

    public void setAvailableCapacity(int availableCapacity) {
        this.availableCapacity = availableCapacity;
    }
}
