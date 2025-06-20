package com.fundamentos.clud.u4.match.domain;

import java.util.List;
import java.util.Optional;

public interface MatchRepository {
    Match save(Match match);

    Optional<Match> findById(String id);

    List<Match> findAll();

    void deleteById(String id);

    boolean exists(String id);

    Match update(String id, Match match);
}
