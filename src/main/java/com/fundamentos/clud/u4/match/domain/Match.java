package com.fundamentos.clud.u4.match.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Match {
    private String id;
    private String equipoLocal;
    private String equipoVisitante;
    private String equipoGanador;
    private Date fecha;
    private String estadio;
    private String ciudad;
    private String fase;
    private int golesLocal;
    private int golesVisitante;
    private String jugadorDestacado;
    private RefereeTeam ternaArbitral;
}
