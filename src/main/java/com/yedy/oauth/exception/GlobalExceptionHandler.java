package com.yedy.oauth.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.Principal;
import java.util.UUID;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final SimpMessagingTemplate messagingTemplate;

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus
    public Error handleException(Exception ex) {
        return new Error(500, ex.getMessage());
    }

    @ExceptionHandler(YedyException.class)
    public Error handleAuthException(Exception ex) {
        return new Error(401, ex.getMessage());
    }

    @MessageExceptionHandler
    public void handleWebSocketException(Exception ex, Principal principal) {
        if (principal instanceof UsernamePasswordAuthenticationToken auth) {
            if (auth.getPrincipal() != null && auth.getPrincipal() instanceof UUID userId) {
                messagingTemplate.convertAndSendToUser(userId.toString(), "/error", new Error(501, ex.getMessage()));
            }
        }
    }
}