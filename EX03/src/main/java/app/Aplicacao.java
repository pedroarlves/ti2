package app;

import static spark.Spark.*;

import service.EstabelecimentoService;
import service.JogadorService;
import service.PartidaService;
import service.Jog_Parti_PartidaService;

public class Aplicacao{
	private static JogadorService JogadorService = new JogadorService();
    private static EstabelecimentoService estabelecimentoService = new EstabelecimentoService();
    private static PartidaService partidaService = new PartidaService();
    private static Jog_Parti_PartidaService Jog_Parti_PartidaService = new Jog_Parti_PartidaService();
	
    public static void main(String[] args) {
        port(6789);
        
        staticFiles.location("/FrontEnd");
        
        post("/Jogador/insert", (request, response) -> JogadorService.insert(request, response));
        
        get("/Jogador/header", (request, response) -> JogadorService.loadHeader(request, response));

        post("/Jogador/login", (request, response) -> JogadorService.confirmLogin(request, response));
        
        get("/Jogador/:id", (request, response) -> JogadorService.get(request, response));
        
        get("/Jogador/", (request, response) -> JogadorService.getAll(request, response));

        get("/Jogador/update/:id", (request, response) -> JogadorService.getToUpdate(request, response));
        
        post("/Jogador/update/:id", (request, response) -> JogadorService.update(request, response));
           
        get("/Jogador/delete/:id", (request, response) -> JogadorService.delete(request, response));

        //------------------------------------Estabelecimento------------------------------------
        
        post("/Estabelecimento/insert", (request, response) -> estabelecimentoService.insert(request, response));

        get("/Estabelecimento/:id", (request, response) -> estabelecimentoService.get(request, response));
        
        get("/Estabelecimento/list/:orderby", (request, response) -> estabelecimentoService.getAll(request, response));

        get("/Estabelecimento/update/:id", (request, response) -> estabelecimentoService.getToUpdate(request, response));
        
        post("/Estabelecimento/update/:id", (request, response) -> estabelecimentoService.update(request, response));
           
        get("/Estabelecimento/delete/:id", (request, response) -> estabelecimentoService.delete(request, response));

        //--------------------------------------------Partida--------------------------------------------  
        
        post("/Partida/insert", (request, response) -> partidaService.insert(request, response));

        get("/Partida/list/:orderby", (request, response) -> partidaService.filterPartidas(request, response));

        get(("/Partida/list/"), (request, response) -> partidaService.getAll(request, response));

        get("/Partida/:id", (request, response) -> partidaService.get(request, response));
        
        get("/Partida/", (request, response) -> partidaService.getAll(request, response));

        get("/Partida/update/:id", (request, response) -> partidaService.getToUpdate(request, response));
        
        post("/Partida/update/:nome", (request, response) -> partidaService.update(request, response));
           
        get("/Partida/delete/:id", (request, response) -> partidaService.delete(request, response));
        
        post("/Participa/:id", (request, response) -> Jog_Parti_PartidaService.insert(request, response));

        get("/Participa/list", (request, response) -> Jog_Parti_PartidaService.getAll(request, response));
    }
}