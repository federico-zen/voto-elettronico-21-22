package votoelettronico.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import votoelettronico.dbconnection.DBConnection;
import votoelettronico.logger.VotoLogger;
import votoelettronico.model.*;

public class PartecipanteDAO implements GenericDAO<Partecipante> {

	@Override
	public Partecipante get(String id) {
		return null;
	}

	@Override
	public List<Partecipante> getAll() {		
		return null;
	}

	@Override
	public void save(Partecipante t) {		
	}

	@Override
	public void update(Partecipante t, String[] params) {
		
	}

	@Override
	public void delete(Partecipante t) {
				
	}
	
	public List<Candidato> getCandidati(int id){
		List<Candidato> l = new ArrayList<Candidato>();
			
		String query = "SELECT C.id AS id_candidato,C.nome,C.cognome FROM candidato AS P JOIN candidato AS C ON P.id = C.idPartito WHERE C.idPartito= ?";
			try {
			
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setInt(1, id);
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
				Partito p = new Partito(rs.getInt("id"),rs.getString("nome"),getCandidati(rs.getInt("id")));
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
				l = new Partito(rs.getInt("id"),rs.getString("nome"),getCandidati(rs.getInt("id")));
			}
				
			DBConnection.getInstance().closeConnection();		
		} catch (SQLException e) {
			VotoLogger.writeToLog("Error : ", Level.WARNING, e);
		}
		
		return l;
	}
	
	

}
