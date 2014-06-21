/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DragAndDrop;

import comptedit_db.FecRequest;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import org.jdesktop.swingx.JXList;
import org.jdesktop.swingx.JXTree;

/**
 *
 * @author Flash
 */
public class AffectTreeHandler extends TransferHandler {

    private final JXTree t_;
    private final JXList l_;
    private final int fec_id_;
    private DataFlavor nodesFlavor;
    private DataFlavor[] flavors = new DataFlavor[1];

    public AffectTreeHandler(int fec_id, JXList l, JXTree t) {
        fec_id_ = fec_id;
        l_ = l;
        t_ = t;
        nodesFlavor = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType + ";class=java.util.List", "List");
        flavors[0] = nodesFlavor;
    }

    @Override
    public int getSourceActions(JComponent c) {
        return COPY_OR_MOVE;
    }

    @Override
    public boolean canImport(TransferSupport supp) {
        // Check for String flavor
        if (!supp.isDataFlavorSupported(nodesFlavor)) {
            return false;
        }

        // Fetch the drop location
        JTree.DropLocation loc = (JTree.DropLocation) supp.getDropLocation();
        if (loc != null && loc.getPath() != null && loc.getPath().getPath().length == 3) {
            return true;
        }
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
        List str = new ArrayList<String>();
        try {
            str = (List) t.getTransferData(nodesFlavor);
        } catch (UnsupportedFlavorException | IOException e) {
        }

        // Fetch the drop location
        JTree.DropLocation loc = (JTree.DropLocation) supp.getDropLocation();

        DefaultMutableTreeNode node = (DefaultMutableTreeNode) loc.getPath().getLastPathComponent();
        for (Object o : str) {
            if (o instanceof DefaultMutableTreeNode) {
                DefaultMutableTreeNode n = (DefaultMutableTreeNode) o;
                DefaultMutableTreeNode parent = (DefaultMutableTreeNode) n.getParent();
                if (parent != node.getParent()) {
                    node.add(new DefaultMutableTreeNode(o.toString()));
                    FecRequest.getInstance().add_affectation(fec_id_, o.toString().substring(0, 6), node.toString().substring(0, 4));
                    parent.remove(n);
                }

            } else {
                node.add(new DefaultMutableTreeNode(o.toString()));
                FecRequest.getInstance().add_affectation(fec_id_, o.toString().substring(0, 6), node.toString().substring(0, 4));
            }
        }
        t_.setModel(new DefaultTreeModel((DefaultMutableTreeNode) t_.getModel().getRoot()));
        t_.expandAll();
        return true;
    }

    @Override
    protected Transferable createTransferable(JComponent c) {

        Transferable t = null;
        if (c instanceof JXTree) {
            JXTree tree = (JXTree) c;
            TreePath[] selection = tree.getSelectionPaths();
            Vector selectedRows = new Vector();
            for (int j = 0; j < selection.length; j++) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) selection[j].getLastPathComponent();
                if (selection[j].getPath().length == 4) {
                    selectedRows.add(node);
                }
            }
            t = new ListNodesTransferable(selectedRows);
        }
        return t;
    }

    public class ListNodesTransferable implements Transferable {

        List str;

        public ListNodesTransferable(List str) {
            this.str = str;
        }

        public Object getTransferData(DataFlavor flavor)
                throws UnsupportedFlavorException {
            if (!isDataFlavorSupported(flavor)) {
                throw new UnsupportedFlavorException(flavor);
            }
            return str;
        }

        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[]{new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType + ";class=java.util.List", "List")};
        }

        public boolean isDataFlavorSupported(DataFlavor flavor) {
            DataFlavor[] flavors = getTransferDataFlavors();
            for (int i = 0; i < flavors.length; i++) {
                if (flavors[i].equals(flavor)) {
                    return true;
                }
            }
            return false;
        }
    }

}
