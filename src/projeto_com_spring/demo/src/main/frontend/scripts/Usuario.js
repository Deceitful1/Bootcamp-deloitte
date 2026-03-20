const container = document.getElementById("homepage-main");

document.addEventListener("DOMContentLoaded", async () => {
    const dados = localStorage.getItem("usuarioLogado");

    if (dados) {
        const jsonParse = JSON.parse(dados);
        const termo = jsonParse.emailOrCpf;

        let response = await fetch(`http://localhost:8080/clientes?email=${termo}`);
        if (response.status === 404) {
            response = await fetch(`http://localhost:8080/clientes?cpf=${termo}`);
        }

        if (response.ok) {
            const usuario = await response.json();
            exibirDadosEstaticos(usuario);
        } else {
            container.innerHTML = "<p>Erro ao carregar perfil. Faça login novamente.</p>";
        }
    }
});

function exibirDadosEstaticos(usuario) {
    container.innerHTML = "";
    const card = document.createElement("div");
    card.classList.add("profile-card");

    card.innerHTML = `
        <h3>Meu Perfil</h3>
        <hr>
        <p><strong>Nome:</strong> ${usuario.nome}</p>
        <p><strong>E-mail:</strong> ${usuario.email}</p>
        <p><strong>Endereço:</strong> ${usuario.endereco || "Não informado"}</p>
        <p><strong>CPF:</strong> ${usuario.cpf}</p>
        <div style="margin-top: 20px;">
            <button id="edit-btn">Editar</button>
            <button id="delete-btn" style="background-color: #dc3545; color: white;">Excluir Conta</button>
        </div>
    `;

    container.appendChild(card);

    document.getElementById("edit-btn").addEventListener("click", () => exibirFormularioEdicao(usuario));
    document.getElementById("delete-btn").addEventListener("click", () => deletarConta(usuario.id));
}

function exibirFormularioEdicao(usuario) {
    container.innerHTML = "";
    const card = document.createElement("div");
    card.classList.add("profile-card");

    card.innerHTML = `
        <h3>Editar Informações</h3>
        <hr>
        <label>Nome:</label>
        <input type="text" id="input-nome" value="${usuario.nome}">
        <label>E-mail:</label>
        <input type="email" id="input-email" value="${usuario.email}">
        <label>Endereço:</label>
        <input type="text" id="input-endereco" value="${usuario.endereco || ""}">
        <p><strong>CPF:</strong> ${usuario.cpf} (Não editável)</p>
        <div style="margin-top: 15px;">
            <button id="save-btn">Salvar Alterações</button>
            <button id="cancel-btn" style="background-color: #6c757d; color: white;">Cancelar</button>
        </div>
    `;

    container.appendChild(card);

    document.getElementById("save-btn").addEventListener("click", () => salvarEdicao(usuario));
    document.getElementById("cancel-btn").addEventListener("click", () => exibirDadosEstaticos(usuario));
}

async function salvarEdicao(usuarioOriginal) {
    const updatedData = {
        id: usuarioOriginal.id,
        nome: document.getElementById("input-nome").value,
        email: document.getElementById("input-email").value,
        endereco: document.getElementById("input-endereco").value,
        cpf: usuarioOriginal.cpf,
        senha: usuarioOriginal.senha
    };

    const response = await fetch(`http://localhost:8080/clientes`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(updatedData)
    });

    if (response.ok) {
        alert("Dados atualizados!");
        localStorage.setItem("usuarioLogado", JSON.stringify({
            emailOrCpf: updatedData.email,
            password: updatedData.senha
        }));
        exibirDadosEstaticos(updatedData);
    } else {
        alert("Erro ao atualizar.");
    }
}

async function deletarConta(id) {
    if (confirm("Tem certeza que deseja excluir sua conta? Esta ação não pode ser desfeita.")) {
        const response = await fetch(`http://localhost:8080/clientes/${id}`, {
            method: "DELETE"
        });

        if (response.ok) {
            alert("Conta excluída com sucesso.");
            localStorage.removeItem("usuarioLogado");
            window.location.href = "login.html";
        } else {
            alert("Erro ao excluir a conta.");
        }
    }
}