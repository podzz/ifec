package Models.List;

import Models.AbstractModel;
import Models.ComboBox.ComboBoxExercice;
import Models.Table.TableEntreprise;
import comptedit_db.Entreprise;
import comptedit_db.Exercice;
import comptedit_db.ExerciceRequest;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Flash
 */
public class ListExercice extends AbstractListModel implements AbstractModel, ListModel {

    private JList jl_;
    private List<Exercice> le_;
    private Entreprise selected_;
    public ListExercice(JList jl)
    {
        le_ = new ArrayList<Exercice>();
        jl_ = jl;
        selected_ = null;
    }
    @Override
    public int getSize() {
        return le_.size();
    }

    @Override
    public Object getElementAt(int index) {
        return le_.get(index).getDateBegin() + "->" + le_.get(index).getDateEnd();
    }

    @Override
    public void addListDataListener(ListDataListener l) {
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
    }

    
    
    @Override
    public void property_change() {
        if (selected_ != null)
        {
            le_ = ExerciceRequest.getInstance().list_exercice();
            List<Exercice> refresh_list = new ArrayList<Exercice>();
            for (Exercice e : le_)
               if ((long)e.getEntreprise() == selected_.getIdEntreprise())
                   refresh_list.add(e);
            le_ = refresh_list;
            jl_.updateUI();
        }
    }

    void setSelected(Entreprise e) {
      selected_ = e;
      property_change();
    }
    
    public Exercice getExercice()
    {
        return le_.get(jl_.getSelectedIndex());
    }
    
}
