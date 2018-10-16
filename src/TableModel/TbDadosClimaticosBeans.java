/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import java.sql.Time;

/**
 *
 * @author agroa
 */
public class TbDadosClimaticosBeans {
    
    private Integer ID;
    private Integer ID_OS;
    private Integer ID_OP;
    private String Data;
    private Time hora;
    private Double Vento;
    private Double Umidade;
    private Double Temperatura;
    private Double Chuva;

    public Integer getID_OS() {
        return ID_OS;
    }

    public void setID_OS(Integer ID_OS) {
        this.ID_OS = ID_OS;
    }

    public Integer getID_OP() {
        return ID_OP;
    }

    public void setID_OP(Integer ID_OP) {
        this.ID_OP = ID_OP;
    }

    
    
    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getData() {
        return Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public Double getVento() {
        return Vento;
    }

    public void setVento(Double Vento) {
        this.Vento = Vento;
    }

    public Double getUmidade() {
        return Umidade;
    }

    public void setUmidade(Double Umidade) {
        this.Umidade = Umidade;
    }

    public Double getTemperatura() {
        return Temperatura;
    }

    public void setTemperatura(Double Temperatura) {
        this.Temperatura = Temperatura;
    }

    public Double getChuva() {
        return Chuva;
    }

    public void setChuva(Double Chuva) {
        this.Chuva = Chuva;
    }
    
}
