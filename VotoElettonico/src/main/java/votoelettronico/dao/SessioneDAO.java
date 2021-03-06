package votoelettronico.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
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
			
			ps = DBConnection.getInstance().prepara(query);
			ps.setInt(1,Integer.parseInt(id));
			rs = ps.executeQuery();
			
			while(rs.next()) {
				s = new Sessione(rs.getInt("id"), rs.getString("nome"), rs.getString("modalita_voto"), rs.getString("modalita_vittoria"), rs.getString("domanda"), rs.getBoolean("stato"),
			new ArrayList<Partito>());
			}
			
			if(!rs.getString("modalita_voto").equalsIgnoreCase("referendum")) {
				//prendo i singoli partiti che sono sul DB e sono nella Sessione
				
				query = "SELECT C.id as idPartito FROM partecipazione AS P JOIN candidato AS C ON P.idCandidato = C.id WHERE C.is_p = 1";
				ps = DBConnection.getInstance().prepara(query);
				rs = ps.executeQuery();
				PartecipanteDAO dao = (PartecipanteDAO) DAOFactory.getInstance().getPartecipanteDAO();
				
				while(rs.next()) {
					Partito p =dao.getPartito(rs.getInt("idPartito"));
					s.addPartito(p);
				}
			}
			
			
			
					
		} catch (SQLException e) {
			VotoLogger.writeToLog("Error : ", Level.WARNING, e);
		}
		return s;
	}
	
	
	public Sessione get(int id) {
		return this.get(Integer.toString(id));
	}

	@Override
	public List<Sessione> getAll() {
		List<Sessione> l = new ArrayList<>();
		String query ="SELECT * FROM sessione ";
		
		try {
			
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Sessione s =new Sessione(rs.getInt("id"), rs.getString("nome"), rs.getString("modalita_voto"), rs.getString("modalita_vittoria"), rs.getString("domanda"), rs.getBoolean("stato"),
						new ArrayList<Partito>());
				
				if(!rs.getString("modalita_voto").equalsIgnoreCase("referendum")) {
					//prendo i singoli partiti che sono sul DB e sono nella Sessione
					
					
					
					query = "SELECT C.id as idPartito FROM partecipazione AS P JOIN candidato AS C ON P.idCandidato = C.id ";
					PreparedStatement ps2 = DBConnection.getInstance().prepara(query);
					ResultSet rs2 = ps2.executeQuery();
					PartecipanteDAO dao = (PartecipanteDAO) DAOFactory.getInstance().getPartecipanteDAO();
				
					while(rs2.next()) {
						Partito p =dao.getPartito(rs2.getInt("idPartito"));
						s.addPartito(p);
					}
				
				}
			
				l.add(s);
				
			}
			
			
			
			
					
		} catch (SQLException e) {
			VotoLogger.writeToLog("Error : ", Level.WARNING, e);
		}
		
		
		return l;
	}

	@Override
	public void save(Sessione t) {
		String query = "INSERT INTO sessione (nome,modalita_voto,modalita_vittoria,domanda,stato) VALUES(?,?,?,?,?)";
		
		try {
			
			PreparedStatement ps = DBConnection.getInstance().prepara(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, t.getNome());
			ps.setString(2,t.getMod_voto() );
			ps.setString(3,t.getMod_vittoria());
			ps.setString(4,t.getDomanda());
			ps.setBoolean(5, t.getStato());
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			while(rs.next()) {
				t.setId(rs.getInt(1));
			}
			
			//Inserisci le Partecipazioni dei partiti
			query = "INSERT INTO partecipazione (idSessione,idCandidato) VALUES (?,?)";
			
			
			for (Partito partito : t) {
				
				ps = DBConnection.getInstance().prepara(query);
				ps.setInt(1, t.getId());
				ps.setInt(2, partito.getId());
				ps.executeUpdate();
				
			}
			
		}catch (SQLException e) {
			VotoLogger.writeToLog("Error : ", Level.WARNING, e);
		}
		
		
		
	}

	@Override
	public void update(Sessione t, String[] params) {
		//non usata
		
	}

	@Override
	public void delete(Sessione t) {
		//non usato
		
	}
	
	public List<Sessione> getAllActive() {
		List<Sessione> l = new ArrayList<>();
		String query ="SELECT * FROM sessione WHERE stato = 1";
		
		try {
			
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Sessione s =new Sessione(rs.getInt("id"), rs.getString("nome"), rs.getString("modalita_voto"), rs.getString("modalita_vittoria"), rs.getString("domanda"), rs.getBoolean("stato"),
						new ArrayList<Partito>());
			
				//prendo i singoli partiti che sono sul DB e sono nella Sessione
				
				query = "SELECT C.id as idPartito FROM partecipazione AS P JOIN candidato AS C ON P.idCandidato = C.id WHERE C.is_p = 1";
				PreparedStatement ps2 = DBConnection.getInstance().prepara(query);
				ResultSet rs2 = ps2.executeQuery();
				PartecipanteDAO dao = (PartecipanteDAO) DAOFactory.getInstance().getPartecipanteDAO();
				
				while(rs2.next()) {
					Partito p =dao.getPartito(rs2.getInt("idPartito"));
					s.addPartito(p);
				}
				
				
			
				l.add(s);
			}
			
			
			
			
				
		} catch (SQLException e) {
			VotoLogger.writeToLog("Error : ", Level.WARNING, e);
		}
		
		
		return l;
	}
	
	public List<Sessione> getAllClosed() {
		List<Sessione> l = new ArrayList<>();
		String query ="SELECT * FROM sessione WHERE stato = 0";
		
		try {
			
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Sessione s =new Sessione(rs.getInt("id"), rs.getString("nome"), rs.getString("modalita_voto"), rs.getString("modalita_vittoria"), rs.getString("domanda"), rs.getBoolean("stato"),
						new ArrayList<Partito>());
			
				//prendo i singoli partiti che sono sul DB e sono nella Sessione
				
				query = "SELECT C.id as idPartito FROM partecipazione AS P JOIN candidato AS C ON P.idCandidato = C.id WHERE C.is_p = 1";
				PreparedStatement ps2 = DBConnection.getInstance().prepara(query);
				ResultSet rs2 = ps2.executeQuery();
				PartecipanteDAO dao = (PartecipanteDAO) DAOFactory.getInstance().getPartecipanteDAO();
				
				while(rs2.next()) {
					Partito p =dao.getPartito(rs2.getInt("idPartito"));
					s.addPartito(p);
				}
				
				
			
				l.add(s);
			}
			
			
			
			
				
		} catch (SQLException e) {
			VotoLogger.writeToLog("Error : ", Level.WARNING, e);
		}
		
		
		return l;
	}


	public void closeSession(Sessione s) {
		String query ="UPDATE sessione SET stato = 0 WHERE id = ?";
		try {
			
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setInt(1, s.getId());
			ps.executeUpdate();
			
		}catch (SQLException e){
			VotoLogger.writeToLog("Error : ", Level.WARNING, e);
		}
		
	}
	
	public List<Sessione> getAllActiveNotVoted(String CFElettore) {
		List<Sessione> l = new ArrayList<>();
		String query ="select * from sessione as S where S.stato = 1 AND not exists (select * from votazione where idUtente = ? and idSessione = S.id)";
		
		try {
			
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setString(1, CFElettore);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Sessione s =new Sessione(rs.getInt("id"), rs.getString("nome"), rs.getString("modalita_voto"), rs.getString("modalita_vittoria"), rs.getString("domanda"), rs.getBoolean("stato"),
						new ArrayList<Partito>());
			
				//prendo i singoli partiti che sono sul DB e sono nella Sessione
				
				query = "SELECT C.id as idPartito FROM partecipazione AS P JOIN candidato AS C ON P.idCandidato = C.id WHERE C.is_p = 1";
				PreparedStatement ps2 = DBConnection.getInstance().prepara(query);
				ResultSet rs2 = ps2.executeQuery();
				PartecipanteDAO dao = (PartecipanteDAO) DAOFactory.getInstance().getPartecipanteDAO();
				
				while(rs2.next()) {
					Partito p =dao.getPartito(rs2.getInt("idPartito"));
					s.addPartito(p);
				}
				
				
			
				l.add(s);
			}
			
			
			
			
					
		} catch (SQLException e) {
			VotoLogger.writeToLog("Error : ", Level.WARNING, e);
		}
		
		
		return l;
	}
	
	public void addVotazione(Sessione s , Utente u) {
		
		String query= "INSERT INTO votazione (idUtente,idSessione) VALUES (?,?)";
		
		try {
			
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setString(1,u.getCodiceFiscale());
			ps.setInt(2, s.getId());
			ps.executeUpdate();
			
			
		}catch (SQLException e) {
			VotoLogger.writeToLog("Error : ", Level.WARNING, e);
		}
		
	}

}
