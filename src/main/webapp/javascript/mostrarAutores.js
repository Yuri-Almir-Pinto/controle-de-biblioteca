function deletar(id) {
    if (confirm("Atenção: Deletar um autor faz com que TODOS os seus livros sejam excluidos tambem. Deseja prosseguir?")) {
        const form = document.getElementById("form");
        form.action = "DeletarAutorServlet";

        const input = document.createElement("input");
        input.hidden = true;
        input.value = id;
        input.name = "autor_id";

        form.appendChild(input);
        form.submit();
    }
}
function nomeMudado (elemento, id) {
    const elementoPai = elemento.parentNode.parentNode;
    elementoPai.style.backgroundColor = "#FCF55F";

    const botao = document.getElementById("submit_button");
    botao.disabled = false;
    botao.className = "button-green";

    elemento.name = "autor_" + id;
}
