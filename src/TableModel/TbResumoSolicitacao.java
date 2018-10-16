/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import Beans.PedidosAlmoxarifadoSolicitacao;

public class TbResumoSolicitacao {

    private PedidosAlmoxarifadoSolicitacao solic;
    private Long qSolicitacao;
    private Long qCotacao;
    private Long qAnalise;
    private Long qComprada;
    private Long qRecebido;
    private Long qEnviada;
    private Long qCancelado;

    public TbResumoSolicitacao() {

    }

    public TbResumoSolicitacao(PedidosAlmoxarifadoSolicitacao solic, Long qSolicitacao, Long qAnalise, Long qComprada, Long qRecebida, Long qEnviada, Long qCancelado, Long qCotacao) {
        
        this.solic = solic;
        this.qSolicitacao = qSolicitacao;
        this.qAnalise = qAnalise;
        this.qComprada = qComprada;
        this.qEnviada = qEnviada;
        this.qRecebido = qRecebida;
        this.qCancelado = qCancelado;
        this.qCotacao = qCotacao;
    }

    public PedidosAlmoxarifadoSolicitacao getSolic() {
        return solic;
    }

    public void setSolic(PedidosAlmoxarifadoSolicitacao solic) {
        this.solic = solic;
    }

    public Long getqSolicitacao() {
        return qSolicitacao;
    }

    public void setqSolicitacao(Long qSolicitacao) {
        this.qSolicitacao = qSolicitacao;
    }

    public Long getqAnalise() {
        return qAnalise;
    }

    public void setqAnalise(Long qAnalise) {
        this.qAnalise = qAnalise;
    }

    public Long getqComprada() {
        return qComprada;
    }

    public void setqComprada(Long qComprada) {
        this.qComprada = qComprada;
    }

    public Long getqRecebido() {
        return qRecebido;
    }

    public void setqRecebido(Long qRecebido) {
        this.qRecebido = qRecebido;
    }

    public Long getqEnviada() {
        return qEnviada;
    }

    public void setqEnviada(Long qEnviada) {
        this.qEnviada = qEnviada;
    }

    public Long getqCancelado() {
        return qCancelado;
    }

    public void setqCancelado(Long qCancelado) {
        this.qCancelado = qCancelado;
    }

    public Long getqCotacao() {
        return qCotacao;
    }

    public void setqCotacao(Long qCotacao) {
        this.qCotacao = qCotacao;
    }

    
    
}
