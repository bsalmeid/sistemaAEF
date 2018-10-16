/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NFe;

import Beans.PedidosAlmoxarifadoFechamentoItens;
import java.io.Serializable;
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


@Entity
@Table(name = "nfe_produtos")
public class NFeProdutosBeans implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer ID_DB;

    @Column(name = "id_cadastro", nullable = false)
    private Integer ID_Cadastro;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_item_fechamento", referencedColumnName = "id", nullable = true)
    private PedidosAlmoxarifadoFechamentoItens ItemFechamento;
    
    @Column(name = "cProd", nullable = false, length = 120)
    private String cProd;

    @Column(name = "xProd", nullable = false, length = 200)
    private String xProd;

    @Column(name = "NCM", nullable = true, length = 45)
    private String NCM;

    @Column(name = "CEST", nullable = true, length = 45)
    private String CEST;

    @Column(name = "uCom", nullable = true, length = 45)
    private String uCom;

    @Column(name = "qCom", nullable = false, precision = 10, scale = 2)
    private Double qCom;

    @Column(name = "vUnCom", nullable = false, precision = 7, scale = 2)
    private Double vUnCom;

    @Column(name = "vProd", nullable = false, precision = 7, scale = 2)
    private Double vProd;

    @Column(name = "vDesc", nullable = false, precision = 7, scale = 2)
    private Double vDesc;

    @Column(name = "valorUnitFinal", nullable = false, precision = 7, scale = 2)
    private Double valorUnitFinal;

    @Transient
    private String Localizador;

    @Transient
    private Integer Id_localizador;

    @Override
    public String toString() {
        return getcProd() + " - " + getxProd() + " - " + getqCom() + " " + getuCom() + " - " + getvUnCom();
    }

    public Integer getID_Cadastro() {
        return ID_Cadastro;
    }

    public Integer getID_DB() {
        return ID_DB;
    }

    public void setID_DB(Integer ID_DB) {
        this.ID_DB = ID_DB;
    }

    public void setID_Cadastro(Integer ID_Cadastro) {
        this.ID_Cadastro = ID_Cadastro;
    }

    public String getcProd() {
        return cProd;
    }

    public void setcProd(String cProd) {
        this.cProd = cProd;
    }

    public String getxProd() {
        return xProd;
    }

    public void setxProd(String xProd) {
        this.xProd = xProd;
    }

    public String getNCM() {
        return NCM;
    }

    public void setNCM(String NCM) {
        this.NCM = NCM;
    }

    public String getCEST() {
        return CEST;
    }

    public void setCEST(String CEST) {
        this.CEST = CEST;
    }

    public String getuCom() {
        return uCom;
    }

    public void setuCom(String uCom) {
        this.uCom = uCom;
    }

    public Double getqCom() {
        return qCom;
    }

    public void setqCom(Double qCom) {
        this.qCom = qCom;
    }

    public Double getvUnCom() {
        return vUnCom;
    }

    public void setvUnCom(Double vUnCom) {
        this.vUnCom = vUnCom;
    }

    public Double getvProd() {
        return vProd;
    }

    public void setvProd(Double vProd) {
        this.vProd = vProd;
    }

    public Double getvDesc() {
        if (vDesc == null) {
            return 0.0;
        }
        return vDesc;
    }

    public void setvDesc(Double vDesc) {
        this.vDesc = vDesc;
    }

    public Double getValorUnitFinal() {
        if (valorUnitFinal == null) {
            return 0.0;
        }
        return valorUnitFinal;
    }

    public void setValorUnitFinal() {
        if (this.qCom > 0) {
            this.valorUnitFinal = (this.vProd - this.vDesc) / this.getqCom();
        } else {
            this.valorUnitFinal = this.vUnCom;
        }

    }

    public String getLocalizador() {
        return Localizador;
    }

    public void setLocalizador(String Localizador) {
        this.Localizador = Localizador;
    }

    public Integer getId_localizador() {
        return Id_localizador;
    }

    public void setId_localizador(Integer Id_localizador) {
        this.Id_localizador = Id_localizador;
    }

    public PedidosAlmoxarifadoFechamentoItens getItemFechamento() {
        return ItemFechamento;
    }

    public void setItemFechamento(PedidosAlmoxarifadoFechamentoItens ItemFechamento) {
        this.ItemFechamento = ItemFechamento;
    }

    
    
}
