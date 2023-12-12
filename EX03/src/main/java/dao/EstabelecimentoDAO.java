package dao;

import model.Estabelecimento;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class EstabelecimentoDAO extends DAO {	
	public EstabelecimentoDAO() {
		super();
		conectar();
	}
	
	
	public void finalize() {
		close();
	}
	
	
	public boolean insert(Estabelecimento estabelecimento) {
		boolean status = false;
		try {
			String sql = "INSERT INTO public.\"Estabelecimento\"(\"cnpj\", \"nome\", \"descricao\", \"tipo\", \"end_num\", \"end_bairro\", \"end_logr\", \"end_cidade\", \"end_uf\", \"senha\") VALUES (" +
    "'" + estabelecimento.getEstab_cnpj() + "', " +
    "'" + estabelecimento.getNome() + "', " +
    "'" + estabelecimento.getDescricao() + "', " +
    "'" + estabelecimento.getTipo() + "', " +
    estabelecimento.getEnd_num() + ", " +
    "'" + estabelecimento.getEnd_bairro() + "', " +
    "'" + estabelecimento.getEnd_logr() + "', " +
    "'" + estabelecimento.getEnd_cidade() + "', " +
    "'" + estabelecimento.getEnd_uf() + "', " +
    "'" + estabelecimento.getSenha() + "');";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}


	public Estabelecimento get(String cnpj) {
		  Estabelecimento estabelecimento = null;

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM public.\"Estabelecimento\" WHERE cnpj=" + cnpj;
			ResultSet rs = st.executeQuery(sql);
	        if(rs.next()){
	        	estabelecimento = new Estabelecimento(rs.getString("cnpj"), rs.getString("nome"),rs.getString("descricao"), rs.getString("tipo"), rs.getString("end_num"),rs.getString("end_bairro"),rs.getString("end_logr"),rs.getString("end_cidade"),rs.getString("end_uf"), rs.getString("senha"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return estabelecimento;
	}


	public List<Estabelecimento> get() {
		return get("", 0);
	}


	public List<Estabelecimento> getOrderByDataDeNascimento() {
		return get("Data_Nascimento", 0);
	}


	public List<Estabelecimento> getOrderByNome() {
		return get("Nome", 0);
	}


	public List<Estabelecimento> getOrderByCNPJ() {
		return get("cnpj", 0);
	}


	private List<Estabelecimento> get(String orderBy, int i) {
		List<Estabelecimento> s = new ArrayList<>();

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM public.\"Estabelecimento\" " + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);
	        while(rs.next()) {
	        	 Estabelecimento j = new Estabelecimento(rs.getString("cnpj"), rs.getString("nome"),rs.getString("descricao"), rs.getString("tipo"), rs.getString("end_num"),rs.getString("end_bairro"),rs.getString("end_logr"),rs.getString("end_cidade"),rs.getString("end_uf"), rs.getString("senha"));
	            s.add(j);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return s;
	}


	public boolean update(Estabelecimento estabelecimento) {
		boolean status = false;
		try {
			String sql = "UPDATE public.\"Estabelecimento\" SET \"cnpj\" = '" + estabelecimento.getEstab_cnpj() + "', \"nome\" = '" + estabelecimento.getNome() + "', \"descricao\" = '" + estabelecimento.getDescricao() + "' WHERE cnpj=" + estabelecimento.getEstab_cnpj() + "', \"tipo\" = '" + estabelecimento.getTipo() + "', \"end_num\" = '" + estabelecimento.getEnd_num() + "', \"end_bairro\" = '" + estabelecimento.getEnd_bairro() + "', \"end_logr\" = '" + estabelecimento.getEnd_logr() + "', \"end_cidade\" = '" + estabelecimento.getEnd_cidade() + "', \"end_uf\" = '" + estabelecimento.getEnd_uf() + ";";
			PreparedStatement st = conexao.prepareStatement(sql);
			st.executeUpdate();
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}


	public boolean delete(String cnpj) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM public.\"Estabelecimento\" WHERE cnpj = " + cnpj);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}
