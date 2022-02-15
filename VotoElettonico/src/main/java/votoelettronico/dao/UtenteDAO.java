package votoelettronico.dao;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import votoelettronico.bean.*;
import votoelettronico.dbconnection.DBConnection;

public class UtenteDAO implements GenericDAO<Utente>{
	
	 
	
	public Utente get(String id) {

		Utente t = null;
		String query = "Select * FROM Utente Where username = ?";
		
		try {
			
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setString(1, id);
			ResultSet rs=ps.executeQuery();
			while(rs.next())  
				
				if(rs.getString("ruolo").equalsIgnoreCase("Elettore")) {
					t=new Elettore(rs.getString("nome"),rs.getString("cognome"),rs.getString("username")) ;
				}else {
					t= new Scrutinatore(rs.getString("nome"),rs.getString("cognome"),rs.getString("username")) ;
				}
				
			DBConnection.getInstance().closeConnection();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return t;
	}
	
	public Utente get(String id,String psw) {
		Utente t = null;

		MessageDigest digest;
		String sha1=null;
		try {
			digest = MessageDigest.getInstance("SHA-1");
			digest.reset();
			digest.update(psw.getBytes("utf8"));
			sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
		} catch (NoSuchAlgorithmException e) {		
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String query = "Select * FROM Utente Where username = ? AND password = ? ";
		
		try {
			
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setString(1, id);
			ps.setString(2, sha1);
			ResultSet rs=ps.executeQuery();
			while(rs.next())  
				
				if(rs.getString("ruolo").equalsIgnoreCase("Elettore")) {
					t=new Elettore(rs.getString("nome"),rs.getString("cognome"),rs.getString("username")) ;
				}else {
					t= new Scrutinatore(rs.getString("nome"),rs.getString("cognome"),rs.getString("username")) ;
				}
				
			DBConnection.getInstance().closeConnection();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return t;
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
		
		String query ="INSERT INTO Utente(nome,cognome,username,ruolo) VALUES(?,?,?,?);";
		
	}

	public void update(Utente t, String[] params) {
		
		String query = "UPDATE Utente SET nome = ? , cognome = ? WHERE username = ?;";
		
	}

	public void delete(Utente t) {
		String query = "DELETE FROM Utente WHERE username = ?";
		
	}


}
