
package ComboBoxModel;

import Beans.CidadesBeans;
import java.util.ArrayList;
import java.util.List; 
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
  
public class CbModelCidades extends AbstractListModel<Object> implements ComboBoxModel<Object> {
 
    private List<CidadesBeans> lista;
    private CidadesBeans selectedEstado;
    private final static int FIRSTINDEX = 0;
 
    public CbModelCidades() {
        this.lista = new ArrayList<>();
    }
     
    public CbModelCidades(List<CidadesBeans> listaBeans) {
        this();
        this.lista.addAll(listaBeans);
        if (getSize() > 0) { 
            setSelectedItem(this.lista.get(FIRSTINDEX));
        }
    }
     
    @Override
    public CidadesBeans getElementAt(int index) {
        return lista.get(index);
    }
 
    @Override
    public final int getSize() {
        return lista.size();
    }
 
    @Override
    public CidadesBeans getSelectedItem() {
        return selectedEstado;
    }
 
    @Override
    public final void setSelectedItem(Object anItem) {
        selectedEstado = (CidadesBeans) anItem;
    }
     
    public void addItem(CidadesBeans item) {
        lista.add(item);
        fireIntervalAdded(this, getSize() - 1, getSize() - 1);
        setSelectedItem(lista.get(getSize() - 1));
    }
     
    public void addListaItens(List<CidadesBeans> itens) {
        int primeiraLinha = getSize();
        lista.addAll(itens);
        fireIntervalAdded(this, primeiraLinha, primeiraLinha  + itens.size());
        setSelectedItem(lista.get(getSize() - 1));
    }
     
    public void removeItem() {
        lista.remove(getSelectedItem());
        fireIntervalRemoved(this, FIRSTINDEX, getSize() - 1);
        setSelectedItem(lista.get(FIRSTINDEX));
    }
     
    public void clear() {
        lista.clear();
        fireContentsChanged(this, FIRSTINDEX, getSize() - 1);
    }



}
