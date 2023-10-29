gsap.utils.toArray(".card").forEach((item) => {
    const vacancy = item.querySelector(".vacancy")
    vacancy.onclick = (event) => {
        fade("./vacancies?vid=" + vacancy.id)
    }
})

document.getElementById('createNewBtn').onclick = (event) => {
    fade("./create")
}
