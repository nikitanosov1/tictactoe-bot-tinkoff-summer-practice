package ru.tinkoff.tictactoebottinkoffsummerpractice.bot.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
class GameFieldRequestDto {
    @JsonProperty(value = "game_field")
    private String gameField;
}
