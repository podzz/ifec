/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Models.Table;

import Models.AbstractModel;
import comptedit_db.EntrepriseRequest;
import comptedit_db.Entreprise;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Flash
 */
public class TableEntreprise2 extends AbstractTableModel implements AbstractModel {

    private final String[] entetes = {"Nom", "Description"};
    private final EntrepriseRequest er_ = EntrepriseRequest.getInstance();
    private List<Entreprise> le_ = null;

    public TableEntreprise2() {
        le_ = er_.list_entreprise();
    }

    @Override
    public int getColumnCount() {
        return entetes.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }

    @Override
    public int getRowCount() {
        return le_.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return le_.get(rowIndex).getNameEntreprise();
            case 1:
                return le_.get(rowIndex).getDescriptionEntreprise();
            default:
                return null;
        }
    }

    public void setList() {
        le_ = er_.list_entreprise();
    }
    
    public long getIdAt(int rowIndex)
    {
        return le_.get(rowIndex).getIdEntreprise();
    }
    
    @Override
    public void property_change() {
        setList();
    }
}
