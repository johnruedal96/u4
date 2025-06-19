package com.fundamentos.clud.u4.match.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefereeTeam {
    private Referee principal;
    private Referee asistente1;
    private Referee asistente2;
}
