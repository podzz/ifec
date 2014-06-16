/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import org.jdesktop.swingx.JXTree;

/**
 *
 * @author Flash
 */
public class PopUpEditStruct extends JPopupMenu {

    private JPanel panel = new JPanel();

    public PopUpEditStruct(JXTree t, Object[] path) {
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        setBorder(new LineBorder(Color.BLACK));
        setBackground(Color.WHITE);

        panel.setLayout(new BorderLayout());
        if (((DefaultMutableTreeNode) new TreePath(path).getLastPathComponent()).toString().startsWith("Calcul :")) {
            panel.setBorder(new TitledBorder("Outils de calcul : "));
            PopUpCalc pouc = new PopUpCalc(t, path);
            panel.add(pouc);
        } else if (path.length == 1) {
            panel.setBorder(new TitledBorder("Outils de section : " + path[path.length - 1]));
            PopUpSection pul = new PopUpSection(t, path);
            panel.add(pul);
        } else if (path.length == 2) {
            panel.setBorder(new TitledBorder("Outils de compte : " + path[path.length - 1]));
            PopUpCompte puc = new PopUpCompte(t, path);
            panel.add(puc);
        } else {
            panel.setBorder(new TitledBorder("Outils du compte/libelle : " + path[path.length - 1]));
            PopUpLeaf leaf = new PopUpLeaf(t, path);
            panel.add(leaf);
        }
        add(panel);
    }

}
