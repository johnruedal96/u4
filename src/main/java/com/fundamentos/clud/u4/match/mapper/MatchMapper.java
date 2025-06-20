package com.fundamentos.clud.u4.match.mapper;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;

import com.fundamentos.clud.u4.match.application.command.CreateMatchCommand;
import com.fundamentos.clud.u4.match.application.command.UpdateMatchCommand;
import com.fundamentos.clud.u4.match.domain.Referee;
import com.fundamentos.clud.u4.match.dto.MatchRequestDTO;
import com.fundamentos.clud.u4.match.dto.MatchUpdateRequestDTO;

public class MatchMapper {
    public static CreateMatchCommand toCommand(MatchRequestDTO dto) {
        return new CreateMatchCommand(
                dto.getEquipoLocal(),
                dto.getEquipoVisitante(),
                dto.getEquipoGanador(),
                mapDate(dto.getFecha()),
                dto.getEstadio(),
                dto.getCiudad(),
                dto.getFase(),
                dto.getGolesLocal(),
                dto.getGolesVisitante(),
                dto.getJugadorDestacado(),
                mapRef(dto.getTernaArbitral().getPrincipal()),
                mapRef(dto.getTernaArbitral().getAsistente1()),
                mapRef(dto.getTernaArbitral().getAsistente2()));
    }

    public static UpdateMatchCommand toUpdateCommand(String id, MatchUpdateRequestDTO dto) {
        return UpdateMatchCommand.builder()
                .id(id)
                .homeTeam(dto.getEquipoLocal())
                .awayTeam(dto.getEquipoVisitante())
                .winnerTeam(dto.getEquipoGanador())
                .date(mapDate(dto.getFecha()))
                .stadium(dto.getEstadio())
                .city(dto.getCiudad())
                .stage(dto.getFase())
                .homeGoals(dto.getGolesLocal())
                .awayGoals(dto.getGolesVisitante())
                .highlightedPlayer(dto.getJugadorDestacado())
                .mainReferee(mapRef(dto.getTernaArbitral().getPrincipal()))
                .assistantReferee1(mapRef(dto.getTernaArbitral().getAsistente1()))
                .assistantReferee2(mapRef(dto.getTernaArbitral().getAsistente2()))
                .build();
    }

    private static Referee mapRef(MatchRequestDTO.RefereeDTO dto) {
        return new Referee(dto.getNombre(), dto.getNacionalidad());
    }

    private static Date mapDate(ZonedDateTime date) {
        Instant instant = date.toInstant();
        return Date.from(instant);
    }
}
