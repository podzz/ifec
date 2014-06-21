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
    
    public List<Fec> getListFecOn(int fec_id)
    {
        List<Fec> l = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            l = session.createQuery("from Fec WHERE FEC=" + fec_id).list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return l;
     }

    public void fire_component() {
        for (Tuple<AbstractModel, JComponent> jc : l) {
            jc.getX().property_change();
            jc.getY().revalidate();
            jc.getY().repaint();
        }
    }
    
    public List<Fec> getListNonAffected(int fec_id)
    {
        List<Fec> l = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            l = session.createQuery("from Fec WHERE FEC=" + fec_id + " AND AFFECTATION IS NULL AND SUBSTRING(COMPTE_NUM,1,1)> '5' ORDER BY COMPTE_NUM").list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return l;
    }
    
    public void add_affectation(int fec_id, String compte_anal, String affectation)
    {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List<Fec> l = session.createQuery("from Fec WHERE FEC=" + fec_id + " AND COMPTE_NUM=" + compte_anal).list();
            for (Fec f : l)
            {
                f.setAffectation(String.valueOf(affectation));
                session.save(f);
            }
            session.getTransaction().commit();
            fire_component();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
    
    public void remove_affectation(int fec_id, String compte_anal)
    {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List<Fec> l = session.createQuery("from Fec WHERE FEC=" + fec_id + " AND COMPTE_NUM=" + compte_anal).list();
            for (Fec f : l)
            {
                f.setAffectation(null);
                session.save(f);
            }
            session.getTransaction().commit();
            fire_component();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
    
      public List<String> list_affectation_on(int fec_id, String num)
    {
        
        List<String> l = new ArrayList<String>();
        try {
            List<Fec> aux = null;
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            aux = session.createQuery("from Fec WHERE FEC=" + fec_id + " AND AFFECTATION="+ num).list();
            for (Fec f : aux)
            {
                if (!l.contains(f.getCompteNum() + " - " + f.getCompteLib()))
                    l.add(f.getCompteNum() + " - " + f.getCompteLib());
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return l;
    }

    public void add_fire_component(AbstractModel jc, JComponent c) {
        l.add(new Tuple<AbstractModel, JComponent>(jc, c));
    }

}
