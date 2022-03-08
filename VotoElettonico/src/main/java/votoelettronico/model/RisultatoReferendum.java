package votoelettronico.model;

public class RisultatoReferendum extends Risultato {
	
	private int nSi;
	private int nNo;
	
	public RisultatoReferendum(int nVotanti, int nAstenuti,int nSi,int nNo,String vittoria,int nElettori) {
		super(nVotanti, nAstenuti,vittoria,nElettori);
		this.nSi = nSi;
		this.nNo =nNo;
	}

	public int getnSi() {
		return nSi;
	}

	public void setnSi(int nSi) {
		this.nSi = nSi;
	}

	public int getnNo() {
		return nNo;
	}

	public void setnNo(int nNo) {
		this.nNo = nNo;
	}

	@Override
	public String getVincitore() {
	StringBuilder sb = new StringBuilder();
		
		
		switch (vittoria) {
		case "Quorum":
			if(nVotanti>(nElettori/2) ) {
				if(nNo > nSi) {
					sb.append("Non Favorevole \n");
				}else if(nNo == nSi) {
					sb.append("Pareggio \n");
					
				}else {
					sb.append("Favorevole \n");
				}
			}else {
				sb.append("Non si ha la maggioranza \n");
			}
			break;
		case "Senza-Quorum" :
			if(nNo > nSi) {
				sb.append("Non Favorevole \n");
			}else if(nNo == nSi) {
				sb.append("Pareggio \n");
				
			}else {
				sb.append("Favorevole \n");
			}
			break;

		default:
			break;
		}
		
		sb.append("Schede Bianche :" + nAstenuti);

		return sb.toString();
	}



	@Override
	public String toString() {
		return "RisultatoReferendum [nSi=" + nSi + ", nNo=" + nNo + ", nVotanti=" + nVotanti + ", nAstenuti="
				+ nAstenuti + "]";
	}
	
	
}
