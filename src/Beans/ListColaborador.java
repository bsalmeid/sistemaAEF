/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;


public class ListColaborador {
  
    private Integer ID;
    private Integer Codigo;
    private Integer idCEI;
    private Integer IdFazenda;
    private Integer IdSetor;
    private Integer IdFuncao;
    private String Nome;

    @Override
    public String toString(){
        return getNome();
    }
    
    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getCodigo() {
        return Codigo;
    }

    public void setCodigo(Integer Codigo) {
        this.Codigo = Codigo;
    }

    public Integer getIdCEI() {
        return idCEI;
    }

    public void setIdCEI(Integer idCEI) {
        this.idCEI = idCEI;
    }

    public Integer getIdFazenda() {
        return IdFazenda;
    }

    public void setIdFazenda(Integer IdFazenda) {
        this.IdFazenda = IdFazenda;
    }

    public Integer getIdSetor() {
        return IdSetor;
    }

    public void setIdSetor(Integer IdSetor) {
        this.IdSetor = IdSetor;
    }

    public Integer getIdFuncao() {
        return IdFuncao;
    }

    public void setIdFuncao(Integer IdFuncao) {
        this.IdFuncao = IdFuncao;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }
    

    

    
}
