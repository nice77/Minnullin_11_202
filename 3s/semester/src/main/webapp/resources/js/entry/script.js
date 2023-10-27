const emailRegexp = /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
const phoneRegexp = /^((\+7|7|8)+([0-9]){10})$/

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

if (document.getElementById("redirect-btn") != null) {
    document.getElementById("redirect-btn").onclick = (event) => fadeOut(path="./register")
}

if (document.getElementById("redirect-btn-register") != null) {
    document.getElementById("redirect-btn-register").onclick = (event) => fadeOut(path="./auth")
}

if (document.getElementsByName("email")[0] != null) {
    document.getElementsByName("email")[0].oninput = (event) => {
        if (!emailRegexp.test(event.target.value) && event.target.value.length !== 0) {
            event.target.style.backgroundColor = "#ff2f2f"
            document.getElementById('submit-btn').disabled = true
        }
        else {
            event.target.style.backgroundColor = "#222"
            document.getElementById('submit-btn').disabled = false
        }
    }
}

if (document.getElementsByName("phone")[0] != null) {
    document.getElementsByName("phone")[0].oninput = (event) => {
        if (!phoneRegexp.test(event.target.value) && event.target.value.length !== 0) {
            event.target.style.backgroundColor = "#ff2f2f"
            document.getElementById('submit-btn').disabled = true
        }
        else {
            event.target.style.backgroundColor = "#222"
            document.getElementById('submit-btn').disabled = false
        }
    }
}