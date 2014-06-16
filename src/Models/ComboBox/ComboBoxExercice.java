/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.ComboBox;

import Models.AbstractModel;
import comptedit_db.Entreprise;
import comptedit_db.EntrepriseRequest;
import comptedit_db.Exercice;
import comptedit_db.ExerciceRequest;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Flash
 */
public class ComboBoxExercice implements AbstractModel, ComboBoxModel {

    private final ExerciceRequest er_;
    private List<Exercice> lsa_ = null;
    private JComboBox jcb_;
    private Object selected_ = null;

    public ComboBoxExercice(JComboBox jcb) {
        er_ = ExerciceRequest.getInstance();
        jcb_ = jcb;
        lsa_ = er_.list_exercice();
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
        String name_entreprise = EntrepriseRequest.getInstance().get_entreprise_by_id(lsa_.get(index).getEntreprise()).getNameEntreprise();
        return name_entreprise + " : " + lsa_.get(index).getDateBegin().toString() + "->" + lsa_.get(index).getDateEnd().toString();
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {
    }

    public long get_id_selected() {
        return lsa_.get(jcb_.getSelectedIndex()).getIdExercice();
    }

    public void setList() {
    }

    @Override
    public void property_change() {
        jcb_.setModel(new ComboBoxExercice(jcb_));
    }

}
