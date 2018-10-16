package TableModel;

import java.math.BigInteger;
import java.util.Date;

public class TbResumoColaborador {

    private Integer ID_COLABORADOR;
    public String STATUS;
    private String NOME;
    private BigInteger CEI;
    private String FAZENDA;
    private String SETOR;
    private String FUNCAO;
    private Double SALARIO;
    private Date DT_ADMISSAO;

    public TbResumoColaborador() {
    }

    public BigInteger getCEI() {
        return CEI;
    }

    public void setCEI(BigInteger CEI) {
        this.CEI = CEI;
    }
    
    public Integer getID_COLABORADOR() {
        return ID_COLABORADOR;
    }

    public void setID_COLABORADOR(Integer ID_COLABORADOR) {
        this.ID_COLABORADOR = ID_COLABORADOR;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getNOME() {
        return NOME;
    }

    public void setNOME(String NOME) {
        this.NOME = NOME;
    }

    public String getFAZENDA() {
        return FAZENDA;
    }

    public void setFAZENDA(String FAZENDA) {
        this.FAZENDA = FAZENDA;
    }

    public String getSETOR() {
        return SETOR;
    }

    public void setSETOR(String SETOR) {
        this.SETOR = SETOR;
    }

    public String getFUNCAO() {
        return FUNCAO;
    }

    public void setFUNCAO(String FUNCAO) {
        this.FUNCAO = FUNCAO;
    }

    public Double getSALARIO() {
        return SALARIO;
    }

    public void setSALARIO(Double SALARIO) {
        this.SALARIO = SALARIO;
    }

    public Date getDT_ADMISSAO() {
        return DT_ADMISSAO;
    }

    public void setDT_ADMISSAO(Date DT_ADMISSAO) {
        this.DT_ADMISSAO = DT_ADMISSAO;
    }
    
    
    
    
}
