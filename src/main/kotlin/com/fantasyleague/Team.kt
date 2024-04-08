package com.fantasyleague

import org.slf4j.LoggerFactory

data class Team(
    var name: String? = null,
    var players: MutableList<Player> = mutableListOf()
) {
    private val log = LoggerFactory.getLogger(Team::class.java)

    fun addPlayer(player: Player) {
        if (players.size >= 6) {
            log.warn("Team is already full, cannot add any more players")
            return
        }
        players.add(player)
    }
}