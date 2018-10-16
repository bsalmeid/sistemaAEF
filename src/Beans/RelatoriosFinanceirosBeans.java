/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;


public class RelatoriosFinanceirosBeans {
    
    private String dtInicial;
    private String dtFinal;
    private String tipoDoc;
    private Integer nDoc;
    private String Favorecido;
    private String SQLPagto;
    private String SQL_Plano_Conta_Completo;
    private String GROUPBY_SQL_PlanoConta;

    public String getGROUPBY_SQL_PlanoConta() {
        return GROUPBY_SQL_PlanoConta;
    }

    public void setGROUPBY_SQL_PlanoConta(String GROUPBY_SQL_PlanoConta) {
        this.GROUPBY_SQL_PlanoConta = GROUPBY_SQL_PlanoConta;
    }
    
    

    public String getSQL_Plano_Conta_Completo() {
        return SQL_Plano_Conta_Completo;
    }

    public void setSQL_Plano_Conta_Completo(String SQL_Plano_Conta_Completo) {
        this.SQL_Plano_Conta_Completo = SQL_Plano_Conta_Completo;
    }

    public String getSQLPagto() {
        return SQLPagto;
    }

    public void setSQLPagto(String SQLPagto) {
        this.SQLPagto = SQLPagto;
    }
    
    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public Integer getnDoc() {
        return nDoc;
    }

    public void setnDoc(Integer nDoc) {
        this.nDoc = nDoc;
    }

    public String getFavorecido() {
        return Favorecido;
    }

    public void setFavorecido(String Favorecido) {
        this.Favorecido = Favorecido;
    }
    
    

    public String getDtInicial() {
        return dtInicial;
    }

    public void setDtInicial(String dtInicial) {
        this.dtInicial = dtInicial;
    }

    public String getDtFinal() {
        return dtFinal;
    }

    public void setDtFinal(String dtFinal) {
        this.dtFinal = dtFinal;
    }

   
    
    
}
