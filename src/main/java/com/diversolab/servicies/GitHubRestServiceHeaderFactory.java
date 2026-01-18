package com.diversolab.servicies;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ClientRequest;
import reactor.core.publisher.Mono;

/**
 * Fábrica de filtros para añadir cabeceras Authorization (Basic Auth)
 * en peticiones realizadas con WebClient.
 */
@Component
public class GitHubRestServiceHeaderFactory {

    @Value("${github.user}")
    private String user;

    @Value("${github.password}")
    private String password;

    /**
     * Devuelve un filtro que añade la cabecera Authorization con credenciales básicas.
     */
    public ExchangeFilterFunction authorizationHeaderFilter() {
        return ExchangeFilterFunction.ofRequestProcessor(request -> {
            String credentials = user + ":" + password;
            String encoded = Base64.getEncoder()
                                   .encodeToString(credentials.getBytes(StandardCharsets.UTF_8));

            ClientRequest newRequest = ClientRequest.from(request)
                    .header("Authorization", "Basic " + encoded)
                    .header("User-Agent", "request")
                    .build();

            return Mono.just(newRequest);
        });
    }
}
