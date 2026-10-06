package com.senai.simulado.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErroResposta(LocalDateTime timestamp, int status, String erro, String mensagem,
                           List<String> detalhes) {

    public static ErroResposta de(HttpStatus status, String mensagem) {
        return new ErroResposta(LocalDateTime.now(), status.value(), status.getReasonPhrase(), mensagem, null);
    }

    public static ErroResposta de(HttpStatus status, String mensagem, List<String> detalhes) {
        return new ErroResposta(LocalDateTime.now(), status.value(), status.getReasonPhrase(), mensagem, detalhes);
    }
}