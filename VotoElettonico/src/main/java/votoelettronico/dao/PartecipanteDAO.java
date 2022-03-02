package votoelettronico.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;


import votoelettronico.dbconnection.DBConnection;
import votoelettronico.logger.VotoLogger;
import votoelettronico.model.*;

public class PartecipanteDAO implements GenericDAO<Partecipante> {

	@Override
	public Partecipante get(String id) {
		Partecipante t =null;
		
		String query = "SELECT * FROM candidato WHERE id =?";
			
		try {
			
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if(rs.getBoolean("is_p")) {
					t= new Partito(rs.getInt("id"), rs.getString("nome"),getCandidati(rs.getInt("id")),rs.getBlob("logo"));
				}else {
					t= new Candidato(rs.getInt("id"), rs.getString("nome"), rs.getString("cognome"));
				}
				
				
			}
			
			
			
		}catch(Exception e) {
			VotoLogger.writeToLog("Error : ", Level.WARNING, e);
		}
		
		return t;
	}

	@Override
	public List<Partecipante> getAll() {		
		//non utilizzato
		return null;
	}

	@Override
	public void save(Partecipante t) {
		//non utilizzato
	}

	@Override
	public void update(Partecipante t, String[] params) {
		String query;
		try {
			if(t.isPartito()) {
				Partito p = (Partito) t;
				query = "UPDATE candidato SET nome = ?,logo = ? Where id = ?";
				
				DBConnection.getInstance().openConnection();
				PreparedStatement ps = DBConnection.getInstance().prepara(query);
				ps.setString(1, p.getNome());
				ps.setBlob(2, p.getLogo());
				ps.setInt(3, p.getId());
				ps.executeUpdate();			
				DBConnection.getInstance().closeConnection();
			}else {
				Candidato c = (Candidato) t;
				query = "UPDATE candidato SET nome = ?,cognome = ?  Where id = ?";
				
				DBConnection.getInstance().openConnection();
				PreparedStatement ps = DBConnection.getInstance().prepara(query);
				ps.setString(1, c.getNome());
				ps.setString(2, c.getCognome());
				ps.setInt(3, c.getId());
				ps.executeUpdate();	
				DBConnection.getInstance().closeConnection();
			}
		} catch (SQLException e) {
			VotoLogger.writeToLog("Error : ", Level.WARNING, e);
		}
	}

	@Override
	public void delete(Partecipante t) {
			//cancello il partecipante singolo oppure il partito con i suoi membri	
		String query;
		try {
			if(t.isPartito()) { // è Partito
				PreparedStatement ps;
				query = "DELETE FROM candidato WHERE idPartito=?";
				DBConnection.getInstance().openConnection();
				 ps = DBConnection.getInstance().prepara(query);
				ps.setInt(1, t.getId());
				ps.execute();
				
				query = "DELETE FROM candidato WHERE id=?";
				DBConnection.getInstance().openConnection();
				ps = DBConnection.getInstance().prepara(query);
				ps.setInt(1, t.getId());
				ps.execute();
				
			}else {
				int id = t.getId();
				query = "DELETE FROM candidato WHERE id = ?";
				
				DBConnection.getInstance().openConnection();
				PreparedStatement ps = DBConnection.getInstance().prepara(query);
				ps.setInt(1, id);
				ps.execute();
				
			}
			
			DBConnection.getInstance().closeConnection();
		} catch (SQLException e) {
			VotoLogger.writeToLog("Error : ", Level.WARNING, e);
		}
	}
	
	public List<Candidato> getCandidati(int idPartito){
		List<Candidato> l = new ArrayList<Candidato>();
			
		String query = "SELECT C.id AS id_candidato,C.nome,C.cognome FROM candidato AS P JOIN candidato AS C ON P.id = C.idPartito WHERE C.idPartito= ?";
			try {
			
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setInt(1, idPartito);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				
				Candidato c = new Candidato(rs.getInt("id_candidato"),rs.getString("nome"),rs.getString("cognome"));
 				l.add(c);
			}
					
			DBConnection.getInstance().closeConnection();		
		} catch (SQLException e) {
			VotoLogger.writeToLog("Error : ", Level.WARNING, e);
		}
		return l;
	}
	
	public List<Partito> getPartiti(){
		List<Partito> l = new ArrayList<Partito>();
		
		String query ="SELECT * FROM candidato WHERE is_p = 1 ";
		
		try {
			
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Partito p = new Partito(rs.getInt("id"),rs.getString("nome"),getCandidati(rs.getInt("id")),rs.getBlob("logo"));
				l.add(p);
			}
				
			DBConnection.getInstance().closeConnection();		
		} catch (SQLException e) {
			VotoLogger.writeToLog("Error : ", Level.WARNING, e);
		}
		
		return l;
	}
	
	public Partito getPartito(int id){
		Partito l = null;
		
		String query ="SELECT * FROM candidato WHERE is_p = 1 AND candidato.id = ?";
		
		try {
			
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				l = new Partito(rs.getInt("id"),rs.getString("nome"),getCandidati(rs.getInt("id")),rs.getBlob("logo"));
			}
				
			DBConnection.getInstance().closeConnection();		
		} catch (SQLException e) {
			VotoLogger.writeToLog("Error : ", Level.WARNING, e);
		}
		
		return l;
	}
	
	
	public void savePartito(Partito p) {
		
		String query = "INSERT INTO candidato(nome,is_p,logo) VALUES (?,1,?)";
		
		
		try {
			
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query, Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, p.getNome());
			ps.setBlob(2, p.getLogo());
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			
			while(rs.next()) {
				p.setId(rs.getInt(1));
			}
			
			//Aggiungo ogni candidato
			
			Iterator<Candidato> it = p.iterator();
			
			while(it.hasNext()) {
				Candidato temp = it.next();
				saveCandidato(temp, p.getId());
			}
			DBConnection.getInstance().closeConnection();		
		} catch (SQLException e) {
			VotoLogger.writeToLog("Error : ", Level.WARNING, e);
		}
		
	}
	
	public void saveCandidato(Candidato c ,int p) {
		String query = "INSERT INTO candidato(nome,cognome,is_p,idPartito) VALUES (?,?,0,?)";
				
		try {
			
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setString(1, c.getNome());
			ps.setString(2, c.getCognome());
			ps.setInt(3, p);
			ps.executeUpdate();
			DBConnection.getInstance().closeConnection();		
		} catch (SQLException e) {
			VotoLogger.writeToLog("Error : ", Level.WARNING, e);
		}
	}
	
	public void updateCandidato(Candidato c , int p) {
		String query = "UPDATE candidato SET nome = ?, cognome = ? ,idPartito = ? WHERE id = ?";
		
		try {
			
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setString(1, c.getNome());
			ps.setString(2, c.getCognome());
			ps.setInt(3, p);
			ps.setInt(4, c.getId());
			ps.executeUpdate();
			DBConnection.getInstance().closeConnection();		
		} catch (SQLException e) {
			VotoLogger.writeToLog("Error : ", Level.WARNING, e);
		}
	}
	
}
