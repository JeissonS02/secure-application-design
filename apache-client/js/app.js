async function login() {
    let requestData = {
        username: loginUser.value,
        password: loginPass.value
    };

    requestBox.innerText = JSON.stringify(requestData, null, 2);

    let res = await fetch(`${API_URL}/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(requestData)
    });

    let data = await res.json();

    if (data.token) localStorage.setItem("token", data.token);

    responseBox.innerText = JSON.stringify(data, null, 2);
    loginResult.innerText = data.message;

    showToken();
}

async function register() {
    let requestData = {
        username: regUser.value,
        password: regPass.value
    };

    requestBox.innerText = JSON.stringify(requestData, null, 2);

    let res = await fetch(`${API_URL}/register`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(requestData)
    });

    let data = await res.json();

    if (data.token) localStorage.setItem("token", data.token);

    responseBox.innerText = JSON.stringify(data, null, 2);
    registerResult.innerText = data.message;

    showToken();
}

function showToken() {
    tokenBox.innerText = localStorage.getItem("token") || "No hay token";
}

function logout() {
    localStorage.removeItem("token");
    showToken();
}

window.onload = showToken;