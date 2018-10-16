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
public class ListFrigorificosBeans {

    private Integer ID;
    private String nomeFrigorifico;

    @Override
    public String toString() {
        return getNomeFrigorifico();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getNomeFrigorifico() {
        return nomeFrigorifico;
    }

    public void setNomeFrigorifico(String nomeFrigorifico) {
        this.nomeFrigorifico = nomeFrigorifico;
    }

}
