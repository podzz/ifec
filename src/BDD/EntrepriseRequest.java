package BDD;

import comptedit_db.Entreprise;
import comptedit_db.HibernateUtil;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;

public class EntrepriseRequest {

    private static EntrepriseRequest instance_ = new EntrepriseRequest();

    public boolean check_entreprise(String name) {
        boolean check = false;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List<Entreprise> l = session.createQuery("from Entreprise").list();
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

    public boolean add_entreprise(Entreprise ent) {
        boolean check = check_entreprise(ent.getNameEntreprise());
        if (!check && !ent.getNameEntreprise().equals("")) {
            try {
                Session session = HibernateUtil.getSessionFactory().getCurrentSession();
                session.beginTransaction();
                session.save(ent);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
        return !check;
    }

    public List<Entreprise> list_entreprise() {
        List<Entreprise> l = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            l = session.createQuery("from Entreprise").list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return l;
    }

    public void delete_entreprise(long id) {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Entreprise e = (Entreprise) session.get(Entreprise.class, id);
            session.delete(e);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public void update_entreprise(long id, Entreprise update_entreprise) {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            Entreprise e = (Entreprise) session.get(Entreprise.class, id);
            e.setDescriptionEntreprise(update_entreprise.getDescriptionEntreprise());
            e.setNameEntreprise(update_entreprise.getNameEntreprise());
            session.update(e);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}
