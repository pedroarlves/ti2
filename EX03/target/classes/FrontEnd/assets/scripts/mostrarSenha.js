function mostrarSenhaLogin() {
    let input = document.querySelector('#senha');
    if(input.getAttribute('type') == 'password') {
        input.setAttribute('type', 'text');
    } else {
        input.setAttribute('type', 'password');
    }
    
}


function mostrarSenhaR1() {
    let input = document.querySelector('#senha_registro');
    let input1 = document.querySelector('#senha_confirmacao');
    if(input.getAttribute('type') == 'password') {
        input.setAttribute('type', 'text');
        input1.setAttribute('type', 'text');
    } else {
        input.setAttribute('type', 'password');
        input1.setAttribute('type', 'password');
    }
}


function mostrarSenhaR2() {
    let input1 = document.querySelector('#senha_confirmacao_estabelecimento');
    let input = document.querySelector('#senha_estabelecimento_registro');
    if(input.getAttribute('type') == 'password') {
        input.setAttribute('type', 'text');
        input1.setAttribute('type', 'text');
    } else {
        input.setAttribute('type', 'password');
        input1.setAttribute('type', 'password');
    }
}

