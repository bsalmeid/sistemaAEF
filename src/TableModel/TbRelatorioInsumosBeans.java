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
public class TbRelatorioInsumosBeans {
    
    Integer IdEntrada;
     boolean Status;
    Integer idNF;
    Integer IdItemPedido;
    String DataEntrada;
    Integer idCategoria;
    String Categoria;
    Integer IdInsumo;
    String Insumo;
    Double Quantidade;
    String Motivo;
    String Fornecedor;
    Integer idPedido;
    String Pedido;
    Integer nDoc;
    String Placa;
    BigDecimal VlrUnit;
    BigDecimal VlrTotal;

    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String Placa) {
        this.Placa = Placa;
    }

    
    
    public BigDecimal getVlrUnit() {
        return VlrUnit;
    }

    public void setVlrUnit(BigDecimal VlrUnit) {
        this.VlrUnit = VlrUnit;
    }

    public BigDecimal getVlrTotal() {
        return VlrTotal;
    }

    public void setVlrTotal(BigDecimal VlrTotal) {
        this.VlrTotal = VlrTotal;
    }

    
    
    public Integer getIdEntrada() {
        return IdEntrada;
    }

    public void setIdEntrada(Integer IdEntrada) {
        this.IdEntrada = IdEntrada;
    }

    public Integer getIdNF() {
        return idNF;
    }

    public void setIdNF(Integer idNF) {
        this.idNF = idNF;
    }

    public Integer getIdItemPedido() {
        return IdItemPedido;
    }

    public void setIdItemPedido(Integer IdItemPedido) {
        this.IdItemPedido = IdItemPedido;
    }

    public String getDataEntrada() {
        return DataEntrada;
    }

    public void setDataEntrada(String DataEntrada) {
        this.DataEntrada = DataEntrada;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public Integer getIdInsumo() {
        return IdInsumo;
    }

    public void setIdInsumo(Integer IdInsumo) {
        this.IdInsumo = IdInsumo;
    }

    public String getInsumo() {
        return Insumo;
    }

    public void setInsumo(String Insumo) {
        this.Insumo = Insumo;
    }

    public Double getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(Double Quantidade) {
        this.Quantidade = Quantidade;
    }

    public String getMotivo() {
        return Motivo;
    }

    public void setMotivo(String Motivo) {
        this.Motivo = Motivo;
    }

    public String getFornecedor() {
        return Fornecedor;
    }

    public void setFornecedor(String Fornecedor) {
        this.Fornecedor = Fornecedor;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public String getPedido() {
        return Pedido;
    }

    public void setPedido(String Pedido) {
        this.Pedido = Pedido;
    }

    public Integer getnDoc() {
        return nDoc;
    }

    public void setnDoc(Integer nDoc) {
        this.nDoc = nDoc;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }
    
    
    
    
}
