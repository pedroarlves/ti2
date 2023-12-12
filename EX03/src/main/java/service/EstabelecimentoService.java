package service;

import java.util.Scanner;

import javax.xml.crypto.Data;

import java.io.File;
import java.util.List;

import dao.EstabelecimentoDAO;
import model.Estabelecimento;
import spark.Request;
import spark.Response;


public class EstabelecimentoService {

	private EstabelecimentoDAO estabelecimentoDAO = new EstabelecimentoDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_CNPJ = 1;
	private final int FORM_ORDERBY_NOME = 2;
	private final int FORM_ORDERBY_DESCRICAO = 3;
    private final int FORM_ORDERBY_TIPO = 4;
    private final int FORM_ORDERBY_END_NUM = 5;
    private final int FORM_ORDERBY_END_BAIRRO = 6;
    private final int FORM_ORDERBY_END_LOGR = 7;
    private final int FORM_ORDERBY_END_CIDADE = 8;
    private final int FORM_ORDERBY_END_UF = 9;
	
    
	public EstabelecimentoService() {
		makeForm();
	}

	
	public void makeForm() {
		makeForm(FORM_INSERT, new Estabelecimento(), FORM_ORDERBY_CNPJ);
	}

	
	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new Estabelecimento(), orderBy);
	}

	
	public void makeForm(int tipo, Estabelecimento estabelecimento, int orderBy) {
		String nomeArquivo = "form.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String umEstabelecimento = "";
		if(tipo != FORM_INSERT) {
			umEstabelecimento += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umEstabelecimento += "\t\t<tr>";
			umEstabelecimento += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/Estabelecimento/list/1\">Novo Estabelecimento</a></b></font></td>";
			umEstabelecimento += "\t\t</tr>";
			umEstabelecimento += "\t</table>";
			umEstabelecimento += "\t<br>";			
		}
		
		if(tipo == FORM_INSERT || tipo == FORM_UPDATE) {
			String action = "/Estabelecimento/";
			String name, descricao, buttonLabel;
			if (tipo == FORM_INSERT){
				action += "insert";
				name = "Inserir Estabelecimento";
				buttonLabel = "Inserir";
			} else {
				action += "update/" + estabelecimento.getEstab_cnpj();
				name = "Atualizar Estabelecimento (cnpj " + estabelecimento.getEstab_cnpj() + ")";
				buttonLabel = "Atualizar";
			}
			umEstabelecimento += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
			umEstabelecimento += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umEstabelecimento += "\t\t<tr>";
			umEstabelecimento += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + name + "</b></font></td>";
			umEstabelecimento += "\t\t</tr>";
			umEstabelecimento += "\t\t<tr>";
			umEstabelecimento += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umEstabelecimento += "\t\t</tr>";
			umEstabelecimento += "\t\t<tr>";
            umEstabelecimento += "\t\t\t<td>Nome: <input class=\"input--register\" type=\"text\" name=\"Nome\" value=\""+ estabelecimento.getNome() + "\"></td>";
            umEstabelecimento += "\t\t\t<td>cnpj: <input class=\"input--register\" type=\"text\" name=\"cnpj\" value=\""+ estabelecimento.getEstab_cnpj() + "\"></td>";
			umEstabelecimento += "\t\t\t<td>Descricao: <input class=\"input--register\" type=\"text\" name=\"descricao\" value=\""+ estabelecimento.getDescricao() +"\"></td>";
			umEstabelecimento += "\t\t\t<td>Tipo: <input class=\"input--register\" type=\"text\" name=\"tipo\" value=\""+ estabelecimento.getTipo() +"\"></td>";
			umEstabelecimento += "\t\t</tr>";
			umEstabelecimento += "\t\t<tr>";
			umEstabelecimento += "\t\t\t<td>&nbsp;Speed: <input class=\"input--register\" type=\"number\" name=\"Speed\" value=\""+ estabelecimento.getNome() + "\"></td>";
			umEstabelecimento += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\"></td>";
			umEstabelecimento += "\t\t</tr>";
			umEstabelecimento += "\t</table>";
			umEstabelecimento += "\t</form>";		
		} else if (tipo == FORM_DETAIL){
			umEstabelecimento += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umEstabelecimento += "\t\t<tr>";
			umEstabelecimento += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar Estabelecimento (ID " + estabelecimento.getEstab_cnpj() + ")</b></font></td>";
			umEstabelecimento += "\t\t</tr>";
			umEstabelecimento += "\t\t<tr>";
			umEstabelecimento += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umEstabelecimento += "\t\t</tr>";
			umEstabelecimento += "\t\t<tr>";
            umEstabelecimento += "\t\t\t<td>Nome: "+ estabelecimento.getNome() + "</td>";
            umEstabelecimento += "\t\t\t<td>codPokedex: "+ estabelecimento.getEstab_cnpj() + "</td>";
			umEstabelecimento += "\t\t\t<td>Attack: "+ estabelecimento.getDescricao() +"</td>";
			umEstabelecimento += "\t\t\t<td>Hp: "+ estabelecimento.getDescricao() +"</td>";
			umEstabelecimento += "\t\t</tr>";
			umEstabelecimento += "\t\t<tr>";
			umEstabelecimento += "\t\t\t<td>&nbsp;Speed: "+ estabelecimento.getDescricao() + "</td>";
			umEstabelecimento += "\t\t\t<td>&nbsp;</td>";
			umEstabelecimento += "\t\t</tr>";
			umEstabelecimento += "\t</table>";		
		} else {
			System.out.println("ERRO! Tipo não identificado " + tipo);
		}
		form = form.replaceFirst("<UM-Estabelecimento>", umEstabelecimento);
		
		String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\">");
		list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Relação de Estabelecimentos</b></font></td></tr>\n" +
				"\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
    			"\n<tr>\n" + 
        		"\t<td><a href=\"/Estabelecimento/list/" + FORM_ORDERBY_CNPJ + "\"><b>codPokedex</b></a></td>\n" +
        		"\t<td><a href=\"/Estabelecimento/list/" + FORM_ORDERBY_NOME + "\"><b>Hp</b></a></td>\n" +
        		"\t<td><a href=\"/Estabelecimento/list/" + FORM_ORDERBY_END_BAIRRO + "\"><b>Attack</b></a></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
        		"</tr>\n";
		
		List <Estabelecimento> Estabelecimentos;
		if (orderBy == FORM_ORDERBY_CNPJ) {           Estabelecimentos = estabelecimentoDAO.getOrderByCNPJ();} 
		else if (orderBy == FORM_ORDERBY_NOME) {		Estabelecimentos = estabelecimentoDAO.getOrderByNome();
		} else if (orderBy == FORM_ORDERBY_END_BAIRRO) {			Estabelecimentos = estabelecimentoDAO.getOrderByDataDeNascimento();
		} else {											Estabelecimentos = estabelecimentoDAO.get();
		}

		int i = 0;
		String bgcolor = "";
		for (Estabelecimento j : Estabelecimentos) {
			bgcolor = (i++ % 2 == 0) ? "#fff5dd" : "#dddddd";
			list += "\n<tr bgcolor=\""+ bgcolor +"\">\n" + 
            		  "\t<td>" + j.getEstab_cnpj() + "</td>\n" +
            		  "\t<td>" + j.getDescricao() + "</td>\n" +
            		  "\t<td>" + j.getDescricao() + "</td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/Estabelecimento/" + j.getEstab_cnpj() + "\"><img src=\"/image/detail.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/Estabelecimento/update/" + j.getEstab_cnpj() + "\"><img src=\"/image/update.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeleteEstabelecimento('" + j.getEstab_cnpj()  + "', '" + j.getNome() + "', '" + j.getEstab_cnpj()  + "');\"><img src=\"/image/delete.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "</tr>\n";
		}
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-Estabelecimento>", list);				
	}
	
	

	public Object insert(Request request, Response response) {

        String estab_cnpj = request.queryParams("cnpj_registro");
		String nome = request.queryParams("nome_estabelecimento_registro");
		String descricao = request.queryParams("descr_estabelecimento_registro");
        String tipo = request.queryParams("tipo_estabelecimento");
        String end_num = request.queryParams("estabelecimento_num");
        String end_bairro = request.queryParams("estabelecimento_bairro");
        String end_logr = request.queryParams("estabelecimento_logr");
        String end_cidade = request.queryParams("estabelecimento_cidade");
        String end_uf = request.queryParams("estabelecimento_UF");
		String senha = request.queryParams("senha_estabelecimento_registro");

		String resp = "";
		
		Estabelecimento estabelecimento = new Estabelecimento(estab_cnpj,  nome,  descricao,  tipo,  end_num,  end_bairro,  end_logr,  end_cidade,  end_uf, senha);
		
		if(estabelecimentoDAO.insert(estabelecimento) == true) {
            resp = "estabelecimento (" + estabelecimento.getEstab_cnpj() + ") inserido!";
            response.status(201); // 201 Created
		} else {
			resp = "estabelecimento (" + estabelecimento.getEstab_cnpj() + ") não inserido!";
			response.status(404); // 404 Not found
		}
		makeForm();
		response.redirect("/index.html");
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");

	}

	
	public Object get(Request request, Response response) {
		String estab_cnpj = request.params(":estab_cnpj");		
		Estabelecimento estabelecimento = (Estabelecimento) estabelecimentoDAO.get(estab_cnpj);
		
		if (estabelecimento != null) {
			response.status(200); // success
			makeForm(FORM_DETAIL, estabelecimento, FORM_ORDERBY_CNPJ);
        } else {
            response.status(404); // 404 Not found
            String resp = "Estabelecimento " + estab_cnpj + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}

	
	public Object getToUpdate(Request request, Response response) {
		String estab_cnpj = request.params(":estab_cnpj");		
		Estabelecimento estabelecimento = (Estabelecimento) estabelecimentoDAO.get(estab_cnpj);
		
		if (estabelecimento != null) {
			response.status(200); // success
			makeForm(FORM_UPDATE, estabelecimento, FORM_ORDERBY_CNPJ);
        } else {
            response.status(404); // 404 Not found
            String resp = "Estabelecimento " + estabelecimento + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}
	
	
	public Object getAll(Request request, Response response) {
		int orderBy = Integer.parseInt(request.params(":orderby"));
		makeForm(orderBy);
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
		return form;
	}			
	
	public Object update(Request request, Response response) {
        String estab_cnpj = request.params(":estab_cnpj");		
		Estabelecimento estabelecimento = (Estabelecimento) estabelecimentoDAO.get(estab_cnpj);

        String resp = "";       

        if (estabelecimento != null) {
        	estabelecimento.setNome(request.queryParams("nome_estabelecimento_registro"));
        	estabelecimento.setDescricao(request.queryParams("descr_estabelecimento_registro"));
			estabelecimento.setTipo(request.queryParams("tipo"));
			estabelecimento.setEnd_num(request.queryParams("end_num"));
			estabelecimento.setEnd_bairro(request.queryParams("end_bairro"));
			estabelecimento.setEnd_logr(request.queryParams("end_logr"));
			estabelecimento.setEnd_cidade(request.queryParams("end_cidade"));
			estabelecimento.setEnd_uf(request.queryParams("end_uf"));
        	estabelecimentoDAO.update(estabelecimento);
        	response.status(200); // success
            resp = "Estabelecimento (CPF " + estabelecimento.getEstab_cnpj() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Estabelecimento (CPF \"" + estabelecimento.getEstab_cnpj() + "\") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object delete(Request request, Response response) {
        String estab_cnpj = request.params(":estab_cnpj");		
		Estabelecimento estabelecimento = (Estabelecimento) estabelecimentoDAO.get(estab_cnpj);
        String resp = "";       

        if (estabelecimento != null) {
            estabelecimentoDAO.delete(estab_cnpj);
            response.status(200); // success
            resp = "Estabelecimento (" + estab_cnpj + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "Estabelecimento (" + estab_cnpj + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
}