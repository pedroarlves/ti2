function Validate() {
    let strHtml = '';
    console.log("entrou");

    $.ajax({
        url: `http://localhost:6789/Jogador/header`,
        dataType: 'html', // Defina o tipo de dados esperado
        success: function (data) {
            strHtml = data;
            $('#header_index').html(strHtml);
        },
        error: function (error) {
            console.error('Erro ao obter dados do servidor:', error);
        }
    });
    
}
