/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import java.math.BigDecimal;

/**
 *
 * @author Bruno
 */
public class TbPedidosInsumosBeans {
    
    int id;
    int idPedido;
    String nPedido;
    int idInsumo;
    String Insumo;
    String Unidade;
    boolean Status;
    Double Quantidade;
    String Sifra;
    BigDecimal ValorUnit;
    BigDecimal ValorTotal;
    int idCategoria;
    String Categoria;
    int idFazenda;
    String Fazenda;
    Double QtEntregue;
    Double SaldoEntregar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getnPedido() {
        return nPedido;
    }

    public void setnPedido(String nPedido) {
        this.nPedido = nPedido;
    }

    public int getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(int idInsumo) {
        this.idInsumo = idInsumo;
    }

    public String getInsumo() {
        return Insumo;
    }

    public void setInsumo(String Insumo) {
        this.Insumo = Insumo;
    }

    public String getUnidade() {
        return Unidade;
    }

    public void setUnidade(String Unidade) {
        this.Unidade = Unidade;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public Double getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(Double Quantidade) {
        this.Quantidade = Quantidade;
    }

    public String getSifra() {
        return Sifra;
    }

    public void setSifra(String Sifra) {
        this.Sifra = Sifra;
    }

    public BigDecimal getValorUnit() {
        return ValorUnit;
    }

    public void setValorUnit(BigDecimal ValorUnit) {
        this.ValorUnit = ValorUnit;
    }

    public BigDecimal getValorTotal() {
        return ValorTotal;
    }

    public void setValorTotal(BigDecimal ValorTotal) {
        this.ValorTotal = ValorTotal;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public int getIdFazenda() {
        return idFazenda;
    }

    public void setIdFazenda(int idFazenda) {
        this.idFazenda = idFazenda;
    }

    public String getFazenda() {
        return Fazenda;
    }

    public void setFazenda(String Fazenda) {
        this.Fazenda = Fazenda;
    }

    public Double getQtEntregue() {
        return QtEntregue;
    }

    public void setQtEntregue(Double QtEntregue) {
        this.QtEntregue = QtEntregue;
    }

    public Double getSaldoEntregar() {
        return SaldoEntregar;
    }

    public void setSaldoEntregar(Double SaldoEntregar) {
        this.SaldoEntregar = SaldoEntregar;
    }
     
    
}
