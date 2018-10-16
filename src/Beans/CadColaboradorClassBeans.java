package Beans;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "cad_funcionarios_Classificacao")
public class CadColaboradorClassBeans implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long Id;
    
    @ManyToOne(optional = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcionario", referencedColumnName = "id", nullable = false)
    private CadColaboradorBeans IdColaborador;
    
    @Column(name = "id_funcionario", insertable = false, updatable = false)
    private Integer id_colaborador;
    
    @ManyToOne(optional = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_centro", referencedColumnName = "id", nullable = false)
    private CentroDeResultado IdCentro;
    
    @Column(name = "id_centro", insertable = false, updatable = false)
    private Long id_centro;
    
    @ManyToOne(optional = true ,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fazenda", referencedColumnName = "cad_fazendas_id",nullable = false)
    private PropriedadesBeans IdFazenda;
    
    @Column(name = "id_fazenda", insertable = false, updatable = false)
    private Integer id_fazenda;
    
    @ManyToOne(optional = true ,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plano_contas", referencedColumnName = "pc_id", nullable = false)
    private PlanoContaBeans IdPlanoConta;
    
    @Column(name = "id_plano_contas", insertable = false, updatable = false)
    private Integer id_planoConta;
    
    @Column(name = "valor", nullable = false)
    private Double Valor;

    public CadColaboradorClassBeans() {}

    public CadColaboradorClassBeans(Long Id, Double Valor) {
        this.Id = Id;
        this.Valor = Valor;
    }
    
    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }   
    
    public CadColaboradorBeans getIdColaborador() {
        return IdColaborador;
    }

    public void setIdColaborador(CadColaboradorBeans IdColaborador) {
        this.IdColaborador = IdColaborador;
    }

    public CentroDeResultado getIdCentro() {
        return IdCentro;
    }

    public void setIdCentro(CentroDeResultado IdCentro) {
        this.IdCentro = IdCentro;
    }

    public PropriedadesBeans getIdFazenda() {
        return IdFazenda;
    }

    public void setIdFazenda(PropriedadesBeans IdFazenda) {
        this.IdFazenda = IdFazenda;
    }

    public Double getValor() {
        return Valor;
    }

    public void setValor(Double Valor) {
        this.Valor = Valor;
    }

    public PlanoContaBeans getIdPlanoConta() {
        return IdPlanoConta;
    }

    public void setIdPlanoConta(PlanoContaBeans IdPlanoConta) {
        this.IdPlanoConta = IdPlanoConta;
    }

    public Integer getId_colaborador() {
        return id_colaborador;
    }

    public void setId_colaborador(Integer id_colaborador) {
        this.id_colaborador = id_colaborador;
    }

    public Long getId_centro() {
        return id_centro;
    }

    public void setId_centro(Long id_centro) {
        this.id_centro = id_centro;
    }

    public Integer getId_fazenda() {
        return id_fazenda;
    }

    public void setId_fazenda(Integer id_fazenda) {
        this.id_fazenda = id_fazenda;
    }

    public Integer getId_planoConta() {
        return id_planoConta;
    }

    public void setId_planoConta(Integer id_planoConta) {
        this.id_planoConta = id_planoConta;
    }


    
   
    
}

