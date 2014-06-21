package Models.Tree;

import Models.TreeTable.ColumnData;
import Models.TreeTable.RowData;
import Tools.Resizer;
import comptedit_db.Entreprise;
import comptedit_db.Exercice;
import comptedit_db.Fec;
import comptedit_db.FecRequest;
import comptedit_db.StructAnalRequest;
import comptedit_db.StructureAnalytique;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import org.jdesktop.swingx.JXTree;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Flash
 */
public class TreeTools {

    private JXTree t_;

    public TreeTools(JXTree t) {
        t_ = t;
    }

    public void addSection(String nameSection) {
        DefaultMutableTreeNode root_ = (DefaultMutableTreeNode) t_.getModel().getRoot();
        root_.insert(new DefaultMutableTreeNode(nameSection), 0);
        t_.setModel(new DefaultTreeModel(root_));
        t_.expandAll();
    }

    public void addCompteToSection(String compte, Object[] path) {
        DefaultMutableTreeNode root_ = (DefaultMutableTreeNode) t_.getModel().getRoot();
        TreePath n = new TreePath(path);
        DefaultMutableTreeNode section = (DefaultMutableTreeNode) n.getLastPathComponent();
        section.insert(new DefaultMutableTreeNode(compte), 0);
        t_.setModel(new DefaultTreeModel(root_));
        t_.expandAll();
    }

    public void addCalcTo(String calc, Object[] path) {
        DefaultMutableTreeNode root_ = (DefaultMutableTreeNode) t_.getModel().getRoot();
        TreePath n = new TreePath(path);
        DefaultMutableTreeNode section = (DefaultMutableTreeNode) n.getLastPathComponent();
        section.add(new DefaultMutableTreeNode(calc));
        t_.setModel(new DefaultTreeModel(root_));
        t_.expandAll();
    }

