const form = document.querySelector(".login-form");
const btn = document.querySelector("#login-btn");
btn.addEventListener("click", async (e) => {
    e.preventDefault();

    console.log(form)
    var password = document.getElementById("input-password").value;
    var emailOrCpf = document.getElementById("input-email-cpf").value;

    const response = await fetch("http://localhost:8080/clientes/auth/login", {
        method: "POST",
        headers:
            {
                "Content-Type": "application/json",
                "Accept": "application/json"
            },

        body: JSON.stringify({
            "login": emailOrCpf,
            "password": password
        })


    })
    console.log("Status da resposta:", response.status);
    if (response.ok) {
        localStorage.setItem("usuarioLogado", JSON.stringify({emailOrCpf, password}));
        window.location.href = "https://deloitte-springboot-bootcamp-bngvdfg2ewczdzeb.brazilsouth-01.azurewebsites.net/homepage.html"
    }
    else
    {
        alert("Email/Cpf ou senha inválidos.")
    }

})

form.addEventListener("submit", async (e) => {
    e.preventDefault()



})