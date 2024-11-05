package br.com.Initialiizr.Informatica116.sistem.DTO.AUTH_DAO;

import java.time.LocalDateTime;

public record UserActiveDTO(boolean online, LocalDateTime timestamp) {
}
