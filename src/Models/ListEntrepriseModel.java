/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Models;

import comptedit_db.EntrepriseRequest;
import comptedit_db.Entreprise;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.plaf.basic.BasicComboBoxUI;

/**
 *
 * @author Flash
 */
public class ListEntrepriseModel extends AbstractListModel<Object> implements AbstractModel {

    private List<Entreprise> le_ = null;
    private EntrepriseRequest er_ = EntrepriseRequest.getInstance();
    private JList l_;
    
    public ListEntrepriseModel()
    {
        le_ = er_.list_entreprise();
    }
    
    @Override
    public int getSize() {
        return le_.size();
    }

    @Override
    public Object getElementAt(int index) {
    Entreprise e = le_.get(index);
    System.out.println("Entreprise : " + le_.get(index).getNameEntreprise());
    System.out.println(le_.size());
    return "- " + e.getNameEntreprise();
    
    }
    
    public void setJList(JList l)
    {
        l_ = l;
    }

    @Override
    public void property_change() {
        System.out.println("List modified");
        le_ = er_.list_entreprise();
        if (l_ != null)
        {
            l_.setModel(this);
        }
        
    }
    
}
