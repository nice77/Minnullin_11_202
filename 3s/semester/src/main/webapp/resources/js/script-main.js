const start = () => {
    gsap.to('.main-page__element', {
        opacity: 1,
        duration: 0.3,
        stagger: 0.1
    })
}

start()

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

document.getElementById('profile').onclick = (event) => {
    fade("./profile")
}

document.getElementById('vacancies').onclick = (event) => {
    fade("./vacancies")
}

document.getElementById('settings').onclick = (event) => {
    fade("./settings")
}