/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import comptedit_db.StructAnalRequest;
import comptedit_db.StructureAnalytique;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

/**
 *
 * @author Flash
 */
public class EditStructAnalTableModel implements TableModel, AbstractModel {

    private JTable jta_;
    private List<StructureAnalytique> lsa_;
    private StructAnalRequest sar_;
    private JComboBox jcb_;
    private final String[] entetes = {"Section", "Compte analytique", "Structure analytique"};

    public EditStructAnalTableModel(JTable jta, JComboBox jcb) {
        jta_ = jta;
        jcb_ = jcb;
        lsa_ = new ArrayList<StructureAnalytique>();
        sar_ = StructAnalRequest.getInstance();
    }

    @Override
    public int getRowCount() {
        return lsa_.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {

            return String.class;

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StructureAnalytique sa = lsa_.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return sa.getSection();
            case 1:
                return sa.getCompteAnalytique();
            case 2:
                return sa.getLibelle();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        StructureAnalytique sa = lsa_.get(rowIndex);
        switch (columnIndex) {
            case 0:
                sa.setSection((String) aValue);
                break;
            case 1:
                sa.setCompteAnalytique((String) aValue);
                break;
            case 2:
                sa.setLibelle((String) aValue);
                break;
            default:
                break;
        }
        sar_.update(sa);
        jta_.setModel(this);

    }

    @Override
    public void addTableModelListener(TableModelListener l) {
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
    }

    @Override
    public void property_change() {
        if (jcb_.getSelectedItem() != null) {
            lsa_ = sar_.list_structanal_on_alias((String) jcb_.getSelectedItem());

        }
    }
    
    public long get_id_selected()
    {
        return lsa_.get(jta_.getSelectedRow()).getIdStructure();
    }

}
