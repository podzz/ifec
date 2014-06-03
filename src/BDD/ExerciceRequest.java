package BDD;

import comptedit_db.Entreprise;
import comptedit_db.Exercice;
import comptedit_db.HibernateUtil;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class ExerciceRequest
{
	private Connection con_;

	public ExerciceRequest()
	{
		con_ = Connection_Request.getInstance().getConnection();
	}

	public boolean add_exercice(String name_exercice, String name_entreprise)
	{
		try
		{
			Statement st = con_.createStatement();
			ResultSet r = st.executeQuery("SELECT COUNT(EXERCICE.NAME_TABLE) AS counter FROM EXERCICE WHERE EXERCICE.NAME_TABLE='" + "EXERCICE_" + name_exercice + "'");
			if (r.next())
			{
				int count = r.getInt(1);
				if (count > 0)
					return false;
				else
				{
					st.execute("INSERT INTO EXERCICE (NAME_ENTREPRISE, NAME_TABLE) VALUES ('" + name_entreprise + "', '" + "EXERCICE_" + name_exercice + "')");
					st.execute(getSQLStringFEC("EXERCICE_" + name_exercice));
					st.close();
					getListExercice();
					return true;
					
				}
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public Object[] getListExercice()
	{
            List<Integer> list_name = new ArrayList<Integer>();
               List resultList = executeQuery("from Exercice");
               for (Object e : resultList)
                   list_name.add(((Exercice) e).getFecId());
            return list_name.toArray();
	}

	public String getSQLStringFEC(String nom_table)
	{
		String sql = "CREATE TABLE " + nom_table + " (" +
												   "    id INT PRIMARY KEY NOT NULL,"
												   + "  nom VARCHAR(100)"
												   + ")";
		return sql;
	}

	public void delete_exercice(String text)
	{
		try
		{
			Statement st = con_.createStatement();
			st.execute("DROP TABLE " + text);
			st.execute("DELETE FROM EXERCICE WHERE NAME_TABLE='" + text + "'");
			st.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	public void update_exercice_name(String old_name, String new_name)
	{
		try
		{			
			Statement st = con_.createStatement();
			st.execute("UPDATE EXERCICE SET NAME_TABLE='" + new_name + "' WHERE NAME_TABLE='" + old_name + "'");
			st.execute("SELECT * INTO " + new_name + " FROM " + old_name);
			st.execute("DROP TABLE " + old_name);
			st.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}
         private List executeQuery(String q)
        {
             List<String> resultList = new ArrayList<String>();
            try
            {
               Session session = HibernateUtil.getSessionFactory().openSession();
               session.beginTransaction();
               List l = session.createQuery(q).list();
               }
            catch(HibernateException e)
            {
                e.printStackTrace();
            }
            return resultList;
        }
}
