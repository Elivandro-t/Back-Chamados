package br.com.Initialiizr.Informatica116.sistem.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AlterPassword(long id, String email, String password, String newPassword) {
}
