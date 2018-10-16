/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "remessas")
public class RemessaMercadoriaBeans implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fazenda", nullable = false, referencedColumnName = "cad_fazendas_id")
    private PropriedadesBeans fazendaDestino;
    
    @Column(name = "id_fazenda", nullable = false, insertable = false, updatable = false)
    private Integer idFazenda;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false, referencedColumnName = "id")
    private CadUsuario usuario;

    @Column(name = "data_lancamento", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataLancamento;

    @Column(name = "data_envio", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataEnvio;

    @Column(name = "motorista", nullable = false)
    private String motorista;

    @Column(name = "placa_veiculo", nullable = false)
    private String placaVeiculo;

    @OneToMany(mappedBy = "remessa", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = RemessaMercadoriaItens.class)
    private List<RemessaMercadoriaItens> listaItens;

    @Column(name = "status", nullable = false, columnDefinition = "BIT", length = 1)
    private Boolean status;

    
    
    
    public List<RemessaMercadoriaItens> getListaItens() {
        return listaItens;
    }

    public Integer getIdFazenda() {
        return idFazenda;
    }

    public void setIdFazenda(Integer idFazenda) {
        this.idFazenda = idFazenda;
    }
    
    public void setListaItens(List<RemessaMercadoriaItens> listaItens) {
        this.listaItens = listaItens;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PropriedadesBeans getFazendaDestino() {
        return fazendaDestino;
    }

    public void setFazendaDestino(PropriedadesBeans fazendaDestino) {
        this.fazendaDestino = fazendaDestino;
    }

    public Date getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public CadUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(CadUsuario usuario) {
        this.usuario = usuario;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getMotorista() {
        return motorista;
    }

    public void setMotorista(String motorista) {
        this.motorista = motorista;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Long> getListaObjectItens(){
        List<Long> lista = new ArrayList<>();
        for (RemessaMercadoriaItens i : listaItens) {
            if (i.getItemPedido() != null){
                lista.add(i.getItemPedido().getId());
            }
        }
        return lista;
    }
    
    
    @Override
    public String toString() {
        return "Beans.RemessaMercadoriaBeans[ id=" + id + " ]";
    }

}
