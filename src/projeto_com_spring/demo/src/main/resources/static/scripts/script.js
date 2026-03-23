

    var cpfInput = document.getElementById("cpf");


    function validarCpf(cpf) {
        if (cpf === null || cpf === undefined) {
            throw new Error("CPF inválido");
        }
        if (!/^\d+$/.test(cpf)) {
            throw new Error("CPF deve conter apenas números");
        }
        if (cpf.length > 11 || cpf.length < 11) {
            throw new Error("CPF deve conter 11 digitos");
        }
    }


    var form = document.querySelector(".register-form");


    //função responsável por enviar os dados para minha API
    function sendData() {
        var cliente =
            {
                "cpf": cpfInput.value,
                "nome": document.getElementById("name").value,
                "endereco": document.getElementById("inputAddress").value,
                "senha": document.getElementById("input-password").value,
                "email": document.getElementById("input-email").value,
            }

        return cliente;
    }


    form.addEventListener("submit", async(e) => {
        e.preventDefault()
        var password = document.getElementById("input-password").value;

        var samePassword = document.getElementById("repeat-password").value;
        console.log(password)
        try {
            validarCpf(cpfInput.value);
        } catch (e) {
            alert("Cpf inválido!")
            return new Error(e.message);
        }
        try {
            validatePassword(password, samePassword);
        } catch (error) {
            alert(error.message);
        }

        const dados = sendData();
        try
        {
            const response = await fetch(`https://deloitte-springboot-bootcamp-bngvdfg2ewczdzeb.brazilsouth-01.azurewebsites.net/clientes?cpf=${cpfInput.value}`, {
                method: "GET",
                headers:{
                    "Content-Type": "application/json",
                    "Accept": "application/json"
                }


            })
            if(response.status === 200 || response.status === 201) {
                alert('Cpf já cadastrado no sistema!')
                throw new Error("Cpf já cadastrado no sistema!");
            }
        }
        catch (error)
            {
                alert(error.message);
                console.log("erro: " + error.message);
            }

        try
        {
            const response = await fetch(`https://deloitte-springboot-bootcamp-bngvdfg2ewczdzeb.brazilsouth-01.azurewebsites.net/clientes?email=${dados.email}`, {
                method: "GET",
                headers:{
                    "Content-Type": "application/json",
                    "Accept": "application/json"
                }


            })
            if(response.status === 200 || response.status === 201) {
                alert('Email já cadastrado no sistema!')
                throw new Error("Email já cadastrado no sistema!");
            }
        }
        catch(error) {
            console.log("erro:" + error);
        }

        try {
            const response =  await fetch("https://deloitte-springboot-bootcamp-bngvdfg2ewczdzeb.brazilsouth-01.azurewebsites.net/clientes", {
                    method: "POST",
                    headers:{
                        "Content-Type": "application/json",
                        "Accept": "application/json",

                    },
                    body: JSON.stringify(dados),
                })


                if (response.status === 201) {
                    alert("Cliente com sucesso!");
                    localStorage.setItem("usuarioLogado", JSON.stringify(dados));
                    window.location.href = "https://deloitte-springboot-bootcamp-bngvdfg2ewczdzeb.brazilsouth-01.azurewebsites.net/login.html";

                } else {
                    const error = await response.text();
                    alert(error.message);
                }

            }
            catch (e)
            {
                alert("Ocorreu um erro na hora de enviar os dados!");
            }





    });


    function validatePassword(password, samePassword) {
        if (password.length < 6) {
            alert("A senha deve conter mais que 6 caracteres")
            throw new Error("A senha deve conter mais que 6 caracteres");
        }
        if (!(password === samePassword)) {
            alert("As senhas não se coincidem")
            throw new Error("As senhas não se coincidem.")
        }


    }











