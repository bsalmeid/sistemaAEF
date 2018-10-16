/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import Icones.FormatarICO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.EventListenerList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bruno
 */
public class TableModelRastreabilidade extends AbstractTableModel {

    private List<TbRastreabilidadeBeans> linhas;

    public TableModelRastreabilidade() {
        linhas = new ArrayList<TbRastreabilidadeBeans>();
    }

    public TableModelRastreabilidade(List<TbRastreabilidadeBeans> listaDeFretes) {
        linhas = new ArrayList<TbRastreabilidadeBeans>(listaDeFretes);
    }

    private String[] colunas = new String[]{"ID", "Data","Id Status","Status", "Motivo", "Nº Compra", "Id Fazenda", "Fazenda", "IDF", "IDE", "Peso", "Classificação",
        "Sexo", "Id Categoria", "Categoria", "Biotipo", "Id Raça", "Raça",
        "IDF Matriz", "IDE Matriz", "Protocolo", "ID Comprador", "Comprador", "Forma Neg", "Preco Unit", "Valor @", "Editado"};

    public final int ID = 0;
    public final int DATA = 1;
    public final int IDSTATUS = 2;
    public final int STATUS = 3;
    public final int MOTIVO = 4;
    public final int COMPRA = 5;
    public final int IDFAZENDA = 6;
    public final int FAZENDA = 7;
    public final int IDF = 8;
    public final int IDE = 9;
    public final int PESO = 10;
    public final int CLASSIFICACAO = 11;
    public final int SEXO = 12;
    public final int IDCATEGORIA = 13;
    public final int CATEGORIA = 14;
    public final int BIOTIPO = 15;
    public final int IDRACA = 16;
    public final int RACA = 17;
    public final int IDFMATRIZ = 18;
    public final int IDEMATRIZ = 19;
    public final int PROTOCOLO = 20;

