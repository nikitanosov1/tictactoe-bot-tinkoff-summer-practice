package ru.tinkoff.tictactoebottinkoffsummerpractice.bot.registration;

import lombok.*;
import ru.tinkoff.tictactoebottinkoffsummerpractice.bot.Figure;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Builder
public class RegistrationResponse {
    private Figure figure;
}
