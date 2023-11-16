const start = () => {
    gsap.to('.main-page__element', {
        opacity: 1,
        duration: 0.2,
        stagger: 0.1
    })
}

start()

const fade = (path) => {
    const timeline = gsap.timeline()

    const fadeAnim = gsap.to('.main-page__element > *', {
        opacity: 0,
        duration: 0.3,
        stagger: 0.01,
        onComplete: () => {
            window.location.href = path
        }
    }, ">")

    const backMenu = gsap.to('.main-page__menu', {
        top: '-100px',
        duration: 0.3
    })

    timeline.to(backMenu).to(fadeAnim)
}

const toX = () => {
    const timeline = gsap.timeline()
    const uno = gsap.to("#expand_button span:nth-child(1)", {
        transform: "translateY(8px)",
        rotation: 45
    })
    const dos = gsap.to("#expand_button span:nth-child(3)", {
        transform: "translateY(-8px)",
        rotation: 45
    })
    const tres = gsap.to("#expand_button span:nth-child(2)", {
        rotation: -45
    })
    $(".main-page__menu").css({"top": 5 + $("header").height() + "px"})
    timeline.to(uno).to(dos).to(tres)
}

const toBurger = () => {
    const timeline = gsap.timeline()
    const uno = gsap.to("#expand_button span:nth-child(1)", {
        transform: "inherit"
    })
    const dos = gsap.to("#expand_button span:nth-child(2)", {
        rotation: 0
    })
    const tres = gsap.to("#expand_button span:nth-child(3)", {
        transform: "inherit"
    })
    $(".main-page__menu").css({"top": "-150px"})
    timeline.to(uno).to(dos).to(tres)
}

let opened = false
$(document).ready(() => {
    $("#expand_button").on("click", () => {
        opened = !opened
        opened ? toX() : toBurger()
    })

    $('#profile').on("click", (event) => {
        fade("./profile")
    })

    $('#vacancies').on("click", (event) => {
        fade("./vacancies")
    })

    $('#settings').on("click", (event) => {
        fade("./settings")
    })

    $('#follows').on("click", (event) => {
        fade("./follows")
    })

    $('#news').on("click", (event) => {
        fade("./news")
    })
})