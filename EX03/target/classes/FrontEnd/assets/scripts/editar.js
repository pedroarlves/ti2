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
    
    
    function leDados() {
        let strDados = localStorage.getItem('db');
        let objDados = {};
    
    
        if (strDados) {
            objDados = JSON.parse(strDados);
        }//fim if
        else {
            objDados =
            {
                peladas:
                    [
                        {
                            id: '0',
                            nome: 'Peladeiro_Puc',
                            esporte: 'volei',
                            quantidade: '15',
                            cidade:'BH',
                            bairro: 'Coração Eucaristico',
                            logradouro: 'Rua Dom Jose Gaspar',
                            numero: '500',
                            inicio: '14:00',
                            fim: '16:00',
                            data:'20/12/2023',
                            users: ['0','1','2']
                        }
                    ]
            }
    
        }//fim else
    
        return objDados;
    }//fim ler dados

 
    
    function salvaDados(dados) {
        localStorage.setItem('db', JSON.stringify(dados));
    }//fim salvaDados
    

    function imprimeDados() 
    {
        let nome_partida = document.getElementById('nome_partida');
        let id_partida = document.getElementById('id_partida');
        let esporte_partida = document.getElementById('esporte_partida');
        let qntd_partida = document.getElementById('qntd_partida');
        let cidade_partida = document.getElementById('cidade_partida');
        let bairro_partida = document.getElementById('bairro_partida');
        let logr_partida = document.getElementById('logr_partida');
        let num_partida = document.getElementById('num_partida');
        let inicio_partida = document.getElementById('inicio_partida');
        let fim_partida = document.getElementById('fim_partida');
        let data_partida = document.getElementById('data_partida');
        let strHtml1 = '';
        let strHtml2 = '';
        let strHtml3 = '';
        let strHtml4 = '';
        let strHtml5 = '';
        let strHtml6 = '';
        let strHtml7 = '';
        let strHtml8 = '';
        let strHtml9 = '';
        let strHtml10 = '';
        let strHtml11 = '';
        let objDados = leDados();
    
   
            strHtml1 = `<h3>${objDados.peladas[productId].nome}</h3>`
            nome_partida.innerHTML = strHtml1;
            strHtml2 = `<p>ID:${objDados.peladas[productId].id}</p>`
            id_partida.innerHTML = strHtml2;
            strHtml3 = `<p>${objDados.peladas[productId].esporte}</p>`
            esporte_partida.innerHTML = strHtml3;
            strHtml4 = `<p>QntdMax:${objDados.peladas[productId].quantidade}</p>`
            qntd_partida.innerHTML = strHtml4;
            strHtml5 = `<p>Cidade:${objDados.peladas[productId].cidade}</p>`
            cidade_partida.innerHTML = strHtml5;
            strHtml6 = `<p>Bairro:${objDados.peladas[productId].bairro}</p>`
            bairro_partida.innerHTML = strHtml6;
            strHtml7 = `<p>Logradouro:${objDados.peladas[productId].logradouro}</p>`
            logr_partida.innerHTML = strHtml7;
            strHtml8 = `<p>Número:${objDados.peladas[productId].numero}</p>`
            num_partida.innerHTML = strHtml8;
            strHtml9 = `<p>Inicio:${objDados.peladas[productId].inicio}</p>`
            inicio_partida.innerHTML = strHtml9;
            strHtml10 = `<p>Fim:${objDados.peladas[productId].fim}</p>`
            fim_partida.innerHTML = strHtml10;
            strHtml11 = `<p>${objDados.peladas[productId].data}</p>`
            data_partida.innerHTML = strHtml11;

     
    }//fim imprimeDados()
    



    
    
    
    //botao de enviar
        document.getElementById(' BTNenviar_mensagens').addEventListener('click', incluirMensagem);
    
    
   

