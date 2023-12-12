const urlParams = new URLSearchParams(window.location.search);
const productId = urlParams.get('id');
    
    // function leDados() {
    //     let strDados = localStorage.getItem('db');
    //     let objDados = {};
    
    
    //     if (strDados) {
    //         objDados = JSON.parse(strDados);
    //     }//fim if
    //     else {
    //         objDados =
    //         {
    //             peladas:
    //                 [
    //                     {
    //                         id: '0',
    //                         nome: 'Peladeiro_Puc',
    //                         esporte: 'volei',
    //                         quantidade: '15',
    //                         cidade:'BH',
    //                         bairro: 'Coração Eucaristico',
    //                         logradouro: 'Rua Dom Jose Gaspar',
    //                         numero: '500',
    //                         inicio: '14:00',
    //                         fim: '16:00',
    //                         data:'20/12/2023',
    //                         users: ['0','1','2']
    //                     }
    //                 ]
    //         }
    
    //     }//fim else
    
    //     return objDados;
    // }//fim ler dados

    

    function imprimeDados() 
    {
        $.ajax({
            url: `http://localhost:6789/Partida/${productId}`,
            dataType: 'json', // Defina o tipo de dados esperado
            success: function (data) {

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
            let tipo_partida = document.getElementById('tipo_partida');
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
            let strHtml12 = '';
    
            let objDados = data;
            
            strHtml1 = `<h3>${objDados[0].nome}</h3>`
            nome_partida.innerHTML = strHtml1;
            strHtml2 = `<p>ID:${objDados[0].id_partida}</p>`
            id_partida.innerHTML = strHtml2;
            strHtml3 = `<p>${objDados[0].esporte}</p>`
            esporte_partida.innerHTML = strHtml3;
            strHtml4 = `<p>QntdMax:${objDados[0].jog_max}</p>`
            qntd_partida.innerHTML = strHtml4;
            strHtml5 = `<p>Cidade:${objDados[0].cidade}</p>`
            cidade_partida.innerHTML = strHtml5;
            strHtml6 = `<p>Bairro:${objDados[0].bairro}</p>`
            bairro_partida.innerHTML = strHtml6;
            strHtml7 = `<p>Logradouro:${objDados[0].logr}</p>`
            logr_partida.innerHTML = strHtml7;
            strHtml8 = `<p>Número:${objDados[0].num}</p>`
            num_partida.innerHTML = strHtml8;
            strHtml9 = `<p>Inicio:${objDados[0].inicio}</p>`
            inicio_partida.innerHTML = strHtml9;
            strHtml10 = `<p>Fim:${objDados[0].fim}</p>`
            fim_partida.innerHTML = strHtml10;
            strHtml11 = `<p>${objDados[0].data}</p>`
            data_partida.innerHTML = strHtml11;
            strHtml12 = `<p>Tipo: ${objDados[0].tipo}</p>`
            tipo_partida.innerHTML = strHtml12;
             $('#nome_partida').html(strHtml1);
            },
            error: function (error) {
                console.error('Erro ao obter dados do servidor:', error);
            }
        });
    
    }//fim imprimeDados()
    

    // function EditarDados() 
    // {
    //     let tela = document.getElementById('editar_dados');
    //     let strHtml = '';
    //     let objDados = leDados();
    
        
    //         strHtml += `<a href="./editar.html?nome=${objDados.peladas[productId].nome}"><button class="btnEditar" type="button">Editar</button></a>`

        
    //     tela.innerHTML = strHtml;
     
    // }//fim imprimeDados()

    
    
    
   

