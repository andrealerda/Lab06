package it.polito.tdp.meteo;

import java.util.List;

import it.polito.tdp.meteo.bean.SimpleCity;
import it.polito.tdp.meteo.db.MeteoDAO;

public class Model {

	private final static int COST = 100;
	private final static int NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN = 3;
	private final static int NUMERO_GIORNI_CITTA_MAX = 6;
	private final static int NUMERO_GIORNI_TOTALI = 15;
	

	MeteoDAO meteoDao;;
	
	public Model() {
		meteoDao = new MeteoDAO();
	}

	public String getUmiditaMedia(int mese) {
		String result = null;
		Double mediaCit = 0.0;
		
		mediaCit =  meteoDao.getAvgRilevamentiLocalitaMese(mese, "Torino");
		result = "Torino umidita media : "+ mediaCit + "\n";
		mediaCit =  meteoDao.getAvgRilevamentiLocalitaMese(mese, "Milano");
		result += "Milano umidita media : "+ mediaCit + "\n";
		mediaCit =  meteoDao.getAvgRilevamentiLocalitaMese(mese, "Genova");
		result += "Genova umidita media : "+ mediaCit + "\n";
		
		return result;
	}

	public String trovaSequenza(int mese) {

		return "TODO!";
	}

	private Double punteggioSoluzione(List<SimpleCity> soluzioneCandidata) {

		double score = 0.0;
		return score;
	}

	private boolean controllaParziale(List<SimpleCity> parziale) {

		return true;
	}

}
