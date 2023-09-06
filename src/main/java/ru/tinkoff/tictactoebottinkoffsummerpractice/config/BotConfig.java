package ru.tinkoff.tictactoebottinkoffsummerpractice.config;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.tictactoebottinkoffsummerpractice.bot.Figure;
import ru.tinkoff.tictactoebottinkoffsummerpractice.bot.registration.BotRegistrationService;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.UUID;

@Slf4j
@Getter
@RequiredArgsConstructor
@Configuration
public class BotConfig {
    @Value("${server.port}")
    private int port;

    @Value("${session-uuid}")
    private UUID sessionUUID;

    private final BotRegistrationService botRegistrationService;
    private Figure figure;

    @PostConstruct
    public void init() throws SocketException {
        String hostAddress = NetworkInterface.getNetworkInterfaces().nextElement().getInetAddresses().nextElement().getHostAddress();
        log.info("Попытка зарегистрировать бота с ip {} и port {} в сессии {}", hostAddress, port, sessionUUID);

        figure = botRegistrationService.registerBotInSession(hostAddress, port, sessionUUID);
        log.info("Успешно зарегистрирован. Буду ходить фигурой {}", figure);
    }
}
