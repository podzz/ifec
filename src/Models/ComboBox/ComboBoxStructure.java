/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.ComboBox;

import Models.AbstractModel;
import comptedit_db.StructAnalRequest;
import comptedit_db.StructureAnalytique;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Flash
 */
public class ComboBoxStructure implements  AbstractModel, ComboBoxModel {

    private final StructAnalRequest sa_ = StructAnalRequest.getInstance();
    private List<StructureAnalytique> lsa_ = null;
    private JComboBox jcb_;
    private Object selected_ = null;

    public ComboBoxStructure(JComboBox jcb) {
        jcb_ = jcb;
        lsa_ = sa_.list_structanal();
        if (jcb.getSelectedItem() != null)
            setSelectedItem(jcb.getSelectedItem());
        
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selected_ = anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selected_;
    }

    @Override
    public int getSize() {
        return lsa_.size();
    }

    @Override
    public Object getElementAt(int index) {
        return lsa_.get(index).getAliasStructure();
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {
    }

    public void setList() {
       jcb_.setModel(new ComboBoxStructure(jcb_));
    }

    @Override
    public void property_change() {
        System.out.println("fire jcombobox!");
        setList();
        
    }

   
}
