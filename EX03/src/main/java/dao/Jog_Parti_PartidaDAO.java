package dao;

import model.Jog_Parti_Partida;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Jog_Parti_PartidaDAO extends DAO {	
	public Jog_Parti_PartidaDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	public boolean insert(Jog_Parti_Partida jog_parti_partida) {
		boolean status = false;
		try {
			String sql = "INSERT INTO Jog_Participa_Partida(\"idJog_Participa_Partida\", \"jogador_cpf\", \"Partida_IdPartida\") VALUES (" +
    "" + jog_parti_partida.getIdjog_participa_partida() + ", " +
    "'" + jog_parti_partida.getJogador_cpf() + "', " +
    "" + jog_parti_partida.getIdpartida_idpartida() + ");";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}


	public Jog_Parti_Partida get(String Jogador_CPF, String i) {
		  Jog_Parti_Partida jog_parti_partida = null;

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM Jog_Participa_Partida WHERE Jogador_CPF='" + Jogador_CPF + "'";
			ResultSet rs = st.executeQuery(sql);
	        if(rs.next()){
	        	jog_parti_partida = new Jog_Parti_Partida(rs.getInt("idJog_Participa_Partida"), rs.getString("Jogador_CPF"),rs.getInt("Partida_IdPartida"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return jog_parti_partida;
	}

	public List<Jog_Parti_Partida> get(String Jogador_CPF) {
		  List<Jog_Parti_Partida> s = new ArrayList<>();

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM Jog_Participa_Partida WHERE Jogador_CPF='" + Jogador_CPF + "'";
			ResultSet rs = st.executeQuery(sql);
	        while(rs.next()){
	        	Jog_Parti_Partida j = new Jog_Parti_Partida(rs.getInt("idJog_Participa_Partida"), rs.getString("Jogador_CPF"),rs.getInt("Partida_IdPartida"));
	            s.add(j);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return s;
	}

	public List<Jog_Parti_Partida> get() {
		return get("", 0);
	}


	public List<Jog_Parti_Partida> getOrderByDataDeNascimento() {
		return get("Data_Nascimento", 0);
	}


	public List<Jog_Parti_Partida> getOrderByNome() {
		return get("Nome", 0);
	}


	public List<Jog_Parti_Partida> getOrderByidJog_Participa_Partida() {
		return get("idJog_Participa_Partida", 0);
	}


	private List<Jog_Parti_Partida> get(String orderBy, int i) {
		List<Jog_Parti_Partida> s = new ArrayList<>();

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM Jog_Participa_Partida" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
            ResultSet rs = st.executeQuery(sql);
	        while(rs.next()) {
	        	 Jog_Parti_Partida j = new Jog_Parti_Partida(rs.getInt("idJog_Participa_Partida"), rs.getString("Jogador_CPF"),rs.getInt("Partida_IdPartida"));
	            s.add(j);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return s;
	}


	public boolean update(Jog_Parti_Partida jog_parti_partida) {
		boolean status = false;
		try {
			String sql = "UPDATE Jog_Parti_Partida SET \"idJog_Participa_Partida\" = '" + jog_parti_partida.getIdjog_participa_partida() + "', \"Jogador_CPF\" = '" + jog_parti_partida.getJogador_cpf() + "', \"Partida_IdPartida\" = '" + jog_parti_partida.getIdpartida_idpartida() + "' WHERE idJog_Participa_Partida=" + jog_parti_partida.getIdjog_participa_partida() + ";";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}


	public boolean delete(String idJog_Participa_Partida) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM Jog_Parti_Partida WHERE idJog_Participa_Partida = " + idJog_Participa_Partida);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}

