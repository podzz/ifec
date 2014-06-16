/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.ComboBox;

import Models.AbstractModel;
import comptedit_db.Entreprise;
import comptedit_db.EntrepriseRequest;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Flash
 */
public class ComboBoxEntreprise implements AbstractModel, ComboBoxModel {

    private final EntrepriseRequest er_;
    private List<Entreprise> lsa_ = null;
    private JComboBox jcb_;
    private Object selected_ = null;

    public ComboBoxEntreprise(JComboBox jcb) {
        er_ = EntrepriseRequest.getInstance();
        jcb_ = jcb;
        lsa_ = er_.list_entreprise();
        if (jcb.getSelectedItem() != null) {
            setSelectedItem(jcb.getSelectedItem());
        }

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
        return lsa_.get(index).getNameEntreprise();
    }

    @Override
    public void addListDataListener(ListDataListener l) {
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
    }

    public long get_id_selected() {
        return lsa_.get(jcb_.getSelectedIndex()).getIdEntreprise();
    }

    @Override
    public void property_change() {
        jcb_.setModel(new ComboBoxEntreprise(jcb_));

    }

}
