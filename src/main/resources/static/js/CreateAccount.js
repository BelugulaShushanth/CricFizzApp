window.onload = () => {
    let password = document.getElementById('password');
    let rePassword = document.getElementById('rePassword');
    let showPassword = document.getElementById('showPassword');
    showPassword.addEventListener('click', () => {
        if(password.type === "password" && rePassword.type === "password"){
            password.type = "text";
            rePassword.type = "text";
        }
        else{
            password.type = "password";
            rePassword.type = "password";
        }
    })

    rePassword.addEventListener("keyup", () => {
        let passwordNew = document.getElementById('password');
        let pwdMismatch = document.getElementById('pwdMismatch');
        if(rePassword.value !== passwordNew.value){
            pwdMismatch.innerText = "Password mismatch";
        }
        else if(rePassword.value === passwordNew.value){
            console.log("EQUAL")
            pwdMismatch.innerText = "";
        }
    })
}