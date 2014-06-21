/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.List;

import Models.AbstractModel;
import comptedit_db.Exercice;
import comptedit_db.Fec;
import comptedit_db.FecRequest;
import java.util.List;
import javax.swing.DefaultListModel;
import org.jdesktop.swingx.JXList;

/**
 *
 * @author Flash
 */
public class ListAffectation implements AbstractModel {

    private JXList l_;
    private final Exercice exercice_;

    public ListAffectation(JXList l, Exercice exercice) {
        l_ = l;
        exercice_ = exercice;
    }

    @Override
    public void property_change() {
        DefaultListModel listModel = (DefaultListModel) l_.getModel();
        listModel.removeAllElements();

        List<Fec> l = FecRequest.getInstance().getListNonAffected(exercice_.getFec());
        for (Fec f : l) {
            String cell_title = f.getCompteNum() + " - " + f.getCompteLib().trim();
            if (!listModel.contains(cell_title)) {
                listModel.addElement(cell_title);
            }
        }
        l_.setModel(listModel);
    }

}
