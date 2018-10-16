/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComboBoxModel;

import Beans.ListStatusAnimalBeans;
import DAO.Diversas;
import static GUI.Principal.listStatusAnimal;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author agroa
 */
public class CbModelStatusAnimal extends AbstractListModel<Object> implements ComboBoxModel<Object> {

    private ListStatusAnimalBeans selectedStatus;
    private final static int FIRSTINDEX = 0;
    Diversas DiversasD;

    public CbModelStatusAnimal() {

        DiversasD = new Diversas();
        listStatusAnimal = DiversasD.ListStatusAnimal();
        if (getSize() > 0) {
            setSelectedItem(listStatusAnimal.get(FIRSTINDEX));
        }
    }

    @Override
    public int getSize() {
        return listStatusAnimal.size();
    }

    @Override
    public Object getElementAt(int i) {
        return listStatusAnimal.get(i);
    }

    @Override
    public void setSelectedItem(Object o) {
        selectedStatus = (ListStatusAnimalBeans) o;
    }

    @Override
    public Object getSelectedItem() {
        return selectedStatus;
    }

    public void addBeans(ListStatusAnimalBeans beans) {
        listStatusAnimal.add(beans);
        fireIntervalAdded(this, getSize() - 1, getSize() - 1);
        setSelectedItem(listStatusAnimal.get(getSize() - 1));
    }

    public void addList(List<ListStatusAnimalBeans> list) {
        int primeiraLinha = getSize();
        listStatusAnimal.addAll(list);
        fireIntervalAdded(this, primeiraLinha, primeiraLinha + list.size());
        setSelectedItem(listStatusAnimal.get(getSize() - 1));
    }

    public void remove() {
        listStatusAnimal.remove(getSelectedItem());
        fireIntervalRemoved(this, FIRSTINDEX, getSize() - 1);
        setSelectedItem(listStatusAnimal.get(FIRSTINDEX));
    }

    public void clear() {
        listStatusAnimal.clear();
        fireContentsChanged(this, FIRSTINDEX, getSize() - 1);
    }

    public Integer getID(){
        return selectedStatus.getID();
    }
    
    
}
