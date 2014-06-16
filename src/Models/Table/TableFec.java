/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Table;

import Models.AbstractModel;
import Parser.FecParser;
import comptedit_db.FecRequest;
import java.util.Date;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import org.jdesktop.swingx.JXTable;

/**
 *
 * @author Flash
 */
public class TableFec extends AbstractTableModel implements AbstractModel {

    private String[] columnName_;
    private Class<?>[] classType_ = {String.class, String.class, Integer.class, Date.class, String.class, String.class, String.class, String.class, String.class, Date.class, String.class, Float.class,
        Character.class, String.class, Date.class, Date.class, Float.class, String.class, String.class};
    ;
    private Object[][] render_;
    private JXTable t_;
    private Integer fec_;

    public TableFec(Integer fec, JXTable jXTable1) {
        fec_ = fec;
        t_ = jXTable1;
        FecParser parser = new FecParser(fec);
        columnName_ = parser.getColumnNames();
        render_ = parser.getRenderingJTable();
    }

    @Override
    public int getRowCount() {
        return render_.length;
    }

    @Override
    public int getColumnCount() {
        return columnName_.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnName_[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {

        return classType_[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return render_[rowIndex][columnIndex];
    }

    @Override
    public void property_change() {
        Thread thr = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    FecParser parser = new FecParser(fec_);
                    columnName_ = parser.getColumnNames();
                    render_ = parser.getRenderingJTable();
                    

                } finally {
                    setModel();
                }
            }
        });
        thr.start();

    }
    
    public void setModel()
    {
        t_.setModel(this);
    }
}
