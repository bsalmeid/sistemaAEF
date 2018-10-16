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
public class ListStatusAnimalBeans {
 
    private Integer ID;
    private String Status;

    public ListStatusAnimalBeans(Integer ID, String Status) {
        this.ID = ID;
        this.Status = Status;
    }    
    
    @Override
    public String toString(){
     return getStatus();  
    }
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
    
    
    
}
