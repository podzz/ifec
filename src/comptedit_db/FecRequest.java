package comptedit_db;

import Models.AbstractModel;
import Tools.Tuple;
import comptedit_db.Entreprise;
import comptedit_db.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class FecRequest {

    private static FecRequest instance_ = new FecRequest();

    private List<Tuple<AbstractModel, JComponent>> l;

    private FecRequest() {
        l = new ArrayList<Tuple<AbstractModel, JComponent>>();
    }

    public static FecRequest getInstance() {
        return instance_;
    }

    public boolean check_entreprise(String name) {
        boolean check = false;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List<Entreprise> l = session.createQuery("from Entreprise ORDER BY NAME_ENTREPRISE").list();
            for (Entreprise e : l) {
                if (e.getNameEntreprise().equals(name)) {
                    check = true;
                }
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return check;
    }

    public Integer get_first_id_avaible() {
        Integer n = 0;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List<Fec> l = session.createQuery("from Fec ORDER BY FEC").list();
            List<Integer> find_index = new ArrayList<Integer>();
            for (Fec e : l) {
                if (!find_index.contains(e.getFec())) {
                    find_index.add(e.getFec());
                }
            }
            n = find_index.size();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return n;
    }
    
    public void addFec(Fec f)
    {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            session.save(f);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public void fire_component() {
        for (Tuple<AbstractModel, JComponent> jc : l) {
            jc.getX().property_change();
            jc.getY().revalidate();
            jc.getY().repaint();
        }
    }

    public void add_fire_component(AbstractModel jc, JComponent c) {
        l.add(new Tuple<AbstractModel, JComponent>(jc, c));
    }

}