    public final int IDCOMPRADOR = 21;
    public final int COMPRADOR = 22;
    public final int FORMACOMPRA = 23;
    public final int PRECOUNIT = 24;
    public final int VALORAR = 25;
    public final int EDITADO = 26;

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
            case DATA:
                return String.class;
            case IDSTATUS:
                return Integer.class;
            case STATUS:
                return String.class;    
            case MOTIVO:
                return String.class;
            case COMPRA:
                return Integer.class;
            case IDFAZENDA:
                return Integer.class;
            case FAZENDA:
                return String.class;
            case IDF:
                return String.class;
            case IDE:
                return String.class;
            case PESO:
                return Double.class;
            case CLASSIFICACAO:
                return String.class;
            case SEXO:
                return String.class;
            case IDCATEGORIA:
                return Integer.class;
            case CATEGORIA:
                return String.class;
            case BIOTIPO:
                return String.class;
            case IDRACA:
                return Integer.class;
            case RACA:
                return String.class;
            case IDFMATRIZ:
                return String.class;
            case IDEMATRIZ:
                return String.class;
            case PROTOCOLO:
                return String.class;
            case IDCOMPRADOR:
                return Integer.class;
            case COMPRADOR:
                return String.class;
            case FORMACOMPRA:
                return String.class;
            case PRECOUNIT:
                return Double.class;
            case VALORAR:
                return Double.class;
            case EDITADO:
                return Boolean.class;
            default:
                throw new IndexOutOfBoundsException("column out of bounds");
        }
    }

    @Override
    public boolean isCellEditable(int row, int columnIndex) {
        //Apenas o campo ATIVO será editavel
        // if (columnIndex == DESCCONTA || columnIndex == ID || columnIndex == IDPAGTO ){
        //      return false;
        //}
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //pega o Socio referente a linha especificada;
        TbRastreabilidadeBeans beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID:
                return beans.getID();
            case DATA:
                return beans.getData();
            case IDSTATUS:
                return beans.getIdStatus();
            case STATUS:
                return beans.getStatusAnimal();    
            case MOTIVO:
                return beans.getMotivo();
            case COMPRA:
                return beans.getnCompra();
            case IDFAZENDA:
                return beans.getIdFazenda();
            case FAZENDA:
                return beans.getFazenda();
            case IDF:
                return beans.getIDF();
            case IDE:
                return beans.getIDE();
            case PESO:
                return beans.getPeso();
            case CLASSIFICACAO:
                return beans.getClassificacao();
            case SEXO:
                return beans.getSexo();
            case IDCATEGORIA:
                return beans.getIdCategoria();
            case CATEGORIA:
                return beans.getCategoria();
            case BIOTIPO:
                return beans.getBiotipo();
            case IDRACA:
                return beans.getIdRaca();
            case RACA:
                return beans.getRaca();
            case IDFMATRIZ:
                return beans.getIDFMatriz();
            case IDEMATRIZ:
                return beans.getIDEMatriz();
            case PROTOCOLO:
                return beans.getProtocolo();
            case IDCOMPRADOR:
                return beans.getIdComprador();
            case COMPRADOR:
                return beans.getComprador();
            case FORMACOMPRA:
                return beans.getFormaNeg();
            case PRECOUNIT:
                return beans.getValorUnit();
            case VALORAR:
                return beans.getValorAr();
            case EDITADO:
                return beans.isCampoEditado();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //Pega o sócio referente  a linha especificada
        TbRastreabilidadeBeans beans = linhas.get(rowIndex);

        switch (columnIndex) {
            case ID:
                beans.setID((Integer) aValue);
                break;
            case DATA:
                beans.setData((String) aValue);
                break;
            case MOTIVO:
                beans.setMotivo((String) aValue);
                break;
            case COMPRA:
                beans.setnCompra((Integer) aValue);
                break;
            case IDFAZENDA:
                beans.setIdFazenda((Integer) aValue);
                break;
            case FAZENDA:
                beans.setFazenda((String) aValue);
                break;
            case IDF:
                beans.setIDF((String) aValue);
                break;
            case IDE:
                beans.setIDF((String) aValue);
                break;
            case PESO:
                beans.setPeso((Double) aValue);
                break;
            case SEXO:
                beans.setSexo((String) aValue);
                break;
            case IDCATEGORIA:
                beans.setIdCategoria((Integer) aValue);
                break;
            case CATEGORIA:
                beans.setCategoria((String) aValue);
                break;
            case BIOTIPO:
                beans.setBiotipo((String) aValue);
                break;
            case IDRACA:
                beans.setIdRaca((Integer) aValue);
                break;
            case RACA:
                beans.setRaca((String) aValue);
                break;
            case PROTOCOLO:
                beans.setProtocolo((Integer) aValue);
                break;
            case IDCOMPRADOR:
                beans.setID((Integer) aValue);
                break;
            case COMPRADOR:
                beans.setComprador((String) aValue);
                break;
            case FORMACOMPRA:
                beans.setFormaNeg((String) aValue);
                break;
            case PRECOUNIT:
                beans.setValorUnit((Double) aValue);
                break;
            case VALORAR:
                beans.setValorAr((Double) aValue);
                break;
            case EDITADO:
                beans.setCampoEditado((Boolean) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        fireTableCellUpdated(rowIndex, columnIndex);//notifica a atualização da celula

    }

    public TbRastreabilidadeBeans getBeans(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addBeans(TbRastreabilidadeBeans beans) {
        
        if (linhas.contains(beans) == false){
            linhas.add(beans);
        } else {
            JOptionPane.showMessageDialog(null, "O Brinco de Nº " + beans.getIDF() + " não será lançado pois está duplicado!", "Erro", 0, FormatarICO.ICObtnSair());
        }
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void addLista(List<TbRastreabilidadeBeans> beans) {
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

    public void SetObject(TbRastreabilidadeBeans aValue, int rowIndex) {
        linhas.set(rowIndex, aValue);
        fireTableRowsUpdated(rowIndex, rowIndex);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.IDF;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TableModelRastreabilidade other = (TableModelRastreabilidade) obj;
        if (this.IDF != other.IDF) {
            return false;
        }
        return true;
    }

    

    

    
    
}
