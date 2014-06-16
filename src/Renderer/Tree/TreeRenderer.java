/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Renderer.Tree;

import Tools.Resizer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import org.jdesktop.swingx.JXTree;

/**
 *
 * @author Flash
 */
public class TreeRenderer extends DefaultTreeCellRenderer {

    private static final Icon root_closed
            = (Icon) Resizer.get_resize_icon("Icon/root_close.png", 50, 50);
    private static final Icon root_open
            = (Icon) Resizer.get_resize_icon("Icon/root_open.png", 50, 50);
    private static final Icon section_closed
            = (Icon) Resizer.get_resize_icon("Icon/section_close.png", 40, 40);
    private static final Icon section_open
            = (Icon) Resizer.get_resize_icon("Icon/section_open.png", 40, 40);
    private static final Icon leaf_icon
            = (Icon) Resizer.get_resize_icon("Icon/leaf.png", 25, 25);
        private static final Icon calc
            = (Icon) Resizer.get_resize_icon("Icon/frame-icon.png", 25, 25);

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value,
            boolean sel, boolean exp, boolean leaf, int row, boolean hasFocus) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.white);
        JLabel label_icon = null;
        JLabel label_text = new JLabel(node.toString());
        if (node.toString().startsWith("Calcul :"))
        {
            label_icon = new JLabel(calc);
            panel.setBackground(new Color(122,183,114, 125));
            panel.setBorder(new LineBorder(Color.BLACK));
            
        }
        else if (node.getPath().length == 1) {
            if (exp) {
                label_icon = new JLabel(root_open);
            } else {
                label_icon = new JLabel(root_closed);
            }
            label_text.setForeground(Color.RED);
        } else if (node.getPath().length == 2) {
            if (exp) {
                label_icon = new JLabel(section_open);
            } else {
                label_icon = new JLabel(section_closed);
            }
            label_text.setForeground(Color.BLUE);
        } else {
            label_icon = new JLabel(leaf_icon);
        }

        panel.add(label_icon, BorderLayout.WEST);

        
        panel.add(label_text, BorderLayout.CENTER);
        if (hasFocus == true) {
            panel.setBackground(new Color(102,180,222, 125));
        }
        return panel;
    }
}
