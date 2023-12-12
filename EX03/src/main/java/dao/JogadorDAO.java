package dao;

import model.Jogador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class JogadorDAO extends DAO {	
	public JogadorDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	public boolean insert(Jogador jogador) {
		boolean status = false;
		try {
			String sql = "INSERT INTO jogador(cpf, nome, data_nascimento, senha) VALUES ('" + jogador.getCPF() + "', '"+ jogador.getNome() + "','" + jogador.getData_Nascimento() + "'," + jogador.getSenha() + ");";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}


	public Jogador get(String CPF) {
		  Jogador jogador = null;

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM jogador WHERE cpf='" + CPF + "'";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
	        if(rs.next()){
	        	jogador = new Jogador(rs.getString("cpf"), rs.getString("nome"),
rs.getString("data_Nascimento"), rs.getString("senha"));
	        }
			System.out.println(jogador.getCPF() + " " + jogador.getNome() + " " + jogador.getData_Nascimento() + " " + jogador.getSenha());
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return jogador;
	}

	public boolean autenticar(String cpf, String senha) {
        boolean resp = false;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM jogador WHERE cpf LIKE '" + cpf + "' AND senha LIKE '" + senha  + "'";
            ResultSet rs = st.executeQuery(sql);
            resp = rs.next();
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return resp;
    }

	public List<Jogador> get() {
		return get("", 0);
	}


	public List<Jogador> getOrderByDataDeNascimento() {
		return get("Data_Nascimento", 0);
	}


	public List<Jogador> getOrderByNome() {
		return get("Nome", 0);
	}


	public List<Jogador> getOrderByCPF() {
		return get("CPF", 0);
	}


	private List<Jogador> get(String orderBy, int i) {
		List<Jogador> s = new ArrayList<>();

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM jogador " + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);
	        while(rs.next()) {
	        	 Jogador j = new Jogador(rs.getString("cpf"), rs.getString("nome"),
rs.getString("data_Nascimento"), rs.getString("senha"));
	            s.add(j);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return s;
	}


	public boolean update(Jogador jogador) {
		boolean status = false;
		try {
			String sql = "UPDATE jogador SET \"CPF+ \" = '" + jogador.getCPF() + "', \"Nome\" = '" + jogador.getNome() + "', \"Data_Nascimento\" = '" + jogador.getData_Nascimento() + "' WHERE CPF=" + jogador.getCPF() + ";";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}


	public boolean delete(String CPF) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM jogador WHERE CPF = " + CPF);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}