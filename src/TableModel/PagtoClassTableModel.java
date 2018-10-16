/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import Beans.TbPagtoClassBeans;
import Beans.*;
import Icones.FormatarICO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bruno
 */
public class PagtoClassTableModel extends AbstractTableModel {

    private List<TbPagtoClassBeans>  linhas;
    
    public PagtoClassTableModel(){
        linhas = new ArrayList<TbPagtoClassBeans>();
    }
    
    public PagtoClassTableModel(List<TbPagtoClassBeans> listaDeFretes){
        linhas = new ArrayList<TbPagtoClassBeans>(listaDeFretes);
    }

    private String [] colunas = new String[]{"ID", "ID Pagto", "Atividade", "Tipo Despesa","IdPlano" ,"Conta", 
            "Descrição da Conta", "Fazenda", "Nº Frota", 
            "Descrição da Depesa" ,"Valor", "idFazenda", "idInventario"};
    
    private static final int ID = 0;
    private static final int IDPAGTO = 1;
    private static final int ATIVIDADE = 2;
    private static final int TIPODESPESA = 3;
    private static final int IDPLANO = 4;
    private static final int PLANOCONTA = 5;
    private static final int DESCCONTA = 6;
    private static final int FAZ = 7;
    private static final int APLICACAO = 8;
    private static final int DESCRICAO = 9;
    private static final int VALOR = 10;
    private static final int IDFAZENDA = 11;   
    private static final int IDAPLICACAO = 12;
    
    @Override
    public int getRowCount(){
        return linhas.size();
    }
    @Override
    public int getColumnCount(){
        return colunas.length;
    }
    
    @Override
    public String getColumnName(int columnIndex){
        return colunas[columnIndex]; 
    }
    
    public void excluirLinha(int rowIndex){
        linhas.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
    
    
    @Override
    public Class<?> getColumnClass(int columnIndex){
        switch (columnIndex){
            case ID: 
                return Integer.class;
            case IDPAGTO: 
                return Integer.class;
            case ATIVIDADE:
                return String.class;
            case TIPODESPESA:
                return String.class;
            case IDPLANO:
                return Integer.class;
            case PLANOCONTA:
                return Integer.class;
            case DESCCONTA:
                return String.class;
            case FAZ:
                return String.class;
            case APLICACAO:
                return String.class;
            case DESCRICAO:
                return String.class;    
            case VALOR:
                return String.class;
            case IDFAZENDA:
                return Integer.class;
            case IDAPLICACAO:
                return Integer.class;    
                
            default: 
                throw new IndexOutOfBoundsException("column out of bounds");
        }
    }
    
    @Override
    public boolean isCellEditable(int row, int columnIndex) {
        //Apenas o campo ATIVO será editavel
      //  if (columnIndex == DESCCONTA || columnIndex == ID || columnIndex == IDPAGTO ){
      //      return false;
       // }
        return true;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        //pega o Socio referente a linha especificada;
        TbPagtoClassBeans beans = linhas.get(rowIndex);
        switch (columnIndex){
            case ID: 
                return beans.getID();
            case IDPAGTO:
                return beans.getIDPagto();
            case ATIVIDADE:
                return beans.getAtividade();
            case TIPODESPESA:
                return beans.getTipoDespesa();
            case IDPLANO:
                return beans.getIdPlanoContas();
            case PLANOCONTA:
                return beans.getPlanoContas();
            case DESCCONTA:
                return beans.getDesConta(); 
            case FAZ:
                return beans.getFazenda();
            case APLICACAO:
                return beans.getnFrota();
            case DESCRICAO:
                return beans.getDescricao();    
            case VALOR:
                return beans.getValorClas();    
            case IDFAZENDA:
                return beans.getIdFazenda();    
            case IDAPLICACAO:
                return beans.getIdAplicacao();    
            default: 
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        
    }
  
  @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //Pega o sócio referente  a linha especificada
        TbPagtoClassBeans beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID:
                beans.setID((Integer) aValue);
                break;
            case IDPAGTO:
                beans.setIDPagto((Integer) aValue);
                break;
            case ATIVIDADE:
                beans.setAtividade((String) aValue);
                break;
            case TIPODESPESA:
                beans.setTipoDespesa((String) aValue);
                break;
            case IDPLANO:
                beans.setIdPlanoContas((Integer) aValue);
                break;
            case PLANOCONTA:
                beans.setPlanoContas((Integer) aValue);
                break;
            case DESCCONTA:
                beans.setDesConta((String) aValue);
                break;    
            case FAZ:
                beans.setFazenda(aValue.toString());
                break;
            case APLICACAO:
                beans.setnFrota((String) aValue);
                break;
            case DESCRICAO:
                beans.setDescricao((String) aValue);
                break;    
            case VALOR:
                try {
                    beans.setValorClas(new BigDecimal(aValue.toString().replace(",",".")));
                } catch (Exception e) {
                    beans.setValorClas(new BigDecimal("0"));
                }
                break;
            case IDFAZENDA:
                beans.setIdFazenda((Integer) aValue);
                break;
            case IDAPLICACAO:
                beans.setIdAplicacao((Integer) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }    
            fireTableCellUpdated(rowIndex, columnIndex);//notifica a atualização da celula
                       
    }
  
// retorna o socio referente a linha especidicada      
  public TbPagtoClassBeans getBeans(int indiceLinha){
      return linhas.get(indiceLinha);
  }  
  
  public void addBeans(TbPagtoClassBeans beans){
      //Adiciona registro
      linhas.add(beans);
      // Pega a quantidade de registros e subtrai 1 para
    // achar o último índice. A subtração é necessária
    // porque os índices começam em zero.
    int ultimoIndice = getRowCount() -1;
    
//notifica a mudança
      fireTableRowsInserted(ultimoIndice, ultimoIndice);
  }
  
  public void addLista(List<TbPagtoClassBeans> beans){
      //pega o tamanho antigo da tabela, que servirá como indice para o primeiro dos novos registros
      int indice = getRowCount();
      //adiciona os registros
      linhas.addAll(beans);
      //notifica a mudunça
      fireTableRowsInserted(indice, indice + beans.size());
  }  
  
  public void deletarUltimaLinha(){
      int ultimoIndice = getRowCount() -1;
      linhas.remove(ultimoIndice);
      fireTableRowsDeleted(ultimoIndice, ultimoIndice);
  }
  
  
  public void limpar(){
      //remove todos os elementos da lista sócios;
      linhas.clear();
      //notifica a mudança;
      fireTableDataChanged();
  }
 

  
  
}
  
    
    
    
