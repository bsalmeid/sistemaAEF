/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

/**
 *
 * @author agroa
 */
public class TbConsultaCadAlmoxarif {

    private Integer ID;
    private String Codigo;
    private Boolean CodigoCatalogo;
    private String Descricao;
    private String Unidade;
    private String Modelo;

    public String getUnidade() {
        return Unidade;
    }

    public void setUnidade(String Unidade) {
        this.Unidade = Unidade;
    }

    
    
    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String Modelo) {
        this.Modelo = Modelo;
    }

    public Boolean getCodigoCatalogo() {
        return CodigoCatalogo;
    }

    public void setCodigoCatalogo(Boolean CodigoCatalogo) {
        this.CodigoCatalogo = CodigoCatalogo;
    }

}
