gsap.registerPlugin(ScrollSmoother, ScrollTrigger)

ScrollSmoother.create({
    wrapper: '.wrapper',
    content: '.content',
    smooth: 1,
    effects: true
})

gsap.to('.hero-section', {
    opacity: 0,
    filter: 'blur(10px)',
    scrollTrigger: {
        trigger: '.hero-section',
        start: 'center',
        end: 'bottom',
        scrub: true
    }
})

const itemsLeft = gsap.utils.toArray('.content-section__column.left .content-section__item')
itemsLeft.forEach( item =>
    gsap.fromTo(item, {
        x: -50,
        opacity: 0
    }, {
        x: 0,
        opacity: 1,
        scrollTrigger: {
            trigger: item,
            start: 'top bottom',
            scrub: true
        }
    })
)


const itemsRight = gsap.utils.toArray('.content-section__column.right .content-section__item')
itemsRight.forEach( item =>
    gsap.fromTo(item, {
        x: 50,
        opacity: 0
    }, {
        x: 0,
        opacity: 1,
        scrollTrigger: {
            trigger: item,
            start: 'top bottom',
            scrub: true
        }
    })
)

gsap.to('.content-section', {
    background: "#050505",
    scrollTrigger: {
        trigger: '.basement',
        start: 'top bottom',
        end: 'bottom bottom',
        scrub: true
    }
})

$(document).ready(() => {
    $("button").on("click", (event) => {
        window.location.href = "./profile"
    })
})