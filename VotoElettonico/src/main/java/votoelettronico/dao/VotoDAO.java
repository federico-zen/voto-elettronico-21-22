package votoelettronico.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;

import votoelettronico.dbconnection.DBConnection;
import votoelettronico.factory.DAOFactory;
import votoelettronico.logger.VotoLogger;
import votoelettronico.model.Candidato;
import votoelettronico.model.Categorico;
import votoelettronico.model.CategoricoPreferenze;
import votoelettronico.model.Ordinale;
import votoelettronico.model.Partecipante;
import votoelettronico.model.Referendum;
import votoelettronico.model.Risultato;
import votoelettronico.model.RisultatoCandidati;
import votoelettronico.model.RisultatoPartiti;
import votoelettronico.model.RisultatoReferendum;
import votoelettronico.model.Sessione;
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
	
	public int getNSchedeBianche(int idSessione) {
		
		String query = "SELECT Count(*) AS conto FROM voto WHERE scheda_bianca =1 AND idSessione =?;";
		int n = 0;
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setInt(1, idSessione);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				n = rs.getInt("conto");
			}	
		} catch (SQLException e) {
			VotoLogger.writeToLog("Error : ", Level.WARNING, e);
		}
		
		return n;
	}
	
	public int getNSchedeRegolari(int idSessione) {
		
		String query = "SELECT Count(*) AS conto FROM votazione WHERE  idSessione =?;";
		int n = 0;
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setInt(1, idSessione);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				n = rs.getInt("conto");
			}	
		} catch (SQLException e) {
			VotoLogger.writeToLog("Error : ", Level.WARNING, e);
		}
		
		return n;
	}
	
	private int getSiReferendum(int idSessione) {
		String query = "SELECT Count(*) AS conto FROM voto WHERE risposta = 1 AND idSessione =?;";
		int n = 0;
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setInt(1, idSessione);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				n = rs.getInt("conto");
			}	
		} catch (SQLException e) {
			VotoLogger.writeToLog("Error : ", Level.WARNING, e);
		}
		
		return n;
	}
	
	private int getNoReferendum(int idSessione) {
		String query = "SELECT Count(*) AS conto FROM voto WHERE risposta = 0 AND idSessione =?;";
		int n = 0;
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setInt(1, idSessione);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				n = rs.getInt("conto");
			}	
		} catch (SQLException e) {
			VotoLogger.writeToLog("Error : ", Level.WARNING, e);
		}
		
		return n;
	}
	
	private Map<Partecipante,Integer> getVotiOrdinale(int idSessione) {
		Map<Partecipante,Integer> m = new HashMap<>();
		String query ="SELECT candidato.id as idC,count(*) as nVoti FROM `voto` JOIN candidato ON voto.idCandidato = candidato.id WHERE ordine = 1 AND idSessione = ? GROUP by candidato.id ORDER BY nVoti DESC ;";
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setInt(1, idSessione);
			ResultSet rs=ps.executeQuery();
			PartecipanteDAO dao = (PartecipanteDAO) DAOFactory.getInstance().getPartecipanteDAO();
			while(rs.next()) {
				DBConnection.getInstance().openConnection();
				Partecipante p = dao.get(Integer.toString(rs.getInt("idC")));
				m.put(p, rs.getInt("nVoti"));
				
			}	
			
			DBConnection.getInstance().closeConnection();
		} catch (SQLException e) {
			VotoLogger.writeToLog("Error : ", Level.WARNING, e);
		}
		
		
		return m;
		
	
	}
	
	private Map<Partecipante,Integer> getVotiCategorico(int idSessione) {
		Map<Partecipante,Integer> m = new HashMap<>();
		String query ="SELECT candidato.id as idC,count(*) as nVoti FROM `voto` JOIN candidato ON voto.idCandidato = candidato.id WHERE   idSessione = ? GROUP by candidato.id ORDER BY nVoti DESC ;";
		try {
			DBConnection.getInstance().openConnection();
			PreparedStatement ps = DBConnection.getInstance().prepara(query);
			ps.setInt(1, idSessione);
			ResultSet rs=ps.executeQuery();
			PartecipanteDAO dao = (PartecipanteDAO) DAOFactory.getInstance().getPartecipanteDAO();
			while(rs.next()) {
				DBConnection.getInstance().openConnection();
				Partecipante p = dao.get(Integer.toString(rs.getInt("idC")));
				m.put(p, rs.getInt("nVoti"));
				
			}	
			
			DBConnection.getInstance().closeConnection();
		} catch (SQLException e) {
			VotoLogger.writeToLog("Error : ", Level.WARNING, e);
		}
		
		
		return m;
		
	
	}
	
	
	public Risultato getRisultato(Sessione s) {
		Risultato r=null;
		UtenteDAO dao = (UtenteDAO) DAOFactory.getInstance().getUtenteDAO();
		int nElettori = dao.getNElettori();
		int idS = s.getId();
		String mod_v = s.getMod_vittoria();
		int nVotantiR = this.getNSchedeRegolari(s.getId());
		int nVotantiB = this.getNSchedeBianche(s.getId());
		
		switch (s.getMod_voto()) {
		case "Referendum":
			r = new RisultatoReferendum(nVotantiR, nVotantiB, this.getSiReferendum(idS), this.getNoReferendum(idS), mod_v,nElettori);
			break;
		case "Ordinale-Partiti":
			//
			r = new RisultatoPartiti(nVotantiR, nVotantiB, mod_v, nElettori,this.getVotiOrdinale(idS) );		
			
			break;
		case "Ordinale-Candidati":
			r = new RisultatoCandidati(nVotantiR, nVotantiB, mod_v, nElettori,this.getVotiOrdinale(idS) );
			
			break;
		case "Categorico-Partiti":
			r = new RisultatoPartiti(nVotantiR, nVotantiB, mod_v, nElettori,this.getVotiCategorico(idS) );
			break;
		case "Categorico-Candidati":
				
			
			r = new RisultatoCandidati(nVotantiR, nVotantiB, mod_v, nElettori,this.getVotiCategorico(idS) );
			
			break;
		case "Categorico-Preferenze":
			r = new RisultatoCandidati(nVotantiR, nVotantiB, mod_v, nElettori,this.getVotiCategorico(idS) );
			break;

		default:
			break;
		}		
		
		
		return r;
	}
	
	

}
