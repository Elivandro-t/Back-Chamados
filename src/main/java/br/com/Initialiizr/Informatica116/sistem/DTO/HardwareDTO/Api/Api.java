package br.com.Initialiizr.Informatica116.sistem.DTO.HardwareDTO.Api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Api {
   private String number;
   private String textMessage;

   public Api(String numero,String txt){
      this.number = numero;
      this.textMessage = txt;
   }
}
