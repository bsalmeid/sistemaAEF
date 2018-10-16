/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 *
 * @author agroa
 */
public class ListDeficienciaBeans {

    private Integer Id;
    private String Deficiencia;

    public ListDeficienciaBeans(Integer Id, String Deficiencia) {
        this.Id = Id;
        this.Deficiencia = Deficiencia;
    }

    @Override
    public String toString() {
        return getDeficiencia();
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getDeficiencia() {
        return Deficiencia;
    }

    public void setDeficiencia(String Deficiencia) {
        this.Deficiencia = Deficiencia;
    }

}
