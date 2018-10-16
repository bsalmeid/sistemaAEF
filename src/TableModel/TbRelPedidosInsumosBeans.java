
package TableModel;

import java.math.BigDecimal;


public class TbRelPedidosInsumosBeans {
    
    boolean Status;
    int id;
    String DataEmissao;
    String Vencimento;
    String nPedido;
    String Safra;
    int idFornecedor;
    String Fornecedor;
    int idFazenda;
    String Fazenda; 
    String Moeda;
    String Sifra;
    BigDecimal ValorPedido;
    BigDecimal ValorInsumos;
    BigDecimal ValorAgendadoMoeda;
    BigDecimal ValorPagoMoeda;
    BigDecimal ValorPagoReais;
    BigDecimal ValorNF;
    String StatusPagto;

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataEmissao() {
        return DataEmissao;
    }

    public void setDataEmissao(String DataEmissao) {
        this.DataEmissao = DataEmissao;
    }

    public String getVencimento() {
        return Vencimento;
    }

    public void setVencimento(String Vencimento) {
        this.Vencimento = Vencimento;
    }

    public String getnPedido() {
        return nPedido;
    }

    public void setnPedido(String nPedido) {
        this.nPedido = nPedido;
    }

    public String getSafra() {
        return Safra;
    }

    public void setSafra(String Safra) {
        this.Safra = Safra;
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getFornecedor() {
        return Fornecedor;
    }

    public void setFornecedor(String Fornecedor) {
        this.Fornecedor = Fornecedor;
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

    public String getMoeda() {
        return Moeda;
    }

    public void setMoeda(String Moeda) {
        this.Moeda = Moeda;
    }

    public String getSifra() {
        return Sifra;
    }

    public void setSifra(String Sifra) {
        this.Sifra = Sifra;
    }

    public BigDecimal getValorPedido() {
        return ValorPedido;
    }

    public void setValorPedido(BigDecimal ValorPedido) {
        this.ValorPedido = ValorPedido;
    }

    public BigDecimal getValorInsumos() {
        return ValorInsumos;
    }

    public void setValorInsumos(BigDecimal ValorInsumos) {
        this.ValorInsumos = ValorInsumos;
    }

    public BigDecimal getValorAgendadoMoeda() {
        return ValorAgendadoMoeda;
    }

    public void setValorAgendadoMoeda(BigDecimal ValorAgendadoMoeda) {
        this.ValorAgendadoMoeda = ValorAgendadoMoeda;
    }

    public BigDecimal getValorPagoMoeda() {
        return ValorPagoMoeda;
    }

    public void setValorPagoMoeda(BigDecimal ValorPagoMoeda) {
        this.ValorPagoMoeda = ValorPagoMoeda;
    }

    public BigDecimal getValorPagoReais() {
        return ValorPagoReais;
    }

    public void setValorPagoReais(BigDecimal ValorPagoReais) {
        this.ValorPagoReais = ValorPagoReais;
    }

    public BigDecimal getValorNF() {
        return ValorNF;
    }

    public void setValorNF(BigDecimal ValorNF) {
        this.ValorNF = ValorNF;
    }

    public String getStatusPagto() {
        return StatusPagto;
    }

    public void setStatusPagto(String StatusPagto) {
        this.StatusPagto = StatusPagto;
    }

    
    
    
    
    
    
}
