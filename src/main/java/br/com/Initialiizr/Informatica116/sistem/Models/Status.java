package br.com.Initialiizr.Informatica116.sistem.Models;

public enum Status {
    AGUARDANDO_TECNICO("aguardando tecnico"),
    EM_ANDAMENTO("em andamento"),
    FEITO("feito"),
    RE_ABERTO("reaberto"),
    AGUARDANDO_VALIDACAO("aguardando validacao"),
    FECHADO("fechado");
String name;
 Status(String name){
    this.name = name;
    }
   public String pegarStatus(String status){
     for(Status status1:Status.values()){
       if(status1.name.equalsIgnoreCase(status)){
           return status1.name;
       }
     }
     return null;
   }
}
