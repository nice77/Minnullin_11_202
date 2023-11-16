$(document).ready(() => {
    const emailRegexp = /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
    const phoneRegexp = /^((\+7|7|8)+([0-9]){10})$/
    const passwordRegexp = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,}$/


    const submitBtn = $("#submit-btn")
    submitBtn.enabled = false

    let emailBool = false
    let passBool = false
    let phoneBool = false
    let nameBool = false

    gsap.from("form > *", {
        duration: 0.2,
        opacity: 0,
        filter: "blur(5px)"
    })

    const fadeOut = (path) => {
        gsap.to("form", {
            duration: 0.2,
            opacity: 0,
            filter: "blur(5px)",
            onComplete: () => {
                window.location.href = path
            }
        })
    }

    $("#redirect-btn").on("click", (event) => fadeOut(path="./register"))

    $("#redirect-btn-register").on("click", (event) => fadeOut(path="./auth"))


    // if (document.getElementsByName("email")[0] != null) {
    //     document.getElementsByName("email")[0].oninput = (event) => {
    //         if (!emailRegexp.test(event.target.value) && event.target.value.length !== 0) {
    //             event.target.style.backgroundColor = "#ff2f2f"
    //             document.getElementById('submit-btn').disabled = true
    //         }
    //         else {
    //             event.target.style.backgroundColor = "#222"
    //             document.getElementById('submit-btn').disabled = false
    //         }
    //     }
    // }
    //
    // if (document.getElementsByName("phone")[0] != null) {
    //     document.getElementsByName("phone")[0].oninput = (event) => {
    //         if (!phoneRegexp.test(event.target.value) && event.target.value.length !== 0) {
    //             event.target.style.backgroundColor = "#ff2f2f"
    //             document.getElementById('submit-btn').disabled = true
    //         }
    //         else {
    //             event.target.style.backgroundColor = "#222"
    //             document.getElementById('submit-btn').disabled = false
    //         }
    //     }
    // }

    const checkAll = () => {
        console.log(emailBool)
        console.log(nameBool)
        console.log(passBool)
        console.log(phoneBool)
        console.log("   ")
        if (emailBool && nameBool && passBool && phoneBool) {
            submitBtn.prop("disabled", false)
        }
        else {
            submitBtn.prop("disabled", true)
        }
    }

    const emailField = $("#email")
    emailField.on("input", (event) => {
        if (!emailRegexp.test(emailField.val()) && emailField.val().length !== 0) {
            event.target.style.backgroundColor = "#ff2f2f"
            emailBool = false
        }
        else {
            event.target.style.backgroundColor = "#222"
            emailBool = true
        }
        checkAll()
    })

    const phoneField = $("#phone")
    phoneField.on("input", (event) => {
        if (!phoneRegexp.test(phoneField.val()) && phoneField.val().length !== 0) {
            event.target.style.backgroundColor = "#ff2f2f"
            phoneBool = false
        }
        else {
            event.target.style.backgroundColor = "#222"
            phoneBool = true
        }
        checkAll()
    })

    const nameField = $("#name")
    nameField.on("input", (event) => {
        nameBool = nameField.val().length > 0
        checkAll()
    })

    const passwordField = $("#password")
    passwordField.on("input", (event) => {
        if (!passwordRegexp.test(passwordField.val())) {
            event.target.style.backgroundColor = "#ff2f2f"
            passBool = false
        }
        else {
            event.target.style.backgroundColor = "#222"
            passBool = true
        }
        checkAll()
    })
})
