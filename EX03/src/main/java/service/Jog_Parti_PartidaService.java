package service;

import java.util.Scanner;

import javax.xml.crypto.Data;

import java.io.File;
import java.util.List;

import java.util.ArrayList;
import dao.Jog_Parti_PartidaDAO;
import dao.Jog_Parti_PartidaDAO;
import model.Jog_Parti_Partida;
import model.Partida;
import dao.PartidaDAO;
import model.Jog_Parti_Partida;
import spark.Request;
import spark.Response;

public class Jog_Parti_PartidaService {
    
    private Jog_Parti_PartidaDAO jog_parti_partidaDAO = new Jog_Parti_PartidaDAO();
	private PartidaDAO partidaDAO = new PartidaDAO();


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

    public Object insert(Request request, Response response) {

        List <Jog_Parti_Partida> partidas = jog_parti_partidaDAO.get();

        int idPartida_IdPartida = Integer.parseInt(request.params(":id"));
        String jogador_cpf = request.session().attribute("usuario");
        int idJog_Participa_Partida = partidas.size() + 1;

		
		Jog_Parti_Partida jog_parti_partida = new Jog_Parti_Partida(idJog_Participa_Partida, jogador_cpf, idPartida_IdPartida);
		
		if(jog_parti_partidaDAO.insert(jog_parti_partida) == true) {
            response.status(201); // 201 Created
		} else {
			response.status(404); // 404 Not found
		}
		response.redirect("/index.html");

        return "form";
	}

	
	public Object get(Request request, Response response) {
		String estab_cnpj = request.params(":estab_cnpj");		
		Jog_Parti_Partida jog_parti_partida = (Jog_Parti_Partida) jog_parti_partidaDAO.get(estab_cnpj);
		
		if (jog_parti_partida != null) {
			response.status(200); // success
        } else {
            response.status(404); // 404 Not found
            String resp = "Jog_Parti_Partida " + estab_cnpj + " não encontrado.";
        }

		return "form";
	}

	
	public Object getToUpdate(Request request, Response response) {
		String estab_cnpj = request.params(":estab_cnpj");		
		Jog_Parti_Partida jog_parti_partida = (Jog_Parti_Partida) jog_parti_partidaDAO.get(estab_cnpj);
		
		if (jog_parti_partida != null) {
			response.status(200); // success
        } else {
            response.status(404); // 404 Not found
            String resp = "Jog_Parti_Partida " + jog_parti_partida + " não encontrado.";
        }

		return "form";
	}
	
	

	public String getAll(Request request, Response response) {
		String jogador_cpf = request.session().attribute("usuario");
		List <Partida> partidas = new ArrayList<>();
		List <Jog_Parti_Partida> jog_Parti_Partidas = jog_parti_partidaDAO.get(jogador_cpf);
		
		for (Jog_Parti_Partida participantes : jog_Parti_Partidas) {
			int id_partida = participantes.getIdpartida_idpartida();
			Partida partida = (Partida) partidaDAO.get(id_partida);
			partidas.add(partida);
		}
		String jsonPartida = processarPartidas(partidas);
		
		response.header("Access-Control-Allow-Origin", "*");
		response.type("application/json");
		
		return jsonPartida;
	}

	
	public Object delete(Request request, Response response) {
        String estab_cnpj = request.params(":estab_cnpj");		
		Jog_Parti_Partida jog_parti_partida = (Jog_Parti_Partida) jog_parti_partidaDAO.get(estab_cnpj);
        String resp = "";       

        if (jog_parti_partida != null) {
            jog_parti_partidaDAO.delete(estab_cnpj);
            response.status(200); // success
            resp = "Jog_Parti_Partida (" + estab_cnpj + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "Jog_Parti_Partida (" + estab_cnpj + ") não encontrado!";
        }
		return "form";
	}
}

