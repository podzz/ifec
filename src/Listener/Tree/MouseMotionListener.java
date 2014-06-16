/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listener.Tree;

import Views.PopUpEditStruct;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import org.jdesktop.swingx.JXTree;

/**
 *
 * @author Flash
 */
public class MouseMotionListener implements java.awt.event.MouseMotionListener {

    private JXTree t_;

    public MouseMotionListener(JXTree t) {
        t_ = t;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(final MouseEvent e) {
        final TreePath path;
        TreePath calc_path = null;
        for (int i = 0; i < e.getComponent().getWidth(); i++) {
            calc_path = t_.getPathForLocation(i, e.getY());

            if (calc_path != null) {
                break;
            }
        }
        if (calc_path != null) {
            path = calc_path;
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
            t_.setSelectionRow(t_.getRowForPath(path));
            final Rectangle r = t_.getRowBounds(t_.getSelectionRows()[0]);

            doPop(e, r, path.getPath());

        }
    }

    public void doPop(MouseEvent e, Rectangle r, Object[] path) {
        PopUpEditStruct menu = new PopUpEditStruct(t_, path);
        if (path.length == 1)
        menu.show(e.getComponent(), (int) r.getX() + (int) r.getWidth() + 30, (int) r.getY() - 15);
        else if (path.length == 2)
        menu.show(e.getComponent(), (int) r.getX() + (int) r.getWidth() + 30, (int) r.getY() - 15);
        else if (path.length == 3)
        menu.show(e.getComponent(), (int) r.getX() + (int) r.getWidth() + 30, (int) r.getY() - 30);
    }

}
