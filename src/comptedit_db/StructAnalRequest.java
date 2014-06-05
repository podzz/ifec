package comptedit_db;

import Models.AbstractModel;
import Tools.Tuple;
import comptedit_db.Entreprise;
import comptedit_db.HibernateUtil;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JPanel;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;

public class StructAnalRequest {

    private static final StructAnalRequest instance_ = new StructAnalRequest();

    private List<Tuple<AbstractModel, JComponent>> l;

    private StructAnalRequest() {
        l = new ArrayList<Tuple<AbstractModel, JComponent>>();
    }

    public static StructAnalRequest getInstance() {
        return instance_;
    }

    public boolean check_alias(String alias) {
        boolean check = false;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List<StructureAnalytique> l = session.createQuery("from StructureAnalytique").list();
            for (StructureAnalytique e : l) {
                if (e.getAliasStructure().equals(alias)) {
                    check = true;
                }
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return check;
    }

    public boolean add_structanal(StructureAnalytique sa) {
        boolean check = check_alias(sa.getAliasStructure());
        if (!check && !sa.getAliasStructure().equals("")) {
            try {
                Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                session.beginTransaction();
                session.save(sa);
                session.getTransaction().commit();
                fire_component();
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
        return !check;
    }

    public List<StructureAnalytique> list_structanal() {
        List<StructureAnalytique> l = new ArrayList<StructureAnalytique>();
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            l = session.createQuery("from StructureAnalytique ORDER BY ALIAS_STRUCTURE").list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        List<StructureAnalytique> l_result = new ArrayList<StructureAnalytique>();
        List<String> structFiltered = new ArrayList<String>();
        for (StructureAnalytique sa : l) {
            if (!structFiltered.contains((String)sa.getAliasStructure())) {
                l_result.add(sa);
                structFiltered.add(sa.getAliasStructure());
            }
        }
        
        return l_result;
    }

    public List<StructureAnalytique> list_structanal_on_alias(String alias) {
        List<StructureAnalytique> l = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            l = session.createQuery("from StructureAnalytique ORDER BY ALIAS_STRUCTURE WHERE ALIAS_STRUCTURE=\"" + alias + "\"").list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return l;
    }

    public void add_row_to(String alias) {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            StructureAnalytique new_sa = new StructureAnalytique(alias, "", "", "");
            session.save(new_sa);
            session.getTransaction().commit();
            fire_component();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
    
        public void add_row_to_with(String alias, String section, String cp_an, String lib) {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            StructureAnalytique new_sa = new StructureAnalytique(alias, section, cp_an, lib);
            session.save(new_sa);
            session.getTransaction().commit();
            fire_component();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
    
    public void update(StructureAnalytique sa)
    {
        List<StructureAnalytique> l = null;
         try {
            System.out.println(sa.getIdStructure());
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
             l = session.createQuery("from StructureAnalytique").list();
             for (StructureAnalytique sa_aux : l)
             {
                 if (sa_aux.getIdStructure() == sa.getIdStructure())
                 {
                     sa_aux.setCompteAnalytique(sa.getCompteAnalytique());
                     sa_aux.setLibelle(sa.getLibelle());
                     sa_aux.setSection(sa.getSection());
                     session.save(sa_aux);
                 }
             }
            session.getTransaction().commit();
            fire_component();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
    
        public void delete_row(long _id_selected) {
        List<StructureAnalytique> l = null;
         try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
             l = session.createQuery("from StructureAnalytique WHERE ID_STRUCTURE=" + _id_selected).list();
             session.delete(l.get(0));
            session.getTransaction().commit();
            fire_component();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public void fire_component() {
        for (Tuple<AbstractModel, JComponent> jc : l) {
            jc.getX().property_change();
        }
    }

    public void add_fire_component(AbstractModel jc, JComponent c) {
        l.add(new Tuple<AbstractModel, JComponent>(jc, c));
    }



}
