document.getElementById('createNewBtn').onclick = (event) => {
    fade("./create")
}
gsap.utils.toArray(".card").forEach(item => {
    const element = item.querySelector(".vacancy")
    element.onclick = (event) => {
        fade("./vacancies?vid=" + element.id)
    }
})
