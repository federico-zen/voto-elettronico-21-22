package votoelettronico.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RisultatoPartiti extends Risultato {
		
	Partito vincitore;
	int nVotiC;
	Map<Partecipante,Integer> voti;

	public RisultatoPartiti(int nVotanti, int nAstenuti, String vittoria, int nElettori,Map<Partecipante,Integer> voti) {
		super(nVotanti, nAstenuti, vittoria, nElettori);
		this.voti =voti;
	}

	
	private Partito cercaVincitore() {
		List<Partecipante> lv = new ArrayList<>();
		
		int maxVoti = 0;
		for (Map.Entry<Partecipante, Integer> entry : voti.entrySet()) {
			if (entry.getValue() > maxVoti) {
				maxVoti = entry.getValue();
				lv.clear();
				lv.add(entry.getKey());
			} else if (entry.getValue() == maxVoti) {
				lv.add(entry.getKey());
			}
		}
		
		
		if(lv.size()>1) {
			return null;
		}else {
			nVotiC = maxVoti;
			return (Partito) lv.get(0);
		}
	}
	
	
	@Override
	public String getVincitore() {
		StringBuilder sb = new  StringBuilder();
		vincitore = cercaVincitore();
		
		
			switch (vittoria) {
			case "Maggioranza":
				if(vincitore == null) {
					sb.append("Pareggio");
				}else{
					sb.append("Il vincitore e' " + vincitore.getNome() + " "  +"\n");
				}
				
				break;
			case "Maggioranza-Assoluta":
				if(nVotiC >= (nVotanti/2) +1 ) {
					if(vincitore == null) {
						sb.append("Pareggio");
					}else{
						sb.append("Il vincitore e' " + vincitore.getNome() + " "  +"\n");
					}
				}else {
					sb.append("Non si è  raggiunta la Maggioranza Assoluta \n");
				}
				break;

			default:
				break;
			}
			sb.append("Schede Bianche :" + nAstenuti);
			
		
		
		
		return sb.toString();
	}

}
