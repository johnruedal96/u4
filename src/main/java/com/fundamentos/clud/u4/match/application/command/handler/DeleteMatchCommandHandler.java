package com.fundamentos.clud.u4.match.application.command.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fundamentos.clud.u4.match.application.command.DeleteMatchCommand;
import com.fundamentos.clud.u4.match.domain.MatchRepository;

@Service
public class DeleteMatchCommandHandler {
    @Autowired
    private MatchRepository matchRepository;

    public DeleteMatchCommandHandler(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public void handle(DeleteMatchCommand command) {
        if (!matchRepository.exists(command.getMatchId())) {
            throw new IllegalArgumentException("Match with ID " + command.getMatchId() + " not found");
        }

        matchRepository.deleteById(command.getMatchId());
    }
}
