package BDD;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connection_Request
{
	
	private Connection_Request() { connect(); }
	public static Connection_Request instance_ = new Connection_Request();
	public Connection con_;
	
	public static Connection_Request getInstance()
	{
		return instance_;
	}
	
	public void connect()
	{
		if (con_ == null)
		{
		}
	}
	
	public Connection getConnection()
	{
		return con_;
	}
}
