/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import java.math.BigInteger;
import java.sql.Time;

/**
 *
 * @author agroa
 */
public class TbResumoFuncionarios {

    private Integer ID;
    private String Status;
    private String Nome;
    private BigInteger CEI;
    private Integer CodigoMaster;
    private String Fazenda;
    private String Setor;
    private String Funcao;
    private Double Salario;
    private String Admissao;
    private String DSR;
    private Time HEnt1;
    private Time HSaida1;
    private Time HEnt2;
    private Time HSaida2;
    private String Demissao;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public BigInteger getCEI() {
        return CEI;
    }

    public void setCEI(BigInteger CEI) {
        this.CEI = CEI;
    }

    public Integer getCodigoMaster() {
        return CodigoMaster;
    }

    public void setCodigoMaster(Integer CodigoMaster) {
        this.CodigoMaster = CodigoMaster;
    }

    public String getFazenda() {
        return Fazenda;
    }

    public void setFazenda(String Fazenda) {
        this.Fazenda = Fazenda;
    }

    public String getSetor() {
        return Setor;
    }

    public void setSetor(String Setor) {
        this.Setor = Setor;
    }

    public String getFuncao() {
        return Funcao;
    }

    public void setFuncao(String Funcao) {
        this.Funcao = Funcao;
    }

    public Double getSalario() {
        return Salario;
    }

    public void setSalario(Double Salario) {
        this.Salario = Salario;
    }

    public String getAdmissao() {
        return Admissao;
    }

    public void setAdmissao(String Admissao) {
        this.Admissao = Admissao;
    }

    public String getDSR() {
        return DSR;
    }

    public void setDSR(String DSR) {
        this.DSR = DSR;
    }

    public Time getHEnt1() {
        return HEnt1;
    }

    public void setHEnt1(Time HEnt1) {
        this.HEnt1 = HEnt1;
    }

    public Time getHSaida1() {
        return HSaida1;
    }

    public void setHSaida1(Time HSaida1) {
        this.HSaida1 = HSaida1;
    }

    public Time getHEnt2() {
        return HEnt2;
    }

    public void setHEnt2(Time HEnt2) {
        this.HEnt2 = HEnt2;
    }

    public Time getHSaida2() {
        return HSaida2;
    }

    public void setHSaida2(Time HSaida2) {
        this.HSaida2 = HSaida2;
    }

    public String getDemissao() {
        return Demissao;
    }

    public void setDemissao(String Demissao) {
        this.Demissao = Demissao;
    }

    
    
}
