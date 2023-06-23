function deletar(id) {
    const form = document.getElementById("form");
    form.action = "DeletarUsuarioServlet";

    const input = document.createElement("input");
    input.hidden = true;
    input.value = id;
    input.name = "usuario_id";

    form.appendChild(input);
    form.submit();
}

function loginMudado (elemento, id) {
    const elementoPai = elemento.parentNode.parentNode;
    elementoPai.style.backgroundColor = "#FCF55F";

    const botao = document.getElementById("submit_button");
    botao.disabled = false;
    botao.className = "button-green";

    elemento.name = "login_" + id;
}

function senhaMudado (elemento, id) {
    const elementoPai = elemento.parentNode.parentNode;
    elementoPai.style.backgroundColor = "#FCF55F";

    const botao = document.getElementById("submit_button");
    botao.disabled = false;
    botao.className = "button-green";

    elemento.name = "senha_" + id;
}