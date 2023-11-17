$(document).ready(() => {
    const emailRegexp = /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
    const phoneRegexp = /^((\+7|7|8)+([0-9]){10})$/
    const passwordRegexp = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,}$/
    const nameRegexp = /^([A-Z]|[a-z]|[А-Я]|[а-я]){1,}$/


    const checkbox = $("#company")
    const submitBtn = $("#submit-btn")
    submitBtn.enabled = false

    let emailBool = new Boolean(false)
    let passBool = new Boolean(false)
    let phoneBool = new Boolean(false)
    let nameBool = new Boolean(false)

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

    const checkEmail = () => {
        $.ajax({
            url: "./registerConnection",
            method: "GET",
            data: {
                isCompany: checkbox.is(":checked") ? "company" : "user",
                email: emailField.val()
            },
            success: (resultString) => {
                emailBool.value = JSON.parse(resultString);
                console.log(emailBool)
            }
        })
    }

    $("#redirect-btn").on("click", (event) => fadeOut(path="./register"))

    $("#redirect-btn-register").on("click", (event) => fadeOut(path="./auth"))

    const checkAll = () => {
        if (emailBool.value && nameBool.value && passBool.value && phoneBool.value) {
            submitBtn.prop("disabled", false)
        }
        else if (emailBool.value && !nameBool.value && checkbox.checked && passBool.value && phoneBool.value ) {
            submitBtn.prop("disabled", false)
        }
        else {
            submitBtn.prop("disabled", true)
        }
    }

    const onInputEventHandler = (event, regexp, inputField, booleanValue) => {
        if (!regexp.test(inputField.val()) && inputField.val().length !== 0) {
            event.target.style.backgroundColor = "#ff2f2f"
            booleanValue.value = false
        }
        else {
            event.target.style.backgroundColor = "#222"
            booleanValue.value = true
        }
    }

    const onNameFieldInputHandler = (event) => {
        const isCompany = checkbox.is(":checked")
        if (!nameRegexp.test(nameField.val()) && !isCompany) {
            event.target.style.backgroundColor = "#ff2f2f"
            nameBool.value = false
        }
        else {
            event.target.style.backgroundColor = "#222"
            nameBool.value = true
        }
    }

    checkbox.on("change", (event) => {
        console.log(42)
        onNameFieldInputHandler(event)
        checkAll()
    })

    const emailField = $("#email")
    emailField.on("input", (event) => {
        onInputEventHandler(event, emailRegexp, emailField, emailBool)
        checkEmail()
        checkAll()
    })

    const phoneField = $("#phone")
    phoneField.on("input", (event) => {
        onInputEventHandler(event, phoneRegexp, phoneField, phoneBool)
        checkAll()
    })

    const nameField = $("#name")
    nameField.on("input", (event) => {
        onNameFieldInputHandler(event)
        checkAll()
    })

    const passwordField = $("#password")
    passwordField.on("input", (event) => {
        onInputEventHandler(event, passwordRegexp, passwordField, passBool)
        checkAll()
    })
})
