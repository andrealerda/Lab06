package it.polito.tdp.meteo.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

import it.polito.tdp.meteo.bean.Citta;
import it.polito.tdp.meteo.bean.Rilevamento;
import javafx.scene.chart.XYChart.Data;

public class MeteoDAO {

	public List<Citta> getAllCitta() {
		String sql = "SELECT DISTINCT localita FROM situazione ORDER BY localita";

		List<Citta> result = new ArrayList<>();

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(new Citta(res.getString("localita")));
			}

			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
	public List<Rilevamento> getAllRilevamenti() {

		final String sql = "SELECT Localita, Data, Umidita FROM situazione ORDER BY data ASC";

		List<Rilevamento> rilevamenti = new ArrayList<Rilevamento>();

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Rilevamento r = new Rilevamento(rs.getString("Localita"), rs.getDate("Data"), rs.getInt("Umidita"));
				rilevamenti.add(r);
			}

			conn.close();
			return rilevamenti;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public List<Rilevamento> getAllRilevamentiLocalitaMese(int mese, String localita) {
		
		final String sql = "SELECT Data, Umidita FROM situazione as s where s.Data>=? and s.Data<? and s.Localita=? ORDER BY Data ASC ;";
		String d1 = "2013-"+mese+"-01";
		mese++;
		String d2 = "2013-"+mese+"-01";
		
		List<Rilevamento> rilevamenti = new LinkedList<Rilevamento>();

		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setDate(1, Date.valueOf(d1));
			st.setDate(2, Date.valueOf(d2));
			st.setString(3, localita);
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Rilevamento ril = new Rilevamento(localita, rs.getDate("Data"), rs.getInt("Umidita"));
				rilevamenti.add(ril);
			}

			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return rilevamenti;
	}

	public Double getAvgRilevamentiLocalitaMese(int mese, String localita) {
		
		final String sql = "SELECT AVG(s.Umidita) as media FROM situazione as s where s.Data>=? and s.Data<? and s.Localita=? ;";
		String d1 = "2013-"+mese+"-01";
		mese++;
		String d2 = "2013-"+mese+"-01";
		Double result = 0.0;
		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setDate(1, Date.valueOf(d1));
			st.setDate(2, Date.valueOf(d2));
			st.setString(3, localita);
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				result = rs.getDouble("media");
			}

			conn.close();

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}

}
