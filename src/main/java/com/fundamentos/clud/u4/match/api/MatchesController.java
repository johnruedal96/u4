package com.fundamentos.clud.u4.match.api;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fundamentos.clud.u4.match.application.command.CreateMatchCommand;
import com.fundamentos.clud.u4.match.application.command.DeleteMatchCommand;
import com.fundamentos.clud.u4.match.application.command.ProcessCsvFileCommand;
import com.fundamentos.clud.u4.match.application.command.UpdateMatchCommand;
import com.fundamentos.clud.u4.match.application.command.handler.CreateMatchCommandHandler;
import com.fundamentos.clud.u4.match.application.command.handler.DeleteMatchCommandHandler;
import com.fundamentos.clud.u4.match.application.command.handler.ProcessCsvFileCommandHandler;
import com.fundamentos.clud.u4.match.application.command.handler.UpdateMatchCommandHandler;
import com.fundamentos.clud.u4.match.application.query.handler.GetAllMatchesHandler;
import com.fundamentos.clud.u4.match.domain.Match;
import com.fundamentos.clud.u4.match.dto.ArchivoBase64DTO;
import com.fundamentos.clud.u4.match.dto.MatchRequestDTO;
import com.fundamentos.clud.u4.match.dto.MatchUpdateRequestDTO;
import com.fundamentos.clud.u4.match.mapper.MatchMapper;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("v1/matches")
@Slf4j
public class MatchesController {

    @Autowired
    private CreateMatchCommandHandler createMatchHandler;

    @Autowired
    private UpdateMatchCommandHandler updateMatchCommandHandler;

    @Autowired
    private GetAllMatchesHandler getAllMatchesHandler;

    @Autowired
    private ProcessCsvFileCommandHandler processCsvFileCommandHandler;

    @Autowired
    private DeleteMatchCommandHandler deleteHandler;

    @PostMapping
    public ResponseEntity<Match> createMatch(@RequestBody MatchRequestDTO requestDTO) {
        CreateMatchCommand command = MatchMapper.toCommand(requestDTO);
        Match created = createMatchHandler.handle(command);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Match> updateMatch(@PathVariable String id, @RequestBody MatchUpdateRequestDTO requestDTO) {
        UpdateMatchCommand command = MatchMapper.toUpdateCommand(id, requestDTO);
        Match created = updateMatchCommandHandler.handle(id, command);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<Match>> getAllMatches() {
        return ResponseEntity.ok(getAllMatchesHandler.handle());
    }

    @PostMapping("/upload")
    public ResponseEntity<String> procesarCsvBase64(@RequestBody ArchivoBase64DTO file) {
        try {
            ProcessCsvFileCommand command = new ProcessCsvFileCommand(file.getFile());
            processCsvFileCommandHandler.handle(command);
            return ResponseEntity.ok("File processed successfully");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Failed to decode the base64 file");
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Failed to read the CSV file");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        deleteHandler.handle(new DeleteMatchCommand(id));
        return ResponseEntity.noContent().build();
    }

}
