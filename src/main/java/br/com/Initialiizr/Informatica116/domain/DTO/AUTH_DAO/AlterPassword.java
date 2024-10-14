package br.com.Initialiizr.Informatica116.domain.DTO.AUTH_DAO;

public record AlterPassword(long id, String email, String password, String newPassword) {
}
