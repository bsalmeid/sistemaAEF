
package Controller;

import DAO.EntradaGadoDAO;
import Beans.EntradaGadoBeans;
import Icones.FormatarICO;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class EntradaGadoController {
    EntradaGadoDAO EntradaGadoD;
    EntradaGadoBeans EntradaGadoB;

    public EntradaGadoController(){
        EntradaGadoD = new EntradaGadoDAO();
        EntradaGadoB = new EntradaGadoBeans();
    } 

    public void controlePesquisarRom(EntradaGadoBeans EntGado, DefaultTableModel TbPesquisa){
        EntradaGadoD.PesquisarRomaneio(EntGado, TbPesquisa);
    }
    
    public void controleDeletarNF(EntradaGadoBeans EntGado, DefaultTableModel TbNF) {
        EntradaGadoD.deletarNF(EntGado);
        EntradaGadoD.buscarRomaneio(EntGado, TbNF);
        
    }
    
    public void controleDeletarGTA(EntradaGadoBeans EntGado, DefaultTableModel TbGTA){
        EntradaGadoD.deletarGTA(EntGado);
        EntradaGadoD.preencherTbGTA(EntGado, TbGTA);
    }
    
    public boolean controleInserirGTA(EntradaGadoBeans EntGado){
        
        if(EntGado.getNumGTA() == null){
              int salvar = JOptionPane.showConfirmDialog(null, "Deseja salvar esta entrada de Minuta sem GTA?", "Atenção", JOptionPane.YES_NO_OPTION);
              if (salvar == JOptionPane.YES_OPTION){
                  EntradaGadoD.inserirGTA(EntGado);
                  return true;
              }
        } else {
            if (EntGado.getNumGTA().length() != 6) {
                JOptionPane.showMessageDialog(null, "Verifique o número de digitos do campo 'Nº GTA'!", "Erro", 0, FormatarICO.ICObtnSair());
                return false;
            }
            if (EntGado.getDataGTA().equals(null)) {
                JOptionPane.showMessageDialog(null, "Verifique o campo Data do GTA!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
            }
            if (EntGado.getCodigoROM().equals("")) {
                JOptionPane.showMessageDialog(null, "O campo Nº Romaneio é nulo, selecione um Romaneio Existente ou clique em Criar Novo Romaneio", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
            }
            if (EntGado.getMunicipioGTA().equals("")) {
                JOptionPane.showMessageDialog(null, "Verifique o campo Municipio do GTA", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
            }
            if (EntGado.getFazendaOrigemGTA().equals("")) {
                JOptionPane.showMessageDialog(null, "Verifique o campo Fazenda de Origem do GTA", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
            }
            if (EntGado.getDestinoGTA().equals("-")) {
                JOptionPane.showMessageDialog(null, "Verifique o campo Destino do GTA!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
            }
            if (EntGado.getTipoTransp().equals("-")) {
                JOptionPane.showMessageDialog(null, "Verifique o campo Tipo de Transporte", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
            }
            
            int somaCabecas = Integer.parseInt(EntGado.getFTotal()) + Integer.parseInt(EntGado.getMTotal());
                System.out.println(EntGado.getQuantCab());
                System.out.println(somaCabecas);
            if (Integer.parseInt(EntGado.getQuantGTA()) != (somaCabecas)) {
                JOptionPane.showMessageDialog(null, "Verifique a quantidade de cabeças do GTA e a distribuíção de eras!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
            }
            
        }
        
        EntradaGadoD.inserirGTA(EntGado);
        return true;
    }

    public void controlePmFazendas(){
        EntradaGadoD.pmfazenda();
    }

    public void controleEditarGTA(EntradaGadoBeans entGado){
        EntradaGadoD.editarGTA(entGado);
    }
    
    public Integer controleBuscarUltimo(){
       return EntradaGadoD.buscarUltimo();

    }
    
    public boolean controleEditarNF(EntradaGadoBeans EntGado, DefaultTableModel TbNF){
        
        EntradaGadoD.editarNF(EntGado);
        EntradaGadoD.buscarRomaneio(EntGado, TbNF);
        return true;
    }
    
    public boolean controlePreencherTabelaGTA(EntradaGadoBeans EntGado, DefaultTableModel TbGTA){
        EntradaGadoD.preencherTbGTA(EntGado, TbGTA);
        return true;
    }
    
    public boolean controleBuscarRomaneio(EntradaGadoBeans EntGado, DefaultTableModel TbNF){
        
        if(EntGado.getFazendaEnt().equals("-")){
             JOptionPane.showMessageDialog(null, "Verifique o campo Fazenda de Entrada!", "Erro", 0, FormatarICO.ICObtnSair());
             return false;
        }
        
        EntradaGadoD.buscarRomaneio(EntGado, TbNF);
        return true;
    }

    public boolean controleInserirNF(EntradaGadoBeans EntGado, DefaultTableModel TbNF){
      

            if (EntGado.getDataNF().equals("")) {
                JOptionPane.showMessageDialog(null, "Verifique o campo Data da Nota Fiscal!", "Erro", 0, FormatarICO.ICObtnSair());
                return false;
            }
            if (EntGado.getFazendaEnt().equals("-")) {
                JOptionPane.showMessageDialog(null, "Verifique o campo Fazenda!", "Erro", 0, FormatarICO.ICObtnSair());
                return false;
            }
            if (EntGado.getMotivo().equals("-")) {
                JOptionPane.showMessageDialog(null, "Verifique o campo motivo!", "Erro", 0, FormatarICO.ICObtnSair());
                return false;
            }
            if (EntGado.getMotivo().equals("Compra") & EntGado.getComprador().equals("-")) {
                JOptionPane.showMessageDialog(null, "Verifique o campo comprador!", "Erro", 0, FormatarICO.ICObtnSair());
                return false;
            }
            if (EntGado.getNumNf().equals("")) {
                JOptionPane.showMessageDialog(null, "Verifique o campo Número da Nota Fiscal!", "Erro", 0, FormatarICO.ICObtnSair());
                return false;
            }
            if (EntGado.getQuantNF().equals("")) {
                JOptionPane.showMessageDialog(null, "Verifique o campo Quantidade na Nota Fiscal!", "Erro", 0, FormatarICO.ICObtnSair());
                return false;
            }
            if (EntGado.getValorNF().equals("")) {
                JOptionPane.showMessageDialog(null, "Verifique o campo Valor na Nota Fiscal!", "Erro", 0, FormatarICO.ICObtnSair());
                return false;
            }
            if (EntGado.getInscricao().equals("")) {
                JOptionPane.showMessageDialog(null, "Verifique o campo Inscrição Estadual!", "Erro", 0, FormatarICO.ICObtnSair());
                return false;
            }
            if (EntGado.getProdutor().equals("")) {
                JOptionPane.showMessageDialog(null, "Verifique o campo Produtor!", "Erro", 0, FormatarICO.ICObtnSair());
                return false;
            }
            if (EntGado.getCPFProdutor().equals("")) {
                JOptionPane.showMessageDialog(null, "Verifique o campo CPF/CNPJ do Produtor!", "Erro", 0, FormatarICO.ICObtnSair());
                return false;
            }
            if (EntGado.getFazendaOrigem().equals("")) {
                JOptionPane.showMessageDialog(null, "Verifique o campo Fazenda de Origem!", "Erro", 0, FormatarICO.ICObtnSair());
                return false;
            }

        EntradaGadoD.inserirNF(EntGado, TbNF);
        return true;
    }

}
