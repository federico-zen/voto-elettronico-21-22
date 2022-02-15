package votoelettronico.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import votoelettronico.bean.*;
import votoelettronico.dbconnection.DBConnection;

public class UtenteDAO implements GenericDAO<Utente>{
	
	 

	public Utente get(String id) {
		
		return null;
	}

	public List<Utente> getAll() {
		
		List<Utente> l= new ArrayList<Utente>();
		try {
			
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara("SELECT * FROM Utente");
			ResultSet rs=ps.executeQuery();
			while(rs.next())  
				
				if(rs.getString("ruolo").equalsIgnoreCase("Elettore")) {
					l.add(new Elettore(rs.getString("nome"),rs.getString("cognome"),rs.getString("username"))) ;
				}else {
					l.add(new Scrutinatore(rs.getString("nome"),rs.getString("cognome"),rs.getString("username"))) ;
				}
				
			DBConnection.getInstance().closeConnection();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return l;
	}

	public void save(Utente t) {
		
		
	}

	public void update(Utente t, String[] params) {
		
		
	}

	public void delete(Utente t) {
		
		
	}


}
