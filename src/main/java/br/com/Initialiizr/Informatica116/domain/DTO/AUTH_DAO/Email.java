package br.com.Initialiizr.Informatica116.domain.DTO.AUTH_DAO;

import jakarta.validation.constraints.NotBlank;

public record Email(@NotBlank String email) {
}
