package service;

import java.util.Scanner;

import javax.print.DocFlavor.STRING;
import javax.servlet.http.Part;
import javax.xml.crypto.Data;

import java.io.File;
import java.util.List;

import dao.PartidaDAO;
import model.Partida;
import spark.Request;
import spark.Response;


public class PartidaService {

	private PartidaDAO partidaDAO = new PartidaDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_CPF = 1;
	private final int FORM_ORDERBY_NOME = 2;
	private final int FORM_ORDERBY_DATA_NASCIMENTO = 3;
	
	
	public PartidaService() {
		makeForm();
	}

	
	public void makeForm() {
		makeForm(FORM_INSERT, new Partida(), FORM_ORDERBY_CPF);
	}

	
	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new Partida(), orderBy);
	}

	
	public void makeForm(int tipo, Partida partida, int orderBy) {
		String nomeArquivo = "form.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String umPartida = "";
		if(tipo != FORM_INSERT) {
			umPartida += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umPartida += "\t\t<tr>";
			umPartida += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/Partida/list/1\">Novo Partida</a></b></font></td>";
			umPartida += "\t\t</tr>";
			umPartida += "\t</table>";
			umPartida += "\t<br>";			
		}
		
		if(tipo == FORM_INSERT || tipo == FORM_UPDATE) {
			String action = "/Partida/";
			String name, descricao, buttonLabel;
			if (tipo == FORM_INSERT){
				action += "insert";
				name = "Inserir Partida";
				buttonLabel = "Inserir";
			} else {
				action += "update/" + partida.getId_partida();
				name = "Atualizar Partida (codPokedex " + partida.getId_partida() + ")";
				buttonLabel = "Atualizar";
			}
			umPartida += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id=\"form-add\">";
			umPartida += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umPartida += "\t\t<tr>";
			umPartida += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + name + "</b></font></td>";
			umPartida += "\t\t</tr>";
			umPartida += "\t\t<tr>";
			umPartida += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umPartida += "\t\t</tr>";
			umPartida += "\t\t<tr>";
            umPartida += "\t\t\t<td>Nome: <input class=\"input--register\" type=\"text\" name=\"Nome\" value=\""+ partida.getNome() + "\"></td>";
            umPartida += "\t\t\t<td>codPokedex: <input class=\"input--register\" type=\"number\" name=\"codPokedex\" value=\""+ partida.getId_partida() + "\"></td>";
			umPartida += "\t\t\t<td>Attack: <input class=\"input--register\" type=\"number\" name=\"Attack\" value=\""+ partida.getId_partida() +"\"></td>";
			umPartida += "\t\t\t<td>Hp: <input class=\"input--register\" type=\"number\" name=\"Hp\" value=\""+ partida.getId_partida() +"\"></td>";
			umPartida += "\t\t</tr>";
			umPartida += "\t\t<tr>";
			umPartida += "\t\t\t<td>&nbsp;Speed: <input class=\"input--register\" type=\"number\" name=\"Speed\" value=\""+ partida.getNome() + "\"></td>";
			umPartida += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\"></td>";
			umPartida += "\t\t</tr>";
			umPartida += "\t</table>";
			umPartida += "\t</form>";		
		} else if (tipo == FORM_DETAIL){
			umPartida += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umPartida += "\t\t<tr>";
			umPartida += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar Partida (ID " + partida.getId_partida() + ")</b></font></td>";
			umPartida += "\t\t</tr>";
			umPartida += "\t\t<tr>";
			umPartida += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umPartida += "\t\t</tr>";
			umPartida += "\t\t<tr>";
            umPartida += "\t\t\t<td>Nome: "+ partida.getNome() + "</td>";
            umPartida += "\t\t\t<td>codPokedex: "+ partida.getId_partida() + "</td>";
			umPartida += "\t\t\t<td>Attack: "+ partida.getId_partida() +"</td>";
			umPartida += "\t\t\t<td>Hp: "+ partida.getId_partida() +"</td>";
			umPartida += "\t\t</tr>";
			umPartida += "\t\t<tr>";
			umPartida += "\t\t\t<td>&nbsp;Speed: "+ partida.getId_partida() + "</td>";
			umPartida += "\t\t\t<td>&nbsp;</td>";
			umPartida += "\t\t</tr>";
			umPartida += "\t</table>";		
		} else {
			System.out.println("ERRO! Tipo não identificado " + tipo);
		}
		form = form.replaceFirst("<UM-Partida>", umPartida);
		
		String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\">");
		list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Relação de Partidas</b></font></td></tr>\n" +
				"\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
    			"\n<tr>\n" + 
        		"\t<td><a href=\"/Partida/list/" + FORM_ORDERBY_CPF + "\"><b>codPokedex</b></a></td>\n" +
        		"\t<td><a href=\"/Partida/list/" + FORM_ORDERBY_NOME + "\"><b>Hp</b></a></td>\n" +
        		"\t<td><a href=\"/Partida/list/" + FORM_ORDERBY_DATA_NASCIMENTO + "\"><b>Attack</b></a></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
        		"</tr>\n";
		
		List <Partida> Partidas;
		if (orderBy == FORM_ORDERBY_CPF) {           Partidas = partidaDAO.getOrderByid_partida();
		} else if (orderBy == FORM_ORDERBY_NOME) {		Partidas = partidaDAO.getOrderByNome();
		} else if (orderBy == FORM_ORDERBY_DATA_NASCIMENTO) {			Partidas = partidaDAO.getOrderByDataDeNascimento();
		} else {											Partidas = partidaDAO.get();
		}

		int i = 0;
		String bgcolor = "";
		for (Partida j : Partidas) {
			bgcolor = (i++ % 2 == 0) ? "#fff5dd" : "#dddddd";
			list += "\n<tr bgcolor=\""+ bgcolor +"\">\n" + 
            		  "\t<td>" + j.getId_partida() + "</td>\n" +
            		  "\t<td>" + j.getNome() + "</td>\n" +
            		  "\t<td>" + j.getId_partida() + "</td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/Partida/" + j.getId_partida() + "\"><img src=\"/image/detail.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/Partida/update/" + j.getId_partida() + "\"><img src=\"/image/update.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeletePartida('" + j.getId_partida() + "', '" + j.getNome() + "', '" + j.getId_partida() + "');\"><img src=\"/image/delete.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "</tr>\n";
		}
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-Partida>", list);				
	}
	
	
	public Object insert(Request request, Response response) {

		List <Partida> Partidas = partidaDAO.get();

		int id_partida = Partidas.size() + 1;
        String nome = (request.queryParams("nome_pelada"));
        Double valor_pago = (Double.parseDouble(request.queryParams("valorPartida")));
        String hora_fim = (request.queryParams("horario_fim"));
        String hora_inicio = (request.queryParams("horario_inicio"));
        String jog_max = (request.queryParams("tamanho_pelada"));
		String esporte = (request.queryParams("esporte_pelada"));
        String tipo = (request.queryParams("tipoPartida"));
        String data = (request.queryParams("data_pelada"));
        String end_num = (request.queryParams("numero_pelada"));
        String end_bairro = (request.queryParams("bairro_pelada"));
        String end_logr = (request.queryParams("logradouro_pelada"));
        String end_cidade = (request.queryParams("cidade_pelada"));
        String end_uf = (request.queryParams("uf_pelada"));
		String resp = "";
		
		Partida partida = new Partida(id_partida, nome, valor_pago, hora_fim,  hora_inicio,  jog_max,  tipo,  esporte,  data,  end_num,  end_bairro,  end_logr,  end_cidade,end_uf);
		
		if(partidaDAO.insert(partida) == true) {
            resp = "partida (" + partida.getId_partida() + ") inserido!";
            response.status(201); // 201 Created
		} else {
			resp = "partida (" + partida.getId_partida() + ") não inserido!";
			response.status(404); // 404 Not found
		}
		makeForm();
		response.redirect("/index.html");
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public String get(Request request, Response response) {
		int id_partida = Integer.parseInt(request.params(":id"));		
		Partida partida = (Partida) partidaDAO.get(id_partida);
		StringBuilder jsonPartida = new StringBuilder("[");
		if (partida != null) {
			response.status(200); // success
			jsonPartida.append("{");
			jsonPartida.append("\"id_partida\":\"").append(partida.getId_partida()).append("\",");
			jsonPartida.append("\"nome\":\"").append(partida.getNome()).append("\",");
			jsonPartida.append("\"valor_pago\":\"").append(partida.getValor_pago()).append("\",");
			jsonPartida.append("\"fim\":\"").append(partida.getHora_fim()).append("\",");
			jsonPartida.append("\"inicio\":\"").append(partida.getHora_inicio()).append("\",");
			jsonPartida.append("\"jog_max\":\"").append(partida.getJog_max()).append("\",");
			jsonPartida.append("\"tipo\":\"").append(partida.getTipo()).append("\",");
			jsonPartida.append("\"esporte\":\"").append(partida.getEsporte()).append("\",");
			jsonPartida.append("\"data\":\"").append(partida.getData()).append("\",");
			jsonPartida.append("\"num\":\"").append(partida.getEnd_num()).append("\",");
			jsonPartida.append("\"bairro\":\"").append(partida.getEnd_bairro()).append("\",");
			jsonPartida.append("\"logr\":\"").append(partida.getEnd_logr()).append("\",");
			jsonPartida.append("\"cidade\":\"").append(partida.getEnd_cidade()).append("\",");
			jsonPartida.append("\"uf\":\"").append(partida.getEnd_uf()).append("\"");
			jsonPartida.append("}");
        } else {
            response.status(404); // 404 Not found
        }
		jsonPartida.append("]");

		response.header("Access-Control-Allow-Origin", "*");
		response.type("application/json");

		return jsonPartida.toString();
	}

	
	public Object getToUpdate(Request request, Response response) {
		int id_partida = Integer.parseInt(request.params(":id_partida"));	
		Partida partida = (Partida) partidaDAO.get(id_partida);
		
		if (partida != null) {
			response.status(200); // success
        } else {
            response.status(404); // 404 Not found
            String resp = "Partida " + partida + " não encontrado.";
    		//makeForm();
    		form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }
		return form;
	}
	
	public String filterPartidas(Request request, Response response)
	{
		String nome = request.params(":orderby");
		List <Partida> partidas = partidaDAO.getAllbyName(nome);
		String jsonPartida = processarPartidas(partidas);
		
		response.header("Access-Control-Allow-Origin", "*");
		response.type("application/json");

		return jsonPartida;
	}

	public String processarPartidas(List <Partida> partidas)
	{
		StringBuilder jsonPartida = new StringBuilder("[");
        for (Partida partida : partidas) {
            jsonPartida.append("{");
			jsonPartida.append("\"id_partida\":\"").append(partida.getId_partida()).append("\",");
			jsonPartida.append("\"nome\":\"").append(partida.getNome()).append("\",");
			jsonPartida.append("\"valor_pago\":\"").append(partida.getValor_pago()).append("\",");
			jsonPartida.append("\"fim\":\"").append(partida.getHora_fim()).append("\",");
			jsonPartida.append("\"inicio\":\"").append(partida.getHora_inicio()).append("\",");
			jsonPartida.append("\"jog_max\":\"").append(partida.getJog_max()).append("\",");
			jsonPartida.append("\"tipo\":\"").append(partida.getTipo()).append("\",");
			jsonPartida.append("\"esporte\":\"").append(partida.getEsporte()).append("\",");
			jsonPartida.append("\"data\":\"").append(partida.getData()).append("\",");
			jsonPartida.append("\"num\":\"").append(partida.getEnd_num()).append("\",");
			jsonPartida.append("\"bairro\":\"").append(partida.getEnd_bairro()).append("\",");
			jsonPartida.append("\"logr\":\"").append(partida.getEnd_logr()).append("\",");
			jsonPartida.append("\"cidade\":\"").append(partida.getEnd_cidade()).append("\",");
			jsonPartida.append("\"uf\":\"").append(partida.getEnd_uf()).append("\"");
			jsonPartida.append("},");

        }

        if (partidas.size() > 0) {
            jsonPartida.deleteCharAt(jsonPartida.length() - 1);
        }
        jsonPartida.append("]");

        return jsonPartida.toString();
	}

	
	public String getAll(Request request, Response response) {
		List <Partida> partidas = partidaDAO.get();
		String jsonPartida = processarPartidas(partidas);
		
		response.header("Access-Control-Allow-Origin", "*");
		response.type("application/json");
		
		return jsonPartida;
	}
	
	public Object update(Request request, Response response) {
        String nome = request.params("nome");
		
		
		Partida partida = (Partida) partidaDAO.get(nome);

		
        String resp = "";       

        if (partida != null) {
        	partida.setNome(request.queryParams("nome_pelada"));
        	partida.setValor_pago(Double.parseDouble(request.queryParams("valorPartida")));
            partida.setHora_fim(request.queryParams("horario_fim"));
            partida.setHora_inicio(request.queryParams("horario_inicio"));
            partida.setJog_max(request.queryParams("tamanho_pelada"));
			partida.setEsporte(request.queryParams("esporte_pelada"));
            partida.setTipo(request.queryParams("tipoPartida"));
            partida.setData(request.queryParams("data_pelada"));
            partida.setEnd_num(request.queryParams("numero_pelada"));
            partida.setEnd_bairro(request.queryParams("bairro_pelada"));
            partida.setEnd_logr(request.queryParams("logradouro_pelada"));
            partida.setEnd_cidade(request.queryParams("cidade_pelada"));
            partida.setEnd_uf(request.queryParams("uf_pelada"));

        	partidaDAO.update(partida);
        	response.status(200); // success
            resp = "Partida (id_partida " + partida.getId_partida() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Partida (id_partida \"" + partida.getId_partida() + "\") não encontrado!";
        }
	//	makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object delete(Request request, Response response) {
        int id_partida = Integer.parseInt(request.params(":id_partida"));		
		Partida partida = (Partida) partidaDAO.get(id_partida);
        String resp = "";       

        if (partida != null) {
            partidaDAO.delete(id_partida);
            response.status(200); // success
            resp = "Partida (" + id_partida + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "Partida (" + id_partida + ") não encontrado!";
        }
		//makeForm();
		return form.replaceFirst("<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
}
