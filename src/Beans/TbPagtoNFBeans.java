/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.math.BigDecimal;

/**
 *
 * @author Bruno
 */
public class TbPagtoNFBeans {
    
    private int ID;
    private int IDPagto;
    private String DtEmissao;
    private String TipoCad;
    private String nCad;
    private String TipoDoc;
    private int NDOC;
    private String Fazenda;
    private int IdFornecedor;
    private String Fornecedor;
    private BigDecimal Valor;
    private int IdTipoDoc;

    public int getIdTipoDoc() {
        return IdTipoDoc;
    }

    public void setIdTipoDoc(int IdTipoDoc) {
        this.IdTipoDoc = IdTipoDoc;
    }
    
    public String getFazenda() {
        return Fazenda;
    }

    public void setFazenda(String Fazenda) {
        this.Fazenda = Fazenda;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDPagto() {
        return IDPagto;
    }

    public void setIDPagto(int IDPagto) {
        this.IDPagto = IDPagto;
    }

    public String getDtEmissao() {
        return DtEmissao;
    }

    public void setDtEmissao(String DtEmissao) {
        this.DtEmissao = DtEmissao;
    }

    public String getTipoDoc() {
        return TipoDoc;
    }

    public void setTipoDoc(String TipoDoc) {
        this.TipoDoc = TipoDoc;
    }

    public int getNDOC() {
        return NDOC;
    }

    public void setNDOC(int NDOC) {
        this.NDOC = NDOC;
    }

    public int getIdFornecedor() {
        return IdFornecedor;
    }

    public void setIdFornecedor(int IdFornecedor) {
        this.IdFornecedor = IdFornecedor;
    }

    public String getFornecedor() {
        return Fornecedor;
    }

    public void setFornecedor(String Fornecedor) {
        this.Fornecedor = Fornecedor;
    }

    public String getTipoCad() {
        return TipoCad;
    }

    public void setTipoCad(String TipoCad) {
        this.TipoCad = TipoCad;
    }

    public String getnCad() {
        return nCad;
    }

    public void setnCad(String nCad) {
        this.nCad = nCad;
    }

    public BigDecimal getValor() {
        return Valor;
    }

    public void setValor(BigDecimal Valor) {
        this.Valor = Valor;
    }


    
    
}
