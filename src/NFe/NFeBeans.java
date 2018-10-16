/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NFe;

import Beans.PedidosAlmoxarifadoFechamento;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author agroa
 */
@Entity
@Table(name = "nfe_xml")
//, uniqueConstraints = {@UniqueConstraint(columnNames={"nNF", "CNPJ"})}
public class NFeBeans implements Serializable{
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer ID;
    
    @Column(name = "id_fornecedor", nullable = false)
    private Integer ID_Fornecedor;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_fechamento", referencedColumnName = "id", nullable = true)
    private PedidosAlmoxarifadoFechamento fechamento;
    
    @Column(name = "chNFe", nullable = true, length = 250)
    private  String chNFe;
    
    @Column(name = "cUF", nullable = true)
    private Integer cUF;
    
    @Column(name = "cNF", nullable = true)
    private Integer cNF;
    
    @Column(name = "natOp", nullable = true, length = 180)
    private String natOp;
    
    @Column(name = "nNF", nullable = true)
    private Integer nNF;
    
    @Column(name = "dhEmi", nullable = false, length = 80)
    private String dhEmi;
    
    @Column(name = "cMunFG", nullable = true)
    private Integer cMunFG;
    
    @Column(name = "CNPJ", nullable = false, length = 120)
    private String CNPJ;
    
    @Column(name = "xNome", nullable = true, length = 250)
    private String xNome;
    
    @Column(name = "xFant", nullable = true, length = 250)
    private String xFant;
    
    @Column(name = "xLgr", nullable = true, length = 250)
    private String xLgr;
    
    @Column(name = "nro", nullable = true, length = 80)
    private String nro;
    
    @Column(name = "xBairro", nullable =  true, length = 200)
    private String xBairro;
    
    @Column(name = "cMun", nullable = true)
    private Integer cMun;
    
    @Column(name = "xMun", nullable = true, length = 120)
    private String xMun;
    
    @Column(name = "UF", nullable = true, length = 80 )
    private String UF;
    
    @Column(name = "IEDest", nullable = true, length = 100)
    private String IEDest;
    
    @Column(name = "vNF", nullable = false, precision = 7, scale = 2)
    private Double vNF;
    
    @Column(name = "nDup", nullable = true, length = 80)
    private String nDup;
    
    @Column(name = "dVenc", nullable = true, length = 80)
    private String dVenc;
    
    @Column(name = "vDup", nullable = true, precision = 7, scale = 2)
    private Double vDup;
    
    @Column(name = "arq_xml", nullable = true, length = 255)
    private String Caminho;
    
    
    @Transient
    private List<NFeProdutosBeans> listProdutos;
    @Transient
    private Boolean Selecionado;

    public NFeBeans() {
    
    }
    

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getID_Fornecedor() {
        return ID_Fornecedor;
    }

    public void setID_Fornecedor(Integer ID_Fornecedor) {
        this.ID_Fornecedor = ID_Fornecedor;
    }
    
    public Integer getcUF() {
        return cUF;
    }

    public void setcUF(Integer cUF) {
        this.cUF = cUF;
    }

    public Integer getcNF() {
        return cNF;
    }

    public void setcNF(Integer cNF) {
        this.cNF = cNF;
    }

    public String getNatOp() {
        return natOp;
    }

    public void setNatOp(String natOp) {
        this.natOp = natOp;
    }

    public Integer getnNF() {
        return nNF;
    }

    public void setnNF(Integer nNF) {
        this.nNF = nNF;
    }

    public String getDhEmi() {
        return dhEmi;
    }

    public void setDhEmi(String dhEmi) {
        this.dhEmi = dhEmi;
    }
    
    public Integer getcMunFG() {
        return cMunFG;
    }

    public void setcMunFG(Integer cMunFG) {
        this.cMunFG = cMunFG;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getxNome() {
        return xNome;
    }

    public void setxNome(String xNome) {
        this.xNome = xNome;
    }

    public String getxFant() {
        return xFant;
    }

    public void setxFant(String xFant) {
        this.xFant = xFant;
    }

    public String getxLgr() {
        return xLgr;
    }

    public void setxLgr(String xLgr) {
        this.xLgr = xLgr;
    }

    public String getNro() {
        return nro;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

    public String getxBairro() {
        return xBairro;
    }

    public void setxBairro(String xBairro) {
        this.xBairro = xBairro;
    }

    public Integer getcMun() {
        return cMun;
    }

    public void setcMun(Integer cMun) {
        this.cMun = cMun;
    }

    public String getxMun() {
        return xMun;
    }

    public void setxMun(String xMun) {
        this.xMun = xMun;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public String getIEDest() {
        return IEDest;
    }

    public void setIEDest(String IEDest) {
        this.IEDest = IEDest;
    }

    public String getChNFe() {
        return chNFe;
    }

    public void setChNFe(String chNFe) {
        this.chNFe = chNFe;
    }

    public Double getvNF() {
        return vNF;
    }

    public void setvNF(Double vNF) {
        this.vNF = vNF;
    }

    public String getnDup() {
        return nDup;
    }

    public void setnDup(String nDup) {
        this.nDup = nDup;
    }

    public String getdVenc() {
        return dVenc;
    }

    public void setdVenc(String dVenc) {
        this.dVenc = dVenc;
    }

    public Double getvDup() {
      if (vDup == null){
          return 0.0;
      }
        return vDup;
    }

    public void setvDup(Double vDup) {
        this.vDup = vDup;
    }

    public String getCaminho() {
        return Caminho;
    }

    public void setCaminho(String Caminho) {
        this.Caminho = Caminho;
    }

    public List<NFeProdutosBeans> getListProdutos() {
        return listProdutos;
    }

    public void setListProdutos(List<NFeProdutosBeans> listProdutos) {
        this.listProdutos = listProdutos;
    }
    
    public PedidosAlmoxarifadoFechamento getFechamento() {
        return fechamento;
    }

    public void setFechamento(PedidosAlmoxarifadoFechamento fechamento) {
        this.fechamento = fechamento;
    }
    
    public Boolean getSelecionado() {
        return Selecionado;
    }

    public void setSelecionado(Boolean Selecionado) {
        this.Selecionado = Selecionado;
    }
    

    
}
