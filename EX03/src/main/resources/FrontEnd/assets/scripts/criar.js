const urlParams = new URLSearchParams(window.location.search);
const productId = urlParams.get('id');
    
    
    var objPessoas = {
        pessoas:
        [
            {
                id: '0',
                nome: 'Joaquim',
                fav_esporte: 'peteca',
                descricao: 'Gosto de jogar peteca'
            },
            {
                id: '1',
                nome: 'Ana',
                fav_esporte: 'futebol',
                descricao: 'Gosto de jogar futebol'
            },
            {
                id: '2',
                nome: 'Rouanet',
                fav_esporte: 'futebol',
                descricao: 'Gosto de jogar futebol'
            }
        ]

    }



    
    
    function salvaDados(dados) {
        localStorage.setItem('db', JSON.stringify(dados));
    }//fim salvaDados
    
    

    function imprimeDados() {
        $.ajax({
            url: 'http://localhost:6789/Partida/',
            dataType: 'json', // Defina o tipo de dados esperado
            success: function (data) {

                var partidas = data;


                var htmlDestacados = '';
                for (var i = 0; i < partidas.length; i++) {
                    htmlDestacados += `<div class="apresentar_partida"><a href="./modulos/partida.html?id=${partidas[i].id_partida}"> 
                    <div class="apresentar_nome">${partidas[i].nome}</div>
                    <div class="apresentar_esporte">${partidas[i].esporte}</div>
                    <div class="apresentar_cidade">${partidas[i].cidade}</div>
                    <div class="apresentar_bairro">${partidas[i].bairro}</div>
                    <div class="apresentar_horario">${partidas[i].inicio} às ${partidas[i].fim}</div>
                    <div class="apresentar_data">${partidas[i].data}</div>
                    <div class="apresentar_qntd">${partidas[i].jog_max}</div></a></div>`
                }


                $('#apresentar_jogos').html(htmlDestacados);
            },
            error: function (error) {
                console.error('Erro ao obter dados do servidor:', error);
            }
        });
    }//fim imprimeDados()
    
    function filtrarDados() {
        let strFiltro = document.getElementById('pesquisarPartidas').value;
        console.log(strFiltro);
        console.log("erro");
        $.ajax({
            url: 'http://localhost:6789/Partida/list/' + strFiltro,
            dataType: 'json', // Defina o tipo de dados esperado
            success: function (data) {

                var partidas = data;


                var htmlDestacados = '';
                for (var i = 0; i < partidas.length; i++) {
                    htmlDestacados += `<div class="apresentar_partida"><a href="./modulos/partida.html?id=${partidas[i].id_partida}"> 
                    <div class="apresentar_nome">${partidas[i].nome}</div>
                    <div class="apresentar_esporte">${partidas[i].esporte}</div>
                    <div class="apresentar_cidade">${partidas[i].cidade}</div>
                    <div class="apresentar_bairro">${partidas[i].bairro}</div>
                    <div class="apresentar_horario">${partidas[i].inicio} às ${partidas[i].fim}</div>
                    <div class="apresentar_data">${partidas[i].data}</div>
                    <div class="apresentar_qntd">${partidas[i].jog_max}</div></a></div>`
                }


                $('#apresentar_jogos').html(htmlDestacados);
            },
            error: function (error) {
                console.error('Erro ao obter dados do servidor:', error);
            }
        });
    }



     function incluirPeladas() 
    {
     // ler os dados do localStorage
        let objDados = leDados();
    
     // incluir uma nova pelada
        let strId = objDados.peladas.length;
        let strNome     = document.getElementById('nome_pelada'  ).value;
        let strEsporte  = document.getElementById('esporte_pelada' ).value;
        let strQntd  = document.getElementById('tamanho_pelada'  ).value;
        let strCidade = document.getElementById('cidade_pelada').value;
        let strBairro = document.getElementById('bairro_pelada').value;
        let strLogr = document.getElementById('logradouro_pelada').value;
        let strNumero = document.getElementById('numero_pelada').value;
        let strInicio   = document.getElementById('horario_inicio' ).value;
        let strFim      = document.getElementById('horario_fim'   ).value;
        let strData = document.getElementById('data_pelada').value;
    
    
    
        let novaPelada = {
                id :strId,
                nome: strNome,
                esporte: strEsporte,
                quantidade: strQntd,
                cidade: strCidade,
                bairro: strBairro,
                logradouo: strLogr,
                numero: strNumero,
                inicio: strInicio,
                fim: strFim,
                data: strData
        };
    
        objDados.peladas.push(novaPelada);
     // Salvar os dados no localStorage novamente
        salvaDados(objDados);
    
     // imprimir dados
        imprimeDados();
    
    }//fim incluirPeladas()
    
    
    //botao de enviar
        document.getElementById('btnFiltrar').addEventListener('click', filtrarDados);
