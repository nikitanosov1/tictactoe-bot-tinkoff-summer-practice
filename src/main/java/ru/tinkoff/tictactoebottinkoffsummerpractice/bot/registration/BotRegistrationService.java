package ru.tinkoff.tictactoebottinkoffsummerpractice.bot.registration;

import ru.tinkoff.tictactoebottinkoffsummerpractice.bot.Figure;

import java.util.UUID;

public interface BotRegistrationService {
    Figure registerBotInSession(String botAddress, int botPort, UUID sessionUUID);
}
