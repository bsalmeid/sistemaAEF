/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import java.math.BigDecimal;

/**
 *
 * @author Bruno
 */
public class TbInsumosAplicBeans {
    
    private int IdDB;
    private int IdInsumo;
    private String Insumo;
    private int IdCategoria;
    private String Categoria;
    private boolean DoseControl;
    private Double DoseMin;
    private Double DoseMax;
    private Double DoseAplic;
    private Double QuantConsumida;
    private Double QuantRetirada;

    public int getIdDB() {
        return IdDB;
    }

    public void setIdDB(int IdDB) {
        this.IdDB = IdDB;
    }
    
    public int getIdInsumo() {
        return IdInsumo;
    }

    public void setIdInsumo(int IdInsumo) {
        this.IdInsumo = IdInsumo;
    }

    public String getInsumo() {
        return Insumo;
    }

    public void setInsumo(String Insumo) {
        this.Insumo = Insumo;
    }

    public int getIdCategoria() {
        return IdCategoria;
    }

    public void setIdCategoria(int IdCategoria) {
        this.IdCategoria = IdCategoria;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public boolean isDoseControl() {
        return DoseControl;
    }

    public void setDoseControl(boolean DoseControl) {
        this.DoseControl = DoseControl;
    }

    public Double getDoseMin() {
        return DoseMin;
    }

    public void setDoseMin(Double DoseMin) {
        this.DoseMin = DoseMin;
    }

    public Double getDoseMax() {
        return DoseMax;
    }

    public void setDoseMax(Double DoseMax) {
        this.DoseMax = DoseMax;
    }

    public Double getDoseAplic() {
        return DoseAplic;
    }

    public void setDoseAplic(Double DoseAplic) {
        this.DoseAplic = DoseAplic;
    }

    public Double getQuantConsumida() {
        return QuantConsumida;
    }

    public void setQuantConsumida(Double QuantConsumida) {
        this.QuantConsumida = QuantConsumida;
    }

    public Double getQuantRetirada() {
        return QuantRetirada;
    }

    public void setQuantRetirada(Double QuantRetirada) {
        this.QuantRetirada = QuantRetirada;
    }
            

    
}
