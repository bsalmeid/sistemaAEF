
package Controller;

import Beans.EscalasAbateBeans;
import DAO.EscalasAbateDAO;
import javax.swing.table.DefaultTableModel;


public class EscalasAbateController {
    
    EscalasAbateDAO EscalasD;
    EscalasAbateBeans EscalasB;
    
    public EscalasAbateController(){
        EscalasD = new EscalasAbateDAO();
        EscalasB = new EscalasAbateBeans();
    }
    
    public void controleInserirEscala (EscalasAbateBeans Escalas, DefaultTableModel TbEscalas){
        
        EscalasD.inserirEscala(Escalas, TbEscalas);
        
        
    }
    
    public void controlePreencherTabela (EscalasAbateBeans Escalas, DefaultTableModel TbEscalas, String SQLWhere){
        
            
        EscalasD.preencherTabela(Escalas, TbEscalas, SQLWhere);
    }
    
    public void controleEditarEscala (EscalasAbateBeans Escala){
        EscalasD.editarEscala(Escala);
    }
    
    public void controleExcuirEscala (EscalasAbateBeans Escala){
        EscalasD.excluirEscala(Escala);
    }
    
    
    
}
