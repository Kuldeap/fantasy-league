package com.fantasyleague
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Put;
import jakarta.inject.Inject


@Controller("/winner")
class MatchController (@Inject val winnerCheckService: WinnerCheckService ){

    @Put
    fun getWinnerName(teamData : List<String>) : String {
       return winnerCheckService.getWinnerName(teamData);
    }
 }