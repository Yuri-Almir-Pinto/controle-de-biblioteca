function estadoSelect (elemento, id) {
    const elementoPai = elemento.parentNode.parentNode;
    elementoPai.style.backgroundColor = "#FFFF00";

    const botao = document.getElementById("submit_button");
    botao.disabled = false;

    elemento.name = "livro_" + id;


}