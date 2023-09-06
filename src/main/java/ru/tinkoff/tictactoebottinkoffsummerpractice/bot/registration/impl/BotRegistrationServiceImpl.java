package ru.tinkoff.tictactoebottinkoffsummerpractice.bot.registration.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.tinkoff.tictactoebottinkoffsummerpractice.bot.Figure;
import ru.tinkoff.tictactoebottinkoffsummerpractice.bot.registration.BotRegistrationService;
import ru.tinkoff.tictactoebottinkoffsummerpractice.bot.registration.RegistrationRequest;
import ru.tinkoff.tictactoebottinkoffsummerpractice.bot.registration.RegistrationResponse;
import ru.tinkoff.tictactoebottinkoffsummerpractice.bot.registration.exception.BotRegistrationException;

import java.net.InetAddress;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class BotRegistrationServiceImpl implements BotRegistrationService {
    private final RestTemplate restTemplate;

    @Value("${mediator.bot-registration-path-pattern}")
    private String mediatorBotRegistrationPathPattern;

    @SneakyThrows
    @Override
    public Figure registerBotInSession(String botAddress, int botPort, UUID sessionUUID)  {
        RegistrationRequest registrationRequest = RegistrationRequest.builder()
                .botIp(InetAddress.getByName(botAddress))
                .botPort(botPort)
                .build();

        String url = String.format(mediatorBotRegistrationPathPattern, sessionUUID);
        HttpEntity<RegistrationRequest> request = new HttpEntity<>(registrationRequest);
        log.info("Отправляем запрос на {} для регистрации бота в сессии:", url);
        ResponseEntity<RegistrationResponse> response = restTemplate
                .exchange(url, HttpMethod.POST, request, RegistrationResponse.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new BotRegistrationException();
        }
        RegistrationResponse registrationResponse = response.getBody();

        log.info("Бот успешно зарегистрирован в сессии {}", sessionUUID);
        log.info("Фигура, которой будет ходить бот {}", registrationResponse.getFigure());
        return registrationResponse.getFigure();
    }
}
