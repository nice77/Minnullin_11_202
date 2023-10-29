gsap.utils.toArray(".card").forEach(item => {
    const post = item.querySelector(".post")
    post.onclick = (event) => {
        fade("./vacancies?pid=" + post.id)
    }
})