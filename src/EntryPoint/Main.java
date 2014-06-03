package EntryPoint;

import Main_view.Main_window;
import Main_view.Main_window;
import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class Main {

    public static void main(String[] args) {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main_window frame = new Main_window();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
