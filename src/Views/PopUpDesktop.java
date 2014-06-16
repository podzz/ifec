/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Tools.Resizer;
import comptedit_db.Entreprise;
import comptedit_db.Exercice;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.VerticalLayout;

/**
 *
 * @author Flash
 */
public class PopUpDesktop extends JPopupMenu {

    private JXButton affect_;
    private JXButton show_;

    private final JTabbedPane jtp_;

    public PopUpDesktop(JTabbedPane jTabbedPane1, final Entreprise entreprise, final Exercice exercice) {

        jtp_ = jTabbedPane1;

        initComponent(entreprise, exercice);
        setLayout(new VerticalLayout(0));
        add(affect_);
        add(show_);
    }

    public final void initComponent(Entreprise entreprise, Exercice exercice) {
        String affect_title = entreprise.getNameEntreprise() + " : " + exercice.getDateBegin() + " / " + exercice.getDateEnd();
        affect_ = new JXButton("Affectation");
        affect_.setIcon(Resizer.get_resize_icon("Icon/link.png", 20, 20));
        add_tab(affect_, affect_title, Resizer.get_resize_icon("Icon/link.png", 20, 20), new EditAffectation(entreprise, exercice));

        String show_fec = "Vue FEC : " + entreprise.getNameEntreprise() + " : " + exercice.getDateBegin() + " / " + exercice.getDateEnd();
        show_ = new JXButton("Voir le fichier FEC");
        show_.setIcon(Resizer.get_resize_icon("Icon/stat.png", 20, 20));
        add_tab(show_, show_fec, Resizer.get_resize_icon("Icon/stat.png", 20, 20), new ShowFEC(exercice));
    }

    public void add_tab(JXButton button, final String default_title, final Icon icon, final JComponent panel_add) {
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int duplicate = -1;
                boolean ok = false;
                String title = "";
                while (!ok) {
                    if (duplicate < 0) {
                        title = default_title;
                    } else {
                        title = default_title + " (" + duplicate + ")";
                    }
                    ok = true;
                    for (int i = 0; i < jtp_.getTabCount(); i++) {
                        if (jtp_.getTitleAt(i).equals(title)) {
                            duplicate++;
                            ok = false;
                            break;
                        }
                    }
                }

                jtp_.addTab(title, panel_add);

                JPanel tab_component = new JPanel(new FlowLayout());
                tab_component.setOpaque(false);

                JLabel label = new JLabel(title);
                label.setIcon(icon);
                tab_component.add(label);
                tab_component.add(initCloseButton(title));
                jtp_.setTabComponentAt(jtp_.indexOfTab(title), tab_component);
            }
        });

    }

    public JComponent initCloseButton(String title) {
        final String title_compare = title;
        JXButton close_button = new JXButton("x");

        close_button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jtp_.indexOfTab(title_compare);
                if (index != -1) {
                    jtp_.remove(index);
                }
            }
        });
        close_button.add(new JLabel(Resizer.get_resize_icon("Icon/Delete-icon.png", 10, 10)));
        return close_button;
    }
}
