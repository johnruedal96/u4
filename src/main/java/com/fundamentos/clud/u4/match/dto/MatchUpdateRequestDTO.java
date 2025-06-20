package com.fundamentos.clud.u4.match.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class MatchUpdateRequestDTO extends MatchRequestDTO {
    private String id;
}
