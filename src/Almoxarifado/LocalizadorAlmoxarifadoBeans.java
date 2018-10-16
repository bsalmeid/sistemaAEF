package Almoxarifado;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "cad_almoxarifado_localizador")
public class LocalizadorAlmoxarifadoBeans implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer ID;
    
    @ManyToOne(optional = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "id_almoxarifado")
    private CadAlmoxarifadoBeans id_almoxarifado;
    
    @Column(name = "descricao")
    private String Descricao;
    
    @Column(name = "status",length = 1, columnDefinition = "BIT" , nullable = false)
    private Boolean Status;
    
    @Transient
    private Integer Id_Almoxarifado;

    @Override
    public String toString() {
        return Descricao;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getId_Almoxarifado() {
        return Id_Almoxarifado;
    }

    public void setId_Almoxarifado(Integer Id_Almoxarifado) {
        this.Id_Almoxarifado = Id_Almoxarifado;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public CadAlmoxarifadoBeans getId_almoxarifado() {
        return id_almoxarifado;
    }

    public void setId_almoxarifado(CadAlmoxarifadoBeans id_almoxarifado) {
        this.id_almoxarifado = id_almoxarifado;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean Status) {
        this.Status = Status;
    }
    
}
