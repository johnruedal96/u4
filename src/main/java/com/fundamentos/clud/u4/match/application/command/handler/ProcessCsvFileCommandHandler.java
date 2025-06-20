package com.fundamentos.clud.u4.match.application.command.handler;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fundamentos.clud.u4.match.application.command.CreateMatchCommand;
import com.fundamentos.clud.u4.match.application.command.ProcessCsvFileCommand;
import com.fundamentos.clud.u4.match.domain.Referee;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProcessCsvFileCommandHandler {

    @Autowired
    private CreateMatchCommandHandler createHandler;

    public void handle(ProcessCsvFileCommand command) throws IOException {
        byte[] decodedBytes = Base64.getDecoder().decode(command.getFile());

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new ByteArrayInputStream(decodedBytes), StandardCharsets.UTF_8))) {

            String header = reader.readLine();
            log.info(header);

            String line;
            while ((line = reader.readLine()) != null) {
                log.info("Processing line: " + line);
                String[] columns = line.split(",");
                String homeTeam = columns[0];
                String awayTeam = columns[1];
                String winnerTeam = columns[2];
                ZonedDateTime date = ZonedDateTime.parse(columns[3]);
                String stadium = (columns[4]);
                String city = (columns[5]);
                String stage = (columns[6]);
                int homeGoals = (Integer.parseInt(columns[7]));
                int awayGoals = (Integer.parseInt(columns[8]));
                String highlightedPlayer = (columns[9]);

                Referee mainReferee = new Referee(columns[10], columns[11]);

                Referee assistantReferee1 = new Referee(columns[12], columns[13]);

                Referee assistantReferee2 = new Referee(columns[14], columns[15]);

                CreateMatchCommand createCommand = new CreateMatchCommand(homeTeam, awayTeam, winnerTeam,
                        Date.from(date.toInstant()), stadium,
                        city, stage, homeGoals, awayGoals, highlightedPlayer, mainReferee, assistantReferee1,
                        assistantReferee2);

                createHandler.handle(createCommand);
            }
        }
    }
}
