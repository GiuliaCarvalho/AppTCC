package br.com.giulia.webservicetcc.webservices;

import br.com.giulia.webservicetcc.webservices.content.AppSquidexInfo;

public interface CarregaListaServicosListener {
    public void onErro();
    public void onSucesso(AppSquidexInfo appSquidexInfo);
}
