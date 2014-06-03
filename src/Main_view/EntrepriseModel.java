/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Main_view;

import BDD.EntrepriseRequest;
import comptedit_db.Entreprise;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Flash
 */
public class EntrepriseModel extends AbstractTableModel implements AbstractModel {

    private final String[] entetes = {"id_entreprise", "Nom", "Description"};
    private final EntrepriseRequest er_ = EntrepriseRequest.getInstance();
    private List<Entreprise> le_ = null;

    public EntrepriseModel() {
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
                return le_.get(rowIndex).getIdEntreprise();
            case 1:
                return le_.get(rowIndex).getNameEntreprise();
            case 2:
                return le_.get(rowIndex).getDescriptionEntreprise();
            default:
                return null;
        }
    }

    public void setList() {
        le_ = er_.list_entreprise();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
    }

    @Override
    public void property_change() {
        System.out.println("fire !");
        setList();
    }
}
