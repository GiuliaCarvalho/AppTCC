package br.com.giulia.webservicetcc.webservices.content;

import java.io.Serializable;
import java.util.ArrayList;

public class Data implements Serializable {
    private StringValue nome;
    private StringValue endRua;
    private StringValue endBairro;
    private StringValue endNum;
    private StringValue endComplemento;
    private StringValue tipoServico;

    public StringValue getTelefone() {
        return telefone;
    }

    public void setTelefone(StringValue telefone) {
        this.telefone = telefone;
    }

    public GeolocationValue getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(GeolocationValue enderecos) {
        this.enderecos = enderecos;
    }

    //private ArrayList listaTelefones; mudar p int
    private StringValue telefone;
    private GeolocationValue enderecos;

    public StringValue getNome() {
        return nome;
    }

    public void setNome(StringValue nome) {
        this.nome = nome;
    }

    public StringValue getEndRua() {
        return endRua;
    }

    public void setEndRua(StringValue endRua) {
        this.endRua = endRua;
    }

    public StringValue getEndBairro() {
        return endBairro;
    }

    public void setEndBairro(StringValue endBairro) {
        this.endBairro = endBairro;
    }

    public StringValue getEndNum() {
        return endNum;
    }

    public void setEndNum(StringValue endNum) {
        this.endNum = endNum;
    }

    public StringValue getEndComplemento() {
        return endComplemento;
    }

    public void setEndComplemento(StringValue endComplemento) {
        this.endComplemento = endComplemento;
    }

    public StringValue getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(StringValue tipoServico) {
        this.tipoServico = tipoServico;
    }

}
