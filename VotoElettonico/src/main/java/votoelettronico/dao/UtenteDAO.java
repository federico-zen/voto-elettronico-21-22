package votoelettronico.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import votoelettronico.bean.Utente;
import votoelettronico.dbconnection.DBConnection;

public class UtenteDAO implements GenericDAO<Utente>{
	
	 

	public Utente get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Utente> getAll() {
		List<Utente> l= new ArrayList<Utente>();
		try {
			
			
			PreparedStatement ps = DBConnection.getInstance().prepara("SELECT * FROM Student");
			ResultSet rs=ps.executeQuery();
			while(rs.next())  
				//l.add(new Student(rs.getString(1), rs.getString(2), rs.getString(3))) ;
			DBConnection.getInstance().closeConnection();		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return l;
	}

	public void save(Utente t) {
		// TODO Auto-generated method stub
		
	}

	public void update(Utente t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Utente t) {
		// TODO Auto-generated method stub
		
	}


}
