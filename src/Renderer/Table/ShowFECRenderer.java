/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Renderer.Table;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Flash
 */
public class ShowFECRenderer extends DefaultTableCellRenderer{
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = null;
       if (value != null)
            label = new JLabel(value.toString());
      else
           label = new JLabel();
        if ((row % 2 == 0) && !isSelected)
            label.setBackground(new Color(102,180,222, 70));
        else if (isSelected)
            label.setBackground(new Color(102,180,222, 200));
        else
            label.setBackground(null);
        label.setOpaque(true);
        return label;
    }
}
