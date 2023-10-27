gsap.to('.main-page__element', {
    opacity: 1,
    duration: 0.3,
    stagger: 0.1
})

const fade = (path) => {
    gsap.to('.main-page__element', {
        opacity: 0,
        duration: 0.3,
        stagger: 0.1,
        onComplete: () => {
            window.location.href = path
        }
    })
}

const path = window.location.pathname.split("/")

if (window.location.pathname.includes("settings")) {
    document.getElementById("clickToUpload").onclick = (event) => {
        document.getElementById("inputFileUploader").click()
        document.getElementById("inputFileUploader").oninput = (eventInput) => {
            const filePath = document.getElementById("inputFileUploader").value.split("\\")
            document.getElementById("clickToUpload").value = filePath[filePath.length - 1]
        }
    }
}

if (window.location.href.includes("profile") || window.location.href.includes("company")) {
    document.getElementById('createNewBtn').onclick = (event) => {
        window.location.href = "./create"
    }
}

document.getElementById('profile').onclick = (event) => {
    fade("./profile")
}

document.getElementById('vacancies').onclick = (event) => {
    fade("./vacancies")
}

document.getElementById('settings').onclick = (event) => {
    fade("./settings")
}