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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.jdesktop.swingx.JXSearchField;

/**
 *
 * @author Flash
 */
public class TableEntreprise implements TableModel, AbstractModel {

    private final String[] entetes = {"Nom"};
    private final EntrepriseRequest er_ = EntrepriseRequest.getInstance();
    private List<Entreprise> le_ = null;
    private JXSearchField search_ = null;
    private JTable table_ = null;

    public TableEntreprise(JTable table) {
        table_ = table;
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
                return le_.get(rowIndex);
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
        
        search_.addKeyListener(new KeyAdapter() {

          
            @Override
            public void keyReleased(KeyEvent e) {
                table_.getSelectionModel().clearSelection();
            }
        });

    }

    public void setList() {
        if (search_ == null) {
            le_ = er_.list_entreprise();
        } else {
            le_ = er_.list_entreprise_begin_with(search_.getText());
        }
    }

    @Override
    public void property_change() {
        setList();
        if (table_ != null) {
            table_.revalidate();
            table_.repaint();
        }
    }

    public Entreprise getEntreprise() {
        if (table_.getSelectedRow() >= 0) {
            return le_.get(table_.getSelectedRow());
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
    }
}
