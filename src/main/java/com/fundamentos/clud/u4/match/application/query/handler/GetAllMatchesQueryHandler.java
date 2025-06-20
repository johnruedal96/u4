package com.fundamentos.clud.u4.match.application.query.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fundamentos.clud.u4.match.domain.Match;
import com.fundamentos.clud.u4.match.domain.MatchRepository;

@Service
public class GetAllMatchesQueryHandler {
    @Autowired
    private MatchRepository repository;

    public List<Match> handle() {
        return repository.findAll();
    }
}
