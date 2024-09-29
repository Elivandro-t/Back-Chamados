package br.com.Initialiizr.Informatica116.sistem.validators.validaStatusFechadoETecnicoLogado;

import br.com.Initialiizr.Informatica116.sistem.Models.CHAMADO_HARDWARE.Issue;

public interface ValidaStatusFechadoELogado {
    void valid(Issue issue,long usuario);
}
