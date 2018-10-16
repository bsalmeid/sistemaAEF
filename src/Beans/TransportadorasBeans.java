package Beans;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author agroa
 */
@Entity
@Table(name = "cad_transportadora")
public class TransportadorasBeans implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cad_transp_id")
    private Integer ID;

    @Column(name = "cad_transp_nome")
    private String nome;

    @Column(name = "cad_transp_contato")
    private String contato;

    @Column(name = "cad_transp_tel")
    private String telefone;

    @Column(name = "cad_transp_email")
    private String email;

    @Column(name = "cad_transp_cnpj")
    private String CNPJ;

    @Column(name = "cad_transp_segmento")
    private String Segmento;

    @Column(name = "cad_transp_status", columnDefinition = "BIT", length = 1, nullable = false)
    private Boolean status;

    @OneToMany(mappedBy = "transportadora", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CadPlacaBeans> listPlacas;

    public TransportadorasBeans() {
    }

    public TransportadorasBeans(Integer ID) {
        this.ID = ID;
    }

    public TransportadorasBeans(Integer ID, String nome) {
        this.ID = ID;
        this.nome = nome;
    }

    public void addItem(CadPlacaBeans ii) {
        if (listPlacas == null) {
            listPlacas = new LinkedList<CadPlacaBeans>();
        }
        ii.setTransportadora(this);
        listPlacas.add(ii);
    }

    public List<CadPlacaBeans> getListPlacas() {
        return listPlacas;
    }

    public void setListPlacas(List<CadPlacaBeans> listPlacas) {
        this.listPlacas = listPlacas;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getSegmento() {
        return Segmento;
    }

    public void setSegmento(String Segmento) {
        this.Segmento = Segmento;
    }

    @Override
    public String toString() {
        return nome;
    }

}
