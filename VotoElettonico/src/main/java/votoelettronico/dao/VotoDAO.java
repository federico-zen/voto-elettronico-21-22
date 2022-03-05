package votoelettronico.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import votoelettronico.dbconnection.DBConnection;
import votoelettronico.logger.VotoLogger;
import votoelettronico.model.Categorico;
import votoelettronico.model.CategoricoPreferenze;
import votoelettronico.model.Ordinale;
import votoelettronico.model.Partecipante;
import votoelettronico.model.Referendum;
import votoelettronico.model.Voto;

public class VotoDAO implements GenericDAO<Voto> {

	@Override
	public Voto get(String id) {
		//Non usato
		return null;
	}

	@Override
	public List<Voto> getAll() {
		//Non usato
		return null;
	}

	@Override
	public void save(Voto t) {
		//Non usato
		
	}

	@Override
	public void update(Voto t, String[] params) {
		//Non usato
		
	}

	@Override
	public void delete(Voto t) {
		//Non usato
		
	}
	
	
	public void save(Voto t , int idSessione) {
		try {
		DBConnection.getInstance().openConnection();
		PreparedStatement ps = null;
		if(t.isBianca()) {
			//Scheda Bianca
			ps = DBConnection.getInstance().prepara("INSERT INTO voto (idSessione,scheda_bianca) VALUES (?,1)");
			ps.setInt(1, idSessione);
			ps.executeUpdate();
		
		}else {
			switch (t.getTipo()) {
			
				case "Referendum":
					Referendum r = (Referendum) t;
					ps = DBConnection.getInstance().prepara("INSERT INTO voto (idSessione,risposta,scheda_bianca) VALUES (?,?,0)");
					ps.setInt(1, idSessione);
					ps.setBoolean(2, r.getRisposta());	
					ps.executeUpdate();
					break;
					
				case "Ordinale-Candidati":
				case "Ordinale-Partiti":	
					Ordinale op = (Ordinale) t;
					int pos = 1;
					for (Partecipante partecipante : op) {
						int idC = partecipante.getId();
						ps = DBConnection.getInstance().prepara("INSERT INTO voto (idSessione,ordine,scheda_bianca,idCandidato) VALUES (?,?,0,?)");
						ps.setInt(1, idSessione);
						ps.setInt(2, pos);
						ps.setInt(3, idC);
						ps.executeUpdate();
						pos++;
					}
					
					break;
			
				case "Categorico-Candidati":
				case "Categorico-Partiti":
					Categorico cp = (Categorico) t;
					int idpp = cp.getPreferenza().getId();
					ps = DBConnection.getInstance().prepara("INSERT INTO voto (idSessione,scheda_bianca,idCandidato) VALUES (?,0,?)");
					ps.setInt(1, idSessione);
					ps.setInt(2, idpp);
					ps.executeUpdate();
					
					break;

				case "Categorico-Preferenze":
					CategoricoPreferenze cP = (CategoricoPreferenze) t;
					for (Partecipante partecipante : cP) {
						int idC = partecipante.getId();
						ps = DBConnection.getInstance().prepara("INSERT INTO voto (idSessione,scheda_bianca,idCandidato) VALUES (?,0,?)");
						ps.setInt(1, idSessione);
						ps.setInt(2, idC);
						ps.executeUpdate();
					}
					break;
	
				default:
					break;
			}		
		}

		DBConnection.getInstance().closeConnection();
		}catch(Exception e) {
			VotoLogger.writeToLog("Error : ",Level.WARNING,e);
		}
	}
	
	public int getNSchedeBianche() {
		
		String query = "SELECT Count(*) AS conto FROM voto WHERE scheda_bianca =1;";
		int n = 0;
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				n = rs.getInt("conto");
			}	
		} catch (SQLException e) {
			VotoLogger.writeToLog("Error : ", Level.WARNING, e);
		}
		
		return n;
	}
	
	public int getNSchedeRegolari() {
		
		String query = "SELECT Count(*) AS conto FROM voto WHERE scheda_bianca =0';";
		int n = 0;
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				n = rs.getInt("conto");
			}	
		} catch (SQLException e) {
			VotoLogger.writeToLog("Error : ", Level.WARNING, e);
		}
		
		return n;
	}
	

}
