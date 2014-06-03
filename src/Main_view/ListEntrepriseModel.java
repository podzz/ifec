/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Main_view;

import BDD.EntrepriseRequest;
import comptedit_db.Entreprise;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author Flash
 */
public class ListEntrepriseModel extends AbstractListModel<Object> implements AbstractModel {

    private List<Entreprise> le_ = null;
    private EntrepriseRequest er_ = EntrepriseRequest.getInstance();
    
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
        return le_.get(index).getNameEntreprise() + "-" + le_.get(index).getDescriptionEntreprise();
    }

    @Override
    public void property_change() {
        le_ = er_.list_entreprise();
    }
    
}
