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
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import org.jdesktop.swingx.JXList;
import org.jdesktop.swingx.JXTree;

/**
 *
 * @author Flash
 */
public class AffectListHandler extends TransferHandler {

    private final JXTree t_;
    private final JXList l_;
    private final int fec_id_;
    private DataFlavor nodesFlavor;

    public AffectListHandler(int fec_id, JXList l, JXTree t) {
        fec_id_ = fec_id;
        l_ = l;
        t_ = t;
        nodesFlavor = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType + ";class=java.util.List", "List");

    }

    @Override
    public int getSourceActions(JComponent c) {
        return COPY_OR_MOVE;
    }

    @Override
    public boolean canImport(TransferSupport supp) {
        if (!supp.isDataFlavorSupported(nodesFlavor)) {
            return false;
        }
        return true;
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

        for (Object o : str) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) o;
            FecRequest.getInstance().remove_affectation(fec_id_, node.toString().substring(0, 6));

            DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
            parent.remove(node);
        }
        t_.setModel(new DefaultTreeModel((DefaultMutableTreeNode) t_.getModel().getRoot()));
        t_.expandAll();

        return true;
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        Transferable t = null;
        if (c instanceof JXList) {
            JXList list = (JXList) c;
            int[] selection = list.getSelectedIndices();
            Vector selectedRows = new Vector();
            for (int j = 0; j < selection.length; j++) {
                selectedRows.add(((DefaultListModel) list.getModel()).getElementAt(selection[j]));
            }
            t = new ListTransferable(selectedRows);
        }
        return t;

    }

    public class ListTransferable implements Transferable {

        List str;

        public ListTransferable(List str) {
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
            return new DataFlavor[]{nodesFlavor};
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
