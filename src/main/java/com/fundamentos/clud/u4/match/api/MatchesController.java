package com.fundamentos.clud.u4.match.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("v1/matches")
public class MatchesController {
    @GetMapping("/all")
    private String getAllMatches() {
        return "all mathces";
    }
}
