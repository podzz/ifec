/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Renderer.Tree;

import Tools.Resizer;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author Flash
 */
public class TreeAffectRenderer extends DefaultTreeCellRenderer {

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

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value,
            boolean sel, boolean exp, boolean leaf, int row, boolean hasFocus) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        super.getTreeCellRendererComponent(tree, node.toString(), sel, exp, leaf, row, hasFocus);

        setText(node.toString());
        setBackground(null);
        if (node.getPath().length == 1) {
            if (exp) {
                setIcon(root_open);
            } else {
                setIcon(root_closed);
            }
        } else if (node.getPath().length == 2) {
            if (exp) {
                setIcon(section_open);
            } else {
                setIcon(section_closed);
            }
        } else if (node.getPath().length == 3) {
            setIcon(leaf_icon);
        } else if (node.getPath().length == 4 || sel) {
            JLabel label = new JLabel(node.toString());
            label.setIcon(Resizer.get_resize_icon("Icon/compte.png", 25, 25));
            if (sel) {
                label.setBackground(new Color(102, 180, 222, 60));
            }
            return label;

        }
        setOpaque(true);

        return this;
    }
}
