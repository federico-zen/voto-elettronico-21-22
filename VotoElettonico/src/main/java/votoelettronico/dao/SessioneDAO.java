package votoelettronico.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import votoelettronico.dbconnection.DBConnection;
import votoelettronico.factory.DAOFactory;
import votoelettronico.logger.VotoLogger;
import votoelettronico.model.Partito;
import votoelettronico.model.Sessione;
import votoelettronico.model.Utente;

public class SessioneDAO implements GenericDAO<Sessione>{

	@Override
	public Sessione get(String id) {
		String query ="SELECT * FROM sessione WHERE id = ?";
		Sessione s = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			DBConnection.getInstance().openConnection();
			ps = DBConnection.getInstance().prepara(query);
			ps.setInt(1,Integer.parseInt(id));
			rs = ps.executeQuery();
			
			while(rs.next()) {
				s = new Sessione(rs.getInt("id"), rs.getString("nome"), rs.getString("modalita_voto"), rs.getString("modalità_vittoria"), rs.getString("domanda"), rs.getBoolean("stato"),
			new ArrayList<Partito>());
			}
			
			
			//prendo i singoli partiti che sono sul DB e sono nella Sessione
			
			query = "SELECT C.id as idPartito FROM partecipazione AS P JOIN candidato AS C ON P.idCandidato = C.id WHERE C.is_p = 1";
			ps = DBConnection.getInstance().prepara(query);
			rs = ps.executeQuery();
			PartecipanteDAO dao = (PartecipanteDAO) DAOFactory.getInstance().getPartecipanteDAO();
			
			while(rs.next()) {
				Partito p =dao.getPartito(rs.getInt("idPartito"));
				s.addPartito(p);
			}
			
			
			DBConnection.getInstance().closeConnection();		
		} catch (SQLException e) {
			VotoLogger.writeToLog("Error : ", Level.WARNING, e);
		}
		return s;
	}

	@Override
	public List<Sessione> getAll() {
		List<Sessione> l = new ArrayList<>();
		String query ="SELECT * FROM sessione ";
		
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				l.add(new Sessione(rs.getInt("id"), rs.getString("nome"), rs.getString("modalita_voto"), rs.getString("modalità_vittoria"), rs.getString("domanda"), rs.getBoolean("stato"),
			new ArrayList<Partito>()));
			}
			
			//prendo i singoli partiti che sono sul DB e sono nella Sessione
			
			
			DBConnection.getInstance().closeConnection();		
		} catch (SQLException e) {
			VotoLogger.writeToLog("Error : ", Level.WARNING, e);
		}
		
		
		return l;
	}

	@Override
	public void save(Sessione t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Sessione t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Sessione t) {
		// TODO Auto-generated method stub
		
	}

}
