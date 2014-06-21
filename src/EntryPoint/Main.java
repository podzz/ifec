package EntryPoint;

import Views.Desktop_view;
import Views.EditEntreprise;
import Views.EditEntreprise;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import org.pushingpixels.substance.api.skin.SubstanceGraphiteLookAndFeel;

public class Main {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(new SubstanceGraphiteLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    Desktop_view v = new Desktop_view();
                    v.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        );
    }
}
