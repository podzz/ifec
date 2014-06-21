/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Renderer.Table;

import Tools.Resizer;
import comptedit_db.Entreprise;
import comptedit_db.ExerciceRequest;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Flash
 */

public class TableEntepriseRenderer extends DefaultTableCellRenderer
{
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Entreprise e = (Entreprise) value;
        int count = ExerciceRequest.getInstance().count_exercice_in(e.getIdEntreprise());
        super.getTableCellRendererComponent(table,  e.getNameEntreprise() + " (" + count + ")", isSelected, hasFocus, row, column);
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(true);
        JLabel label = new JLabel((String) e.getNameEntreprise() + " (" + count + ")");
        label.setHorizontalAlignment(CENTER);
      
        if (isSelected)
            panel.setBackground(new Color(102,180,222, 200));
        else
            panel.setBackground(null);
        
        panel.add(label, BorderLayout.CENTER);
            
        return panel;
    }

    
}
