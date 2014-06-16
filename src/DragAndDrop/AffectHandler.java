/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DragAndDrop;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import org.jdesktop.swingx.JXTree;

/**
 *
 * @author Flash
 */
public class AffectHandler extends TransferHandler {
    private final JXTree t_;
    public AffectHandler(JXTree t)
    {
        t_ = t;
    }
    @Override
    public boolean canImport(TransferSupport supp) {
        // Check for String flavor
        
        if (!supp.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            return false;
        }

        // Fetch the drop location
        JTree.DropLocation loc = (JTree.DropLocation) supp.getDropLocation();
        if (loc.getPath().getPath().length == 2)
                return true;
        return false;
    }

    @Override
    public boolean importData(TransferSupport supp) {
        if (!canImport(supp)) {
            return false;
        }

        // Fetch the Transferable and its data
        Transferable t = supp.getTransferable();
        //String data = t.getTransferData(stringFlavor);
        String str = "";
        try {
            str = (String) t.getTransferData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException | IOException e){
            
        }
        // Fetch the drop location
        JTree.DropLocation loc = (JTree.DropLocation) supp.getDropLocation();
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) loc.getPath().getLastPathComponent();
        node.add(new DefaultMutableTreeNode(str));
        t_.setModel(new DefaultTreeModel((DefaultMutableTreeNode)t_.getModel().getRoot()));
        t_.expandAll();
        
        return true;
    }
}
