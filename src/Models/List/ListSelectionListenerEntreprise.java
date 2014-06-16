/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Models.List;

import Models.Table.TableEntreprise;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Flash
 */
public class ListSelectionListenerEntreprise implements ListSelectionListener {

    private ListExercice le_;
    private final JTable jtb_;
    public ListSelectionListenerEntreprise(ListExercice le, JTable jtb) {
        le_ = le;
        jtb_ = jtb;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (jtb_.getSelectedRow() >= 0)
        {
            le_.setSelected(((TableEntreprise)jtb_.getModel()).getEntreprise());
        }
    }
    
}
