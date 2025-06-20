package com.fundamentos.clud.u4.match.application.command.handler;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fundamentos.clud.u4.match.application.command.CreateMatchCommand;
import com.fundamentos.clud.u4.match.application.command.UpdateMatchCommand;
import com.fundamentos.clud.u4.match.domain.Match;
import com.fundamentos.clud.u4.match.domain.MatchRepository;
import com.fundamentos.clud.u4.match.domain.RefereeTeam;

@Service
public class UpdateMatchCommandHandler {
    @Autowired
    private MatchRepository matchRepository;

    public Match handle(String id, UpdateMatchCommand command) {

        RefereeTeam refereeTeam = new RefereeTeam(
                command.getMainReferee(),
                command.getAssistantReferee1(),
                command.getAssistantReferee2());

        Match match = new Match(
                id,
                command.getHomeTeam(),
                command.getAwayTeam(),
                command.getWinnerTeam(),
                command.getDate(),
                command.getStadium(),
                command.getCity(),
                command.getStage(),
                command.getHomeGoals(),
                command.getAwayGoals(),
                command.getHighlightedPlayer(),
                refereeTeam);

        return matchRepository.update(id, match);
    }
}
