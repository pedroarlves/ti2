const form = document.querySelector('form');
form.addEventListener('submit', (event) => {
  event.preventDefault();
  // faça a validação ou processamento adicional aqui
}); 

function Validate() {
    let username = document.getElementById("usuario").value;
    let password = document.getElementById("senha").value;
    
    $.ajax({
        url: `http://localhost:6789/Jogador/`+ username,
        dataType: 'json', // Defina o tipo de dados esperado
        success: function (data) {
            var jogador = data[0];

            if (username == jogador.cpf && password == jogador.senha) {
                window.location.href = "../index.html";
            } else {
                alert("Falha no Login");
            }
        },
        error: function (error) {
            console.error('Erro ao obter dados do servidor:', error);
        }
    });
    
}

document.getElementById('btnLogin').addEventListener('click', Validate);