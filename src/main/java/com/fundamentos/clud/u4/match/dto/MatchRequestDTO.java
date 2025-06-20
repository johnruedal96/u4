package com.fundamentos.clud.u4.match.dto;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MatchRequestDTO {
    private String equipoLocal;
    private String equipoVisitante;
    private String equipoGanador;
    private ZonedDateTime fecha;
    private String estadio;
    private String ciudad;
    private String fase;
    private int golesLocal;
    private int golesVisitante;
    private String jugadorDestacado;

    private refereeTeam ternaArbitral;

    @Data
    @SuperBuilder
    @NoArgsConstructor
    public static class refereeTeam {
        private RefereeDTO principal;
        private RefereeDTO asistente1;
        private RefereeDTO asistente2;
    }

    @Data
    @SuperBuilder
    @NoArgsConstructor
    public static class RefereeDTO {
        private String nombre;
        private String nacionalidad;
    }

}
