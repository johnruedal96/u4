package com.fundamentos.clud.u4.match.application.command;

import java.util.Date;

import com.fundamentos.clud.u4.match.domain.Referee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CreateMatchCommand {

    private String homeTeam;
    private String awayTeam;
    private String winnerTeam;
    private Date date;
    private String stadium;
    private String city;
    private String stage;
    private int homeGoals;
    private int awayGoals;
    private String highlightedPlayer;

    private Referee mainReferee;
    private Referee assistantReferee1;
    private Referee assistantReferee2;
}
