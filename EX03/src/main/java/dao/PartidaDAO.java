package dao;

import model.Partida;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class PartidaDAO extends DAO {	
	public PartidaDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	public boolean insert(Partida partida) {
		boolean status = false;
		try {
			String sql = "INSERT INTO Partida(id_partida, nome, valor_pago, hora_fim, hora_inicio, jog_max, tipo, esporte, data, end_num, end_bairro, end_logr, end_cidade, end_uf) VALUES (" + partida.getId_partida() + ", '" + partida.getNome() + "', " + partida.getValor_pago() + ", '" + partida.getHora_fim() + "', '" + partida.getHora_inicio() + "', '" + partida.getJog_max() + "', '" + partida.getTipo() + "', '" + partida.getEsporte() + "', '" + partida.getData() + "', '" + partida.getEnd_num() + "', '" + partida.getEnd_bairro() + "', '" + partida.getEnd_logr() + "', '" + partida.getEnd_cidade() + "', '" + partida.getEnd_uf() + "');";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}


	public Partida get(int id_partida) {
		  Partida partida = null;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM partida WHERE id_partida=" + id_partida;
			ResultSet rs = st.executeQuery(sql);
	        if(rs.next()){
	        	partida = new Partida(rs.getInt("id_partida"), rs.getString("nome"), Double.parseDouble(rs.getString("valor_pago")), rs.getString("hora_fim" ), rs.getString("hora_inicio" ), rs.getString("jog_max"), rs.getString("tipo" ),rs.getString("esporte" ),rs.getString("data" ),rs.getString("end_num"),rs.getString("end_bairro" ),rs.getString("end_logr" ),rs.getString("end_cidade" ),rs.getString("end_uf" ));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return partida;
	}

	public List<Partida> getAllbyName(String nome)
	{
		List<Partida> s = new ArrayList<>();

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM Partida WHERE nome LIKE" + "'%" + nome + "%'";
			ResultSet rs = st.executeQuery(sql);
	        while(rs.next()) {
	        	 Partida j = new Partida(rs.getInt("id_partida"), rs.getString("nome"), Double.parseDouble(rs.getString("valor_pago")), rs.getString("hora_fim" ), rs.getString("hora_inicio" ), rs.getString("jog_max"), rs.getString("tipo" ),rs.getString("esporte" ),rs.getString("data" ),rs.getString("end_num"),rs.getString("end_bairro" ),rs.getString("end_logr" ),rs.getString("end_cidade" ),rs.getString("end_uf" ));
	            s.add(j);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return s;
	
	}

	public Partida get(String nome) {
		  Partida partida = null;

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM Partida WHERE nome=" + "'" + nome + "'";
			ResultSet rs = st.executeQuery(sql);
	        if(rs.next()){
	        	partida = new Partida(rs.getInt("id_partida"), rs.getString("nome"), Double.parseDouble(rs.getString("valor_pago")), rs.getString("hora_fim" ), rs.getString("hora_inicio" ), rs.getString("jog_max"), rs.getString("tipo" ),rs.getString("esporte" ),rs.getString("data" ),rs.getString("end_num"),rs.getString("end_bairro" ),rs.getString("end_logr" ),rs.getString("end_cidade" ),rs.getString("end_uf" ));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return partida;
	}

	public List<Partida> get() {
		return get("", 0);
	}


	public List<Partida> getOrderByDataDeNascimento() {
		return get("Data_Nascimento", 0);
	}


	public List<Partida> getOrderByNome() {
		return get("Nome", 0);
	}


	public List<Partida> getOrderByid_partida() {
		return get("id_partida", 0);
	}


	private List<Partida> get(String orderBy, int i) {
		List<Partida> s = new ArrayList<>();

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM Partida " + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);
	        while(rs.next()) {
	        	 Partida j = new Partida(rs.getInt("id_partida"), rs.getString("nome"), Double.parseDouble(rs.getString("valor_pago")), rs.getString("hora_fim" ), rs.getString("hora_inicio"), rs.getString("jog_max"), rs.getString("tipo"),rs.getString("esporte" ),rs.getString("data"),rs.getString("end_num"),rs.getString("end_bairro"),rs.getString("end_logr"),rs.getString("end_cidade"),rs.getString("end_uf"));
	            s.add(j);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return s;
	}


	public boolean update(Partida partida) {
		boolean status = false;
		try {
			String sql = "UPDATE Partida SET id_partida = '" + partida.getId_partida() + "', nome = '" + partida.getNome() + "', valor_pago = '" + partida.getValor_pago() + "', hora_fim = '" + partida.getHora_fim() + "', hora_inicio = '" + partida.getHora_inicio() + "', jog_max = '" + partida.getJog_max() + "', tipo = '" + partida.getTipo() + "', esporte = '" + partida.getEsporte() + "', data = '" + partida.getData() + "', end_num = '" + partida.getEnd_num() + "', end_bairro = '" + partida.getEnd_bairro() + "', end_logr = '" + partida.getEnd_logr() + "', end_cidade = '" + partida.getEnd_cidade() + "', end_uf = '" + partida.getEnd_uf() + "' WHERE id_partida=" + partida.getId_partida() +  ";";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}


	public boolean delete(int id_partida) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM public.Partida WHERE id_partida = " + id_partida);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}