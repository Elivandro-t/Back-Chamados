package br.com.Initialiizr.Informatica116.domain.validators.validaStatusFechadoETecnicoLogado;

import br.com.Initialiizr.Informatica116.domain.Models.CHAMADO_HARDWARE.Issue;

public interface ValidaStatusFechadoELogado {
    void valid(Issue issue,long usuario);
}
