/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Renderer.List;

import Views.ListExercicePanel;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Flash
 */
public class ListRenderer implements ListCellRenderer<Object>{

    @Override
    public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        String s1 = value.toString().split("->")[0];
        String s2 = value.toString().split("->")[1];
        ListExercicePanel pan = new ListExercicePanel(s1,s2);
        
        if (isSelected)
            pan.setBackground(new Color(102,180,222, 125));
        else
            pan.setBackground(Color.WHITE);
        return pan;
    }
    
}
