package service;

import java.util.Scanner;

import javax.xml.crypto.Data;

import static spark.Spark.port;

import java.io.File;
import java.util.List;

import dao.JogadorDAO;
import model.Jogador;
import spark.Request;
import spark.Response;
import spark.Session;


public class JogadorService {

	private JogadorDAO jogadorDAO = new JogadorDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_CPF = 1;
	private final int FORM_ORDERBY_NOME = 2;
	private final int FORM_ORDERBY_DATA_NASCIMENTO = 3;
	
	
	public JogadorService() {
		makeForm();
	}

	
	public void makeForm() {
		makeForm(FORM_INSERT, new Jogador(), FORM_ORDERBY_CPF);
	}

	
	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new Jogador(), orderBy);
	}

	
	public void makeForm(int tipo, Jogador jogador, int orderBy) {
		String nomeArquivo = "form.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String umJogador = "";
		if(tipo != FORM_INSERT) {
			umJogador += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umJogador += "\t\t<tr>";
			umJogador += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/Jogador/list/1\">Novo Jogador</a></b></font></td>";
			umJogador += "\t\t</tr>";
			umJogador += "\t</table>";
			umJogador += "\t<br>";			
		}
		
		if(tipo == FORM_INSERT || tipo == FORM_UPDATE) {
			String action = "/Jogador/";
			String name, descricao, buttonLabel;
			if (tipo == FORM_INSERT){
				action += "insert";
				name = "Inserir Jogador";
				buttonLabel = "Inserir";
			} else {
				action += "update/" + jogador.getCPF();
				name = "Atualizar Jogador (codPokedex " + jogador.getCPF() + ")";
				buttonLabel = "Atualizar";
			}
			umJogador += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
			umJogador += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umJogador += "\t\t<tr>";
			umJogador += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + name + "</b></font></td>";
			umJogador += "\t\t</tr>";
			umJogador += "\t\t<tr>";
			umJogador += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umJogador += "\t\t</tr>";
			umJogador += "\t\t<tr>";
            umJogador += "\t\t\t<td>Nome: <input class=\"input--register\" type=\"text\" name=\"Nome\" value=\""+ jogador.getNome() + "\"></td>";
            umJogador += "\t\t\t<td>codPokedex: <input class=\"input--register\" type=\"number\" name=\"codPokedex\" value=\""+ jogador.getCPF() + "\"></td>";
			umJogador += "\t\t\t<td>Attack: <input class=\"input--register\" type=\"number\" name=\"Attack\" value=\""+ jogador.getData_Nascimento() +"\"></td>";
			umJogador += "\t\t\t<td>Hp: <input class=\"input--register\" type=\"number\" name=\"Hp\" value=\""+ jogador.getData_Nascimento() +"\"></td>";
			umJogador += "\t\t</tr>";
			umJogador += "\t\t<tr>";
			umJogador += "\t\t\t<td>&nbsp;Speed: <input class=\"input--register\" type=\"number\" name=\"Speed\" value=\""+ jogador.getNome() + "\"></td>";
			umJogador += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\"></td>";
			umJogador += "\t\t</tr>";
			umJogador += "\t</table>";
			umJogador += "\t</form>";		
		} else if (tipo == FORM_DETAIL){
			umJogador += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umJogador += "\t\t<tr>";
			umJogador += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar Jogador (ID " + jogador.getCPF() + ")</b></font></td>";
			umJogador += "\t\t</tr>";
			umJogador += "\t\t<tr>";
			umJogador += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umJogador += "\t\t</tr>";
			umJogador += "\t\t<tr>";
            umJogador += "\t\t\t<td>Nome: "+ jogador.getNome() + "</td>";
            umJogador += "\t\t\t<td>codPokedex: "+ jogador.getCPF() + "</td>";
			umJogador += "\t\t\t<td>Attack: "+ jogador.getData_Nascimento() +"</td>";
			umJogador += "\t\t\t<td>Hp: "+ jogador.getData_Nascimento() +"</td>";
			umJogador += "\t\t</tr>";
			umJogador += "\t\t<tr>";
			umJogador += "\t\t\t<td>&nbsp;Speed: "+ jogador.getData_Nascimento() + "</td>";
			umJogador += "\t\t\t<td>&nbsp;</td>";
			umJogador += "\t\t</tr>";
			umJogador += "\t</table>";		
		} else {
			System.out.println("ERRO! Tipo não identificado " + tipo);
		}
		form = form.replaceFirst("<UM-Jogador>", umJogador);
		
		String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\">");
		list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Relação de Jogadors</b></font></td></tr>\n" +
				"\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
    			"\n<tr>\n" + 
        		"\t<td><a href=\"/Jogador/list/" + FORM_ORDERBY_CPF + "\"><b>codPokedex</b></a></td>\n" +
        		"\t<td><a href=\"/Jogador/list/" + FORM_ORDERBY_NOME + "\"><b>Hp</b></a></td>\n" +
        		"\t<td><a href=\"/Jogador/list/" + FORM_ORDERBY_DATA_NASCIMENTO + "\"><b>Attack</b></a></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
        		"</tr>\n";
		
		List <Jogador> Jogadors;
		if (orderBy == FORM_ORDERBY_CPF) {           Jogadors = jogadorDAO.getOrderByCPF();
		} else if (orderBy == FORM_ORDERBY_NOME) {		Jogadors = jogadorDAO.getOrderByNome();
		} else if (orderBy == FORM_ORDERBY_DATA_NASCIMENTO) {			Jogadors = jogadorDAO.getOrderByDataDeNascimento();
		} else {											Jogadors = jogadorDAO.get();
		}

		int i = 0;
		String bgcolor = "";
		for (Jogador j : Jogadors) {
			bgcolor = (i++ % 2 == 0) ? "#fff5dd" : "#dddddd";
			list += "\n<tr bgcolor=\""+ bgcolor +"\">\n" + 
            		  "\t<td>" + j.getCPF() + "</td>\n" +
            		  "\t<td>" + j.getNome() + "</td>\n" +
            		  "\t<td>" + j.getData_Nascimento() + "</td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/Jogador/" + j.getCPF() + "\"><img src=\"/image/detail.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/Jogador/update/" + j.getCPF() + "\"><img src=\"/image/update.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeleteJogador('" + j.getCPF() + "', '" + j.getNome() + "', '" + j.getData_Nascimento() + "');\"><img src=\"/image/delete.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "</tr>\n";
		}
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-Jogador>", list);				
	}

	
	public Object confirmLogin(Request request, Response response){
		String usuario = request.queryParams("usuario");
		String senha = request.queryParams("senha");
		boolean autenticado = false;

		if(jogadorDAO.autenticar(usuario, senha)){
			Session session = request.session(true);
			session.attribute("key", 1);
			session.attribute("usuario", usuario);
			response.redirect("/index.html");
			autenticado = true;
		}
		else{
			response.redirect("/modulos/login.html");
		}
		
		return autenticado;
	}

	public String logout(Request request, Response response){
		Session session = request.session();
		session.invalidate();
		response.redirect("/index.html");
		return "";
	}

	public String loadHeader(Request request, Response response) {
		
		Session session = request.session();
		
		String usuario = session.attribute("usuario");
		
		String header = "";
		
		if(session.attribute("key") != null){
			header = 
			"    <nav>\n" +
			"        <a href=\"./index.html\"><img class=\"logo\" src=\"./assets/imgs/SPORTSlogo.png\"\n" +
			"            alt=\"SportsNOW\"\n" +
			"            width=\"80\"\n" +
			"            height=\"80\"></a>\n" +
			"        <ul>\n" +
			"			<a href=\"../index.html\">\n" +
			"    			<li>Buscar Partidas</li>\n" +
			"			</a>" +
			"			<a href=\"./modulos/participandoPeladas.html\">\n" +
			"    			<li>Partidas que Participo</li>\n" +
			"			</a>" +  
			"            <a href=\"./modulos/criar.html\">\n" +
			"                <li>Criar Jogos</li>\n" +
			"            </a>\n" +
			"            <a href=\"./modulos/perfil.html\">\n" +
			"                <li>Perfil</li>\n" +
			"            </a>\n" +
			"        </ul>\n" +
			"    </nav>\n";

		}
		else {
			header += 
			"    <nav>\n" +
			"        <a href=\"./index.html\"><img class=\"logo\" src=\"./assets/imgs/SPORTSlogo.png\"\n" +
			"            alt=\"SportsNOW\"\n" +
			"            width=\"80\"\n" +
			"            height=\"80\"></a>\n" +
			"        <ul>\n" +
			"            <a href=\"./modulos/login.html\">\n" +
			"                <li>Login</li>\n" +
			"            </a>\n" +
						"<a href=\"../index.html\">\n" +
						"    <li>Buscar Partidas</li>\n" +
					"	</a>" +
			"        </ul>\n" +
			"    </nav>\n";
		}
		response.header("Access-Control-Allow-Origin", "*");
		response.type("text/html");
		return header;
		
	}

	public Object insert(Request request, Response response) {
        String CPF = request.queryParams("cpf");
		String Nome = request.queryParams("nome");
		String Data_Nascimento = request.queryParams("data_nascimento");
		String senha = request.queryParams("senha_registro");
		String resp = "";
		
		Jogador jogador = new Jogador(CPF, Nome, Data_Nascimento, senha);
		
		if(jogadorDAO.insert(jogador) == true) {
            resp = "jogador (" + jogador.getCPF() + ") inserido!";
            response.status(201); // 201 Created
		} else {
			resp = "jogador (" + jogador.getCPF() + ") não inserido!";
			response.status(404); // 404 Not found
		}
		response.redirect("/index.html");
		return CPF;
	}

	
	public String get(Request request, Response response) {
		String CPF = request.params(":id");	
		Jogador jogador = (Jogador) jogadorDAO.get(CPF);
		
		StringBuilder jsonJogador = new StringBuilder("[");
		if (jogador != null) {
			response.status(200); // success
			jsonJogador.append("{");
			jsonJogador.append("\"cpf\":\"").append(jogador.getCPF()).append("\",");
			jsonJogador.append("\"nome\":\"").append(jogador.getNome()).append("\",");
			jsonJogador.append("\"nascimento\":\"").append(jogador.getData_Nascimento()).append("\",");
			jsonJogador.append("\"senha\":\"").append(jogador.getSenha()).append("\"");
			jsonJogador.append("}");

        } else {
            response.status(404); // 404 Not found
            String resp = "Jogador " + CPF + " não encontrado.";
        }
		jsonJogador.append("]");

		response.header("Access-Control-Allow-Origin", "*");
		response.type("application/json");
		return jsonJogador.toString();
	}

	
	public Object getToUpdate(Request request, Response response) {
		String CPF = request.params(":CPF");		
		Jogador jogador = (Jogador) jogadorDAO.get(CPF);
		
		if (jogador != null) {
			response.status(200); // success
			makeForm(FORM_UPDATE, jogador, FORM_ORDERBY_CPF);
        } else {
            response.status(404); // 404 Not found
            String resp = "Jogador " + jogador + " não encontrado.";
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
        String CPF = request.params(":CPF");		
		Jogador jogador = (Jogador) jogadorDAO.get(CPF);

        String resp = "";       

        if (jogador != null) {
        	jogador.setNome(request.queryParams("Nome"));
        	jogador.setData_Nascimento(request.queryParams("Data_Nascimento"));
        	jogadorDAO.update(jogador);
        	response.status(200); // success
            resp = "Jogador (CPF " + jogador.getCPF() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Jogador (CPF \"" + jogador.getCPF() + "\") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object delete(Request request, Response response) {
        String CPF = request.params(":CPF");		
		Jogador jogador = (Jogador) jogadorDAO.get(CPF);
        String resp = "";       

        if (jogador != null) {
            jogadorDAO.delete(CPF);
            response.status(200); // success
            resp = "Jogador (" + CPF + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "Jogador (" + CPF + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
}