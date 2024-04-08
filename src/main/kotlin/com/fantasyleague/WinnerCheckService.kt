package com.fantasyleague

import jakarta.inject.Singleton
import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger(WinnerCheckService::class.java)

@Singleton
class WinnerCheckService {
    fun getWinnerName(records: List<String>): String {
        val team1 = createTeamObject(records.subList(0, 7))
        val team2 = createTeamObject(records.subList(7, 14))
        val team1Score = calculateTeamScore(team1)
        val team2Score = calculateTeamScore(team2)

        log.info("Team 1 score :: $team1Score")
        log.info("Team 2 score :: $team2Score")
        return "Winning team is :: ${if (team1Score > team2Score) team1.name else team2.name}"
    }

    private fun createTeamObject(teamAndItsPlayerDetails: List<String>): Team {
        val team = Team();
        team.name = teamAndItsPlayerDetails[0]
        teamAndItsPlayerDetails.drop(1).forEach { str ->
            val split = str.split(",")
            team.addPlayer(
                Player(
                    name = split[0],
                    runs = split[1].toInt(),
                    wickets = split[2].toInt()
                )
            )
        }
        return team
    }

    private fun calculateTeamScore(team: Team): Int {
        return team.players.map { player ->
            getScoreForRuns(player.runs) + getScoreForWickets(player.wickets)
        }.sum()
    }

    private fun getScoreForRuns(runs: Int?): Int {
        if (runs == null) return 0
        if (runs < 1) return -5
        val bonus = 10 * (runs / 25)
        return runs + bonus
    }

    private fun getScoreForWickets(wickets: Int?): Int {
        if (wickets == null) return 0
        val bonus = 20 * (wickets / 3)
        return wickets * 40 + bonus
    }
}