    public List<DefaultMutableTreeNode> list_nocalc_leaf_in(Object[] path) {
        List<DefaultMutableTreeNode> ldmt = new ArrayList<DefaultMutableTreeNode>();
        TreePath n = new TreePath(path);
        DefaultMutableTreeNode cat = (DefaultMutableTreeNode) n.getLastPathComponent();
        for (int i = 0; i < cat.getChildCount(); i++) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode) cat.getChildAt(i);
            if (child.getChildCount() > 0) {
                for (int j = 0; j < child.getChildCount(); j++) {
                    DefaultMutableTreeNode leaf = (DefaultMutableTreeNode) cat.getChildAt(i);
                    if (!leaf.toString().startsWith("Calcul :")) {
                        ldmt.add(leaf);
                    }
                }
            } else if (!child.toString().startsWith("Calcul :")) {
                ldmt.add(child);
            }
        }
        return ldmt;
    }

    public List<DefaultMutableTreeNode> list_calc_in(Object[] path) {
        List<DefaultMutableTreeNode> ldmt = new ArrayList<DefaultMutableTreeNode>();
        TreePath n = new TreePath(path);
        DefaultMutableTreeNode cat = (DefaultMutableTreeNode) n.getLastPathComponent();
        for (int i = 0; i < cat.getChildCount(); i++) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode) cat.getChildAt(i);
            if (child.toString().startsWith("Calcul :")) {
                ldmt.add(child);
            }
        }
        return ldmt;
    }

    public void remove(Object[] path) {
        DefaultMutableTreeNode root_ = (DefaultMutableTreeNode) t_.getModel().getRoot();
        TreePath n = new TreePath(path);
        DefaultMutableTreeNode section = (DefaultMutableTreeNode) n.getLastPathComponent();
        ((DefaultMutableTreeNode) section.getParent()).remove(section);
        t_.setModel(new DefaultTreeModel(root_));
        t_.expandAll();
    }

    public void up_item(Object[] path) {
        DefaultMutableTreeNode root_ = (DefaultMutableTreeNode) t_.getModel().getRoot();
        TreePath n = new TreePath(path);

        DefaultMutableTreeNode item = (DefaultMutableTreeNode) n.getLastPathComponent();
        DefaultMutableTreeNode previous = (DefaultMutableTreeNode) item.getPreviousSibling();
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) item.getParent();
        if (previous != null && ((item.toString().startsWith("Calcul :") && previous.toString().startsWith("Calcul :"))
                || (!item.toString().startsWith("Calcul :") && !previous.toString().startsWith("Calcul :")))) {
            int id_previous = parent.getIndex(previous);
            int id_item = parent.getIndex(item);
            parent.remove(previous);
            parent.remove(item);
            parent.insert(item, id_previous);
            parent.insert(previous, id_item);
        }
        t_.setModel(new DefaultTreeModel(root_));
        t_.expandAll();
    }

    public void down_item(Object[] path) {
        DefaultMutableTreeNode root_ = (DefaultMutableTreeNode) t_.getModel().getRoot();
        TreePath n = new TreePath(path);

        DefaultMutableTreeNode item = (DefaultMutableTreeNode) n.getLastPathComponent();
        DefaultMutableTreeNode next = (DefaultMutableTreeNode) item.getNextSibling();
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) item.getParent();

        if (next != null && ((item.toString().startsWith("Calcul :") && next.toString().startsWith("Calcul :"))
                || (!item.toString().startsWith("Calcul :") && !next.toString().startsWith("Calcul :")))) {
            int id_next = parent.getIndex(next);
            int id_item = parent.getIndex(item);
            parent.remove(next);
            parent.remove(item);
            parent.insert(next, id_item);
            parent.insert(item, id_next);
        }
        t_.setModel(new DefaultTreeModel(root_));
        t_.expandAll();
    }

    public String formatCompte(DefaultMutableTreeNode node) {
        return node.toString().substring(0, 4);
    }

    public String formatCalc(DefaultMutableTreeNode node) {
        int begin = node.toString().indexOf("\"");
        int last = node.toString().lastIndexOf("\"");
        return node.toString().substring(begin + 1, last);

    }

    public String formatNameCalc(DefaultMutableTreeNode node) {
        String str = node.toString();
        str = str.replaceAll("Calcul : ", "");
        int count = 0;
        String name = "";
        for (int i = 0; i < str.length(); i++) {
            name += str.charAt(i);
            if (str.charAt(i) == '$') {
                count++;
            }
            if (count == 2) {
                break;
            }
        }
        return name;
    }

    public boolean check_calc_for_delete_node(Object[] path) {
        if (path.length == 3) {
            DefaultMutableTreeNode root_ = (DefaultMutableTreeNode) t_.getModel().getRoot();
            TreePath n = new TreePath(path);

            DefaultMutableTreeNode delete = (DefaultMutableTreeNode) n.getLastPathComponent();
            List<DefaultMutableTreeNode> l_str = list_calc_in(((DefaultMutableTreeNode) delete.getParent()).getPath());
            List<DefaultMutableTreeNode> l_scope = list_nocalc_leaf_in(((DefaultMutableTreeNode) delete.getParent()).getPath());

            for (DefaultMutableTreeNode a : l_str) {
                String expr = formatCalc(a);
                if (expr.contains("$" + formatCompte(delete) + "$")) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public List<DefaultMutableTreeNode> list_all_calc_exclude_root() {
        List<DefaultMutableTreeNode> l = new ArrayList<DefaultMutableTreeNode>();
        DefaultMutableTreeNode root_ = (DefaultMutableTreeNode) t_.getModel().getRoot();
        for (int i = 0; i < root_.getChildCount(); i++) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode) root_.getChildAt(i);
            if (child.getChildCount() > 0) {
                l.addAll(list_calc_in(child.getPath()));
            }
        }
        return l;
    }

    public void loadTree(String nameStruct, JXTree t, boolean with_calc) {
        DefaultMutableTreeNode n = new DefaultMutableTreeNode(nameStruct);

        // RESET TREE
        int count = n.getChildCount();
        for (int i = 0; i < count; i++) {
            n.remove(0);
        }

        for (String s : list_section(nameStruct, with_calc)) {
            DefaultMutableTreeNode section = new DefaultMutableTreeNode(s);
            for (String s2 : list_compte(nameStruct, s, with_calc)) {

                section.add(new DefaultMutableTreeNode(s2));

            }
            n.add(section);
        }
        t.setModel(new DefaultTreeModel(n));
        t.expandAll();
    }

    public void loadTree(String nameStruct, JXTree t, int fec_id) {
        DefaultMutableTreeNode n = new DefaultMutableTreeNode(nameStruct);

        // RESET TREE
        int count = n.getChildCount();
        for (int i = 0; i < count; i++) {
            n.remove(0);
        }

        for (String s : list_section(nameStruct, false)) {
            DefaultMutableTreeNode section = new DefaultMutableTreeNode(s);
            for (String s2 : list_compte(nameStruct, s, false)) {
                DefaultMutableTreeNode affectation = new DefaultMutableTreeNode(s2);

                List<String> l = FecRequest.getInstance().list_affectation_on(fec_id, s2.substring(0, 4));
                for (String f : l) {
                    affectation.add(new DefaultMutableTreeNode(f));
                }
                section.add(affectation);
            }
            n.add(section);
        }
        t.setModel(new DefaultTreeModel(n));
        t.expandAll();
    }
    
    public void loadTcTree(String nameStruct, JXTree t, int fec_id, Exercice exercice, ColumnData cd) {
        DefaultMutableTreeNode n = new DefaultMutableTreeNode(nameStruct);

        // RESET TREE
        int count = n.getChildCount();
        for (int i = 0; i < count; i++) {
            n.remove(0);
        }

        for (String s : list_section(nameStruct, false)) {
            DefaultMutableTreeNode section = new DefaultMutableTreeNode(s);
            for (String s2 : list_compte(nameStruct, s, false)) {
                DefaultMutableTreeNode affectation = new DefaultMutableTreeNode(s2);

                List<String> l = FecRequest.getInstance().list_affectation_on(fec_id, s2.substring(0, 4));
                for (String f : l) {
                    affectation.add(new DefaultMutableTreeNode(new RowData(f, exercice, cd)));
                }
                section.add(affectation);
            }
            n.add(section);
        }
        t.setModel(new DefaultTreeModel(n));
        t.expandAll();
    }

    public List<String> list_compte(String nameStruct, String section, boolean with_calc) {
        List<StructureAnalytique> lsa = StructAnalRequest.getInstance().list_structanal_on_alias(nameStruct);
        List<String> ls = new ArrayList<String>();

        for (StructureAnalytique sa : lsa) {
            if (sa.getSection() != null && sa.getCompteAnalytique() != null && sa.getLibelle() != null) {
                if (sa.getSection().equals(section)) {
                    ls.add(sa.getCompteAnalytique() + " - " + sa.getLibelle());
                }
            }
        }

        if (with_calc) {
            for (StructureAnalytique sa : lsa) {
                if (sa.getSection() != null && sa.getSection().equals(section) && sa.getCompteAnalytique() == null) {
                    ls.add(sa.getLibelle());
                }
            }
        }

        return ls;
    }

    public List<String> list_section(String nameStruct, boolean with_calc) {
        List<StructureAnalytique> aux = StructAnalRequest.getInstance().list_structanal_on_alias(nameStruct);
        List<String> ls = new ArrayList<String>();

        for (StructureAnalytique aux_sa : aux) {
            if (aux_sa.getSection() != null && !ls.contains(aux_sa.getSection())) {
                ls.add(aux_sa.getSection());
            }
        }

        if (with_calc) {
            for (StructureAnalytique aux_sa : aux) {
                if (aux_sa.getSection() == null && aux_sa.getCompteAnalytique() == null) {
                    ls.add(aux_sa.getLibelle());
                }
            }
        }

        return ls;
    }

    public String formatCompte(String compte) {
        return compte.substring(0, 4);
    }

    public String formatLibelle(String compte) {
        return compte.split(" - ")[1];
    }

}
