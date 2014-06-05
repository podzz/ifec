/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Table;

import Models.AbstractModel;
import comptedit_db.Entreprise;
import comptedit_db.EntrepriseRequest;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import org.jdesktop.swingx.JXSearchField;

/**
 *
 * @author Flash
 */
public class TableEntreprise extends AbstractTableModel implements AbstractModel {

    private final String[] entetes = {"Nom"};
    private final EntrepriseRequest er_ = EntrepriseRequest.getInstance();
    private List<Entreprise> le_ = null;
    private JXSearchField search_ = null;
    private JTable table_ = null;

    public TableEntreprise() {
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
                return  le_.get(rowIndex).getNameEntreprise();
            default:
                return null;
        }
    }

    public void setSearch(JXSearchField search) {

        search_ = search;
        search_.setCancelAction(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                search_.setText("");
                setList();
                table_.revalidate();
                table_.repaint();
            }
        });
    }

    public void setTable(JTable table) {
        table_ = table;
    }

    public void setList() {
        if (search_ == null) {
            le_ = er_.list_entreprise();
        } else {
            le_ = er_.list_entreprise_begin_with(search_.getText());
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
    }

    @Override
    public void property_change() {
        setList();
        if (table_ != null) {
            table_.revalidate();
            table_.repaint();
        }
    }
}
