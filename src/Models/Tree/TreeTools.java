package Models.Tree;

import Tools.Resizer;
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
    
    public String formatNameCalc(DefaultMutableTreeNode node)
    {
        String str = node.toString();
        str = str.replaceAll("Calcul : ", "");
        int count = 0;
        String name = "";
        for (int i = 0; i < str.length(); i++)
        {
            name += str.charAt(i);
            if (str.charAt(i) == '$')
                count++;
            if (count == 2)
                break;     
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
                if (expr.contains("$" + formatCompte(delete) + "$"))
                    return false;
            }
            return true;
        }
        return false;
    }
    
    
    public List<DefaultMutableTreeNode> list_all_calc_exclude_root()
    {
        List<DefaultMutableTreeNode> l = new ArrayList<DefaultMutableTreeNode>();
        DefaultMutableTreeNode root_ = (DefaultMutableTreeNode) t_.getModel().getRoot();
        for (int i = 0; i < root_.getChildCount(); i++)
        {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode) root_.getChildAt(i);
            if (child.getChildCount() > 0)
                l.addAll(list_calc_in(child.getPath()));
        }
        return l;
    }
}
