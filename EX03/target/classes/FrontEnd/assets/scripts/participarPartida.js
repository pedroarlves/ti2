// Função: Enviar requisição POST para entrar na partida

function EntrarPartida() {
    $.ajax({
        url: 'http://localhost:6789/Participa/' + productId,
        type: 'POST', // Defina o método para POST
        success: function () {
            console.log('Requisição POST enviada com sucesso');
            alert('Você entrou na partida!');
        },
        error: function (error) {
            console.error('Erro ao enviar requisição POST:', error);
        }
    });
}

function imprimeDados(){
    $.ajax({
        url: 'http://localhost:6789/Participa/list',
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

document.getElementById('btnEntrar').addEventListener('click', EntrarPartida);