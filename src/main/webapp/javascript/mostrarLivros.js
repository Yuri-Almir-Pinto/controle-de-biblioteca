function estadoSelect (elemento, id) {
    const elementoPai = elemento.parentNode.parentNode;
    elementoPai.style.backgroundColor = "#FCF55F";

    const botao = document.getElementById("submit_button");
    botao.disabled = false;
    botao.className = "button-green";

    elemento.name = "livro_" + id;
}

function deletar(id) {
    const form = document.getElementById("form");
    form.action = "DeletarLivroServlet";

    const input = document.createElement("input");
    input.hidden = true;
    input.value = id;
    input.name = "livro_id";

    form.appendChild(input);
    form.submit();
}