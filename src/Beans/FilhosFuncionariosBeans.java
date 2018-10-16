package Beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "cad_funcionarios_filhos")
public class FilhosFuncionariosBeans implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer ID;

    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_funcionario")
    private CadColaboradorBeans id_funcionario;

    @Column(name = "nome")
    private String Nome;

    @Column(name = "cpf")
    private String CPF;

    @Column(name = "livro")
    private String Livro;

    @Column(name = "folha")
    private String Folha;

    @Column(name = "numero_folha")
    private String Numero;

    @Column(name = "data_nascimento")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date DataNascimento;

    @Column(name = "cidade")
    private String Cidade;//Relacional

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "estado")
    private EstadosBeans Estado;//Relacional

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public CadColaboradorBeans getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(CadColaboradorBeans id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public EstadosBeans getEstado() {
        return Estado;
    }

    public void setEstado(EstadosBeans Estado) {
        this.Estado = Estado;
    }
    
    public Date getDataNascimento() {
        return DataNascimento;
    }

    public void setDataNascimento(Date DataNascimento) {
        this.DataNascimento = DataNascimento;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getLivro() {
        return Livro;
    }

    public void setLivro(String Livro) {
        this.Livro = Livro;
    }

    public String getFolha() {
        return Folha;
    }

    public void setFolha(String Folha) {
        this.Folha = Folha;
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String Numero) {
        this.Numero = Numero;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String Cidade) {
        this.Cidade = Cidade;
    }

}
