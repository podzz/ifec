/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Renderer.List;

import Tools.Resizer;
import Views.ListExercicePanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Flash
 */
public class ListCompteRenderer extends JLabel implements ListCellRenderer<Object> {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        setText((String) value);
        setIcon(Resizer.get_resize_icon("Icon/compte.png", 30, 30));

        if (isSelected) {
            setBackground(new Color(102, 180, 222, 125));
        } else {
            setBackground(Color.WHITE);
        }
        setOpaque(true);
        return this;
    }

}
