package votoelettronico.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import votoelettronico.bean.*;
import votoelettronico.dbconnection.DBConnection;

public class PartecipanteDAO implements GenericDAO<Partecipante> {

	@Override
	public Partecipante get(String id) {
		String query = "SELECT * FROM candidato WHERE id = ?";
		return null;
	}

	@Override
	public List<Partecipante> getAll() {
		List<Partecipante> l = new ArrayList<Partecipante>();
		String query = "SELECT * FROM candidato";
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			 ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Partecipante t;
				if(rs.getBoolean("is_p")) {
					t= new Partito(rs.getString("nome"));
				}else {
					t=new Candidato(rs.getString("nome"),rs.getString("cognome"));
				}
				
				l.add(t);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return l;
	}

	@Override
	public void save(Partecipante t) {
		
		
	}

	@Override
	public void update(Partecipante t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Partecipante t) {
		// TODO Auto-generated method stub
		
	}

}
