/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import Beans.*;
import Icones.FormatarICO;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bruno
 */
public class TableModelResumoFuncionario extends AbstractTableModel {

    private List<TbResumoFuncionarios> linhas;

    public TableModelResumoFuncionario() {
        linhas = new ArrayList<TbResumoFuncionarios>();
    }

    public TableModelResumoFuncionario(List<TbResumoFuncionarios> listaDeFretes) {
        linhas = new ArrayList<TbResumoFuncionarios>(listaDeFretes);
    }

    private String[] colunas = new String[]{"ID", "Status", "Nome", "CEI", "Cod. Master", "Fazenda",
        "Setor", "Função", "Salário",
        "Admissão", "Descanço", "H. Entr 1º", "H. Saida 1º", "H. Entr 2º", "H. Saida 2º", "Demissao"};

    private static final int ID = 0;
    private static final int STATUS = 1;
    private static final int NOME = 2;
    private static final int CEI = 3;
    private static final int CODIGOMASTER = 4;
    private static final int FAZENDA = 5;
    private static final int SETOR = 6;
    private static final int FUNCAO = 7;
    private static final int SALARIO = 8;
    private static final int ADMISSAO = 9;
    private static final int DSR = 10;
    private static final int HENT1 = 11;
    private static final int HSAIDA1 = 12;
    private static final int HENT2 = 13;
    private static final int HSAIDA2 = 14;
    private static final int DEMISSAO = 15;

    @Override
    public int getRowCount() {
        return linhas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    public void excluirLinha(int rowIndex) {
        linhas.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case ID:
                return Integer.class;
            case STATUS:
                return String.class;
            case NOME:
                return String.class;
            case CEI:
                return BigInteger.class;
            case CODIGOMASTER:
                return Integer.class;
            case FAZENDA:
                return String.class;
            case SETOR:
                return String.class;
            case FUNCAO:
                return String.class;
            case SALARIO:
                return Double.class;
            case ADMISSAO:
                return String.class;
            case DSR:
                return String.class;
            case HENT1:
                return Time.class;
            case HSAIDA1:
                return Time.class;
            case HENT2:
                return Time.class;
            case HSAIDA2:
                return Time.class;
            case DEMISSAO:
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        //pega o Socio referente a linha especificada;
        TbResumoFuncionarios beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID:
                return beans.getID();
            case STATUS:
                return beans.getStatus();
            case NOME:
                return beans.getNome();
            case CEI:
                return beans.getCEI();
            case CODIGOMASTER:
                return beans.getCodigoMaster();
            case FAZENDA:
                return beans.getFazenda();
            case SETOR:
                return beans.getSetor();
            case FUNCAO:
                return beans.getFuncao();
            case SALARIO:
                return beans.getSalario();
            case ADMISSAO:
                return beans.getAdmissao();
            case DSR:
                return beans.getDSR();
            case HENT1:
                return beans.getHEnt1();
            case HSAIDA1:
                return beans.getHSaida1();
            case HENT2:
                return beans.getHEnt2();
            case HSAIDA2:
                return beans.getHSaida2();
            case DEMISSAO:
                return beans.getDemissao();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //Pega o sócio referente  a linha especificada
        TbResumoFuncionarios beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID:
                beans.setID((Integer) aValue);
                break;
       /*     case IDPAGTO:
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
                    beans.setValorClas(new BigDecimal(aValue.toString().replace(",", ".")));
                } catch (Exception e) {
                    beans.setValorClas(new BigDecimal("0"));
                }
                break;
            case IDFAZENDA:
                beans.setIdFazenda((Integer) aValue);
                break;
            case IDAPLICACAO:
                beans.setIdAplicacao((Integer) aValue);
                break;*/
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        fireTableCellUpdated(rowIndex, columnIndex);//notifica a atualização da celula

    }

// retorna o socio referente a linha especidicada      
    public TbResumoFuncionarios getBeans(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addBeans(TbResumoFuncionarios beans) {
        //Adiciona registro
        linhas.add(beans);
        // Pega a quantidade de registros e subtrai 1 para
        // achar o último índice. A subtração é necessária
        // porque os índices começam em zero.
        int ultimoIndice = getRowCount() - 1;

//notifica a mudança
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void addLista(List<TbResumoFuncionarios> beans) {
        //pega o tamanho antigo da tabela, que servirá como indice para o primeiro dos novos registros
        int indice = getRowCount();
        //adiciona os registros
        linhas.addAll(beans);
        //notifica a mudunça
        fireTableRowsInserted(indice, indice + beans.size());
    }

    public void deletarUltimaLinha() {
        int ultimoIndice = getRowCount() - 1;
        linhas.remove(ultimoIndice);
        fireTableRowsDeleted(ultimoIndice, ultimoIndice);
    }

    public void limpar() {
        //remove todos os elementos da lista sócios;
        linhas.clear();
        //notifica a mudança;
        fireTableDataChanged();
    }

}
