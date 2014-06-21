/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.TreeTable;

import java.text.DateFormatSymbols;
import java.util.GregorianCalendar;
import javax.swing.tree.DefaultMutableTreeNode;
import org.jdesktop.swingx.treetable.AbstractTreeTableModel;

/**
 *
 * @author Flash
 */
public class TreeTableTc extends AbstractTreeTableModel {

    private Object[] titles;

    public TreeTableTc(ColumnData columnData, DefaultMutableTreeNode root) {
        super(root);
        titles = columnData.getColumnNames().toArray();
    }

    /**
     * Table Columns
     *
     * @param column
     * @return
     */
    @Override
    public String getColumnName(int column) {
        if (titles[column] instanceof GregorianCalendar) {
            GregorianCalendar cal = (GregorianCalendar) titles[column];
            String month = new DateFormatSymbols().getMonths()[cal.get(GregorianCalendar.MONTH)];
            month = month.substring(0,1);
            return month.toUpperCase() + cal.get(GregorianCalendar.YEAR);
        } else if (titles[column] instanceof String) {
            return (String) titles[column];
        }
        return "";

    }

    @Override
    public int getColumnCount() {
        return titles.length;
    }

    @Override
    public Class getColumnClass(int column) {
        return String.class;
    }

    @Override
    public Object getValueAt(Object arg0, int arg1) {
        if (arg0 instanceof DefaultMutableTreeNode) {
            DefaultMutableTreeNode dataNode = (DefaultMutableTreeNode) arg0;
            if (dataNode.getUserObject() instanceof RowData && arg1 != 0) {
                RowData data = (RowData) dataNode.getUserObject();
                if (titles[arg1] instanceof GregorianCalendar)
                    return data.getResult((GregorianCalendar) titles[arg1]);
            } else if (dataNode.getUserObject() instanceof RowData) {
                RowData data = (RowData) dataNode.getUserObject();
                return data.getAcount();
            } else if (arg1 == 0) {
                return dataNode.toString();
            }
        }
        return null;
    }

    @Override
    public Object getChild(Object arg0, int arg1) {

        if (arg0 instanceof DefaultMutableTreeNode) {
            DefaultMutableTreeNode nodes = (DefaultMutableTreeNode) arg0;
            return nodes.getChildAt(arg1);
        }
        return null;
    }

    @Override
    public int getChildCount(Object arg0) {

        if (arg0 instanceof DefaultMutableTreeNode) {
            DefaultMutableTreeNode nodes = (DefaultMutableTreeNode) arg0;
            return nodes.getChildCount();
        }
        return 0;
    }

    @Override
    public int getIndexOfChild(Object arg0, Object arg1) {
        if (arg0 instanceof DefaultMutableTreeNode && arg1 instanceof DefaultMutableTreeNode) {
            DefaultMutableTreeNode parent = (DefaultMutableTreeNode) arg0;
            for (int i = 0; i < parent.getChildCount(); i++) {
                if (parent.getChildAt(i).equals(arg1)) {
                    return i;
                }
            }
        }
        return -1;

    }

    @Override
    public boolean isLeaf(Object node) {
        return getChildCount(node) == 0;
    }

    public void refresh() {
    }

}
