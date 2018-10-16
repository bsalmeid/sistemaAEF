/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import Beans.FilhosFuncionariosBeans;
import Beans.*;
import Icones.FormatarICO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bruno
 */
public class TableModelFilhosFuncionarios extends AbstractTableModel {

    private List<FilhosFuncionariosBeans>  linhas;
    
    public TableModelFilhosFuncionarios(){
        linhas = new ArrayList<FilhosFuncionariosBeans>();
    }
    
    public TableModelFilhosFuncionarios(List<FilhosFuncionariosBeans> listaDeFretes){
        linhas = new ArrayList<FilhosFuncionariosBeans>(listaDeFretes);
    }

    private String [] colunas = new String[]{"ID", "Id Func.", "Nome", "CPF","Livro", "Folha" ,"Número", 
            "Data Nascimento", "Cidade", "Estado"};
    
    public final int ID = 0;
    private static final int IDFUNC = 1;
    private static final int NOME = 2;
    private static final int CPF = 3;
    private static final int LIVRO = 4;
    private static final int FOLHA = 5;
    private static final int NUMERO = 6;
    private static final int DATANASCIMENTO = 7;
    private static final int CIDADE = 8;
    private static final int ESTADO = 9;
    
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
            case IDFUNC: 
                return Integer.class;
            case NOME:
                return String.class;
            case CPF:
                return String.class;
            case LIVRO:
                return String.class;
            case FOLHA:
                return String.class;
            case NUMERO:
                return String.class;
            case DATANASCIMENTO:
                return Date.class;
            case CIDADE:
                return String.class;
            case ESTADO:
                return String.class;           
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
        return false;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        //pega o Socio referente a linha especificada;
        FilhosFuncionariosBeans beans = linhas.get(rowIndex);
        switch (columnIndex){
            case ID: 
                return beans.getID();
            case IDFUNC:
                return beans.getId_funcionario();
            case NOME:
                return beans.getNome();
            case CPF:
                return beans.getCPF();
            case LIVRO:
                return beans.getLivro();
            case FOLHA:
                return beans.getFolha();
            case NUMERO:
                return beans.getNumero(); 
            case DATANASCIMENTO:
                return beans.getDataNascimento();
            case CIDADE:
                return beans.getCidade();
            case ESTADO:
                return beans.getEstado();     
            default: 
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        
    }
  
  @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //Pega o sócio referente  a linha especificada
        FilhosFuncionariosBeans beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID:
                beans.setID((Integer) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }    
            fireTableCellUpdated(rowIndex, columnIndex);//notifica a atualização da celula
                       
    }
  
// retorna o socio referente a linha especidicada      
  public FilhosFuncionariosBeans getBeans(int indiceLinha){
      return linhas.get(indiceLinha);
  }  
  
  public void addBeans(FilhosFuncionariosBeans beans){
      //Adiciona registro
      linhas.add(beans);
      // Pega a quantidade de registros e subtrai 1 para
    // achar o último índice. A subtração é necessária
    // porque os índices começam em zero.
    int ultimoIndice = getRowCount() -1;
    
//notifica a mudança
      fireTableRowsInserted(ultimoIndice, ultimoIndice);
  }
    
  public void addLista(List<FilhosFuncionariosBeans> beans){
      //pega o tamanho antigo da tabela, que servirá como indice para o primeiro dos novos registros
      int indice = getRowCount();
      //adiciona os registros
      linhas.addAll(beans);
      //notifica a mudunça
      fireTableRowsInserted(indice, indice + beans.size());
  }  
  
   public List<FilhosFuncionariosBeans> getLista(){
        return linhas;
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
  
    
    
    
