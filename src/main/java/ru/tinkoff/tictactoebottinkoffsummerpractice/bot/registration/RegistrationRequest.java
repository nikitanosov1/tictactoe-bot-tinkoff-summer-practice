package ru.tinkoff.tictactoebottinkoffsummerpractice.bot.registration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.net.InetAddress;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Builder
public class RegistrationRequest {
    @JsonProperty(value = "bot_ip")
    private InetAddress botIp;

    @JsonProperty(value = "bot_port")
    private Integer botPort;
}
