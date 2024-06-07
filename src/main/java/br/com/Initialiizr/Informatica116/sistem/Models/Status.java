package br.com.Initialiizr.Informatica116.sistem.Models;

public enum Status {
    AGUARDANDO_TECNICO("aguardando tecnico"),
    AGUARDANDO_JIRA("aguardando jira"),
    EM_ANDAMENTO("em andamento"),
    FEITO("feito"),
    RE_ABERTO("reaberto"),
    AGUARDANDO_VALIDACAO("aguardando validacao"),
    AGUARDANDO_APROVACAO("aguardando aprovação"),
    RECUSADO("recusado"),
    AGUARDANDO_ORCAMENTO("aguardando orçamento"),
    PERDA_TOTAL("perda total"),
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
