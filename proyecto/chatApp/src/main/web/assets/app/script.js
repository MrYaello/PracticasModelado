let input = document.getElementById("name");
let label = document.getElementById("label");
let form = document.getElementsByClassName("form")[0];
let error = document.getElementById("error");

input.addEventListener("focusin", () => {
    document.getElementById("button").scrollIntoView();
    label.style.color = "#dddfe2";
    hideError();
})

input.addEventListener("focusout", () => {
    label.style.color = "#ccd0d5";
    if (input.value == "") label.style.opacity = "100%";
    hideError();
})

input.addEventListener("input", () => {
    label.style.opacity = "0%";
})

const navBar = document.querySelector(".navigation-bar");
const vw = Math.max(document.documentElement.clientWidth || 0, window.innerWidth || 0);

function showError(msg) {
    error.textContent = msg;
    error.style.visibility = "visible";
    form.style.height = "110px";
}

function hideError() {
    error.style.visibility = "hidden";
    form.style.height = "80px";
}

function validateForm() {
    let name = document.forms["user"]["name"].value;
    if (name == "") {
        showError("Debe proporcionar un nombre de usuario.");
        return false;
    } else if (name.length > 8) {
        showError("El nombre de usuario no puede mayor a 8 caracteres.")
        return false;
    } else {
        window.location.href = `?name=${name}`
        return true;
    }
  }

if (vw <= 768) {
    var element = document.querySelector("nav");
    var position = element.getBoundingClientRect();
    navBar.style.cssText += `top: ${position.bottom - 1}px`;
    window.addEventListener('scroll', function() {
        position = element.getBoundingClientRect();
        var navPosition = position.bottom;
        navBar.style.cssText += `top: ${navPosition - 1}px`;
    });
}
