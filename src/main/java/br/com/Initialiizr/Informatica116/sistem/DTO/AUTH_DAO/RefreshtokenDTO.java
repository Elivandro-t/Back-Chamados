package br.com.Initialiizr.Informatica116.sistem.DTO.AUTH_DAO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshtokenDTO {
   private  String refreshtoken;
   private  String token;

   public RefreshtokenDTO(String token, String refreshtoken) {
      this.refreshtoken = refreshtoken;
      this.token=token;

   }
}
