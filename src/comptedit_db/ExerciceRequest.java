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

public class ExerciceRequest {

    private static ExerciceRequest instance_ = new ExerciceRequest();

    private List<Tuple<AbstractModel, JComponent>> l;

    private ExerciceRequest() {
        l = new ArrayList<Tuple<AbstractModel, JComponent>>();
    }

    public static ExerciceRequest getInstance() {
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

    public void add_exercice(Exercice e) {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            session.save(e);
            session.getTransaction().commit();
            fire_component();
        } catch (HibernateException exc) {
            exc.printStackTrace();
        }
    }

    public List<Exercice> list_exercice() {
        List<Exercice> le = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            le = session.createQuery("from Exercice ORDER BY DATE_BEGIN").list();
            session.getTransaction().commit();
        } catch (HibernateException exc) {
            exc.printStackTrace();
        }
        return le;
    }
    
    
    public int count_exercice_in(long id) {
        List<Entreprise> l = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            l = session.createQuery("from Exercice WHERE ENTREPRISE=" + id).list();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return l.size();
    }

    public void delete_exercice(long id) {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List<Exercice> le = session.createQuery("from Exercice WHERE ID_EXERCICE=" + id).list();
            for (Exercice e : le)
            {
                session.delete(e);
            }
            session.getTransaction().commit();
            fire_component();
        } catch (HibernateException exc) {
            exc.printStackTrace();
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
