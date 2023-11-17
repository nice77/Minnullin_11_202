$(document).ready(() => {
    const subscribeBtn = $("#subscribeBtn")
    const createNewBtn = $("#createNewBtn")
    const expandAbout = $("#expandAbout")
    const dialog = $("#hidden")

    expandAbout.on("click", () => {
        dialog.get(0).show()
    })
    $("#closeDialog").on("click", () => {
        dialog.get(0).close()
    })

    createNewBtn.on("click", (event) => {
        window.location.href = "./create"
    })

    subscribeBtn.on("click", (event) => {
        $.ajax({
            url: "./followsConnection",
            method: "POST",
            data: {
                userId: subscribeBtn.attr("data-user-id"),
                isSubscribed: subscribeBtn.attr("data-is-subscribed")
            },
            success: (result) => {
                let isSubscribed = JSON.parse(subscribeBtn.attr("data-is-subscribed"))
                if (isSubscribed) {
                    subscribeBtn.val("Подписаться")
                }
                else {
                    subscribeBtn.val("Отписаться")
                }

                subscribeBtn.attr("data-is-subscribed", !isSubscribed)
            }
        })
    })
})
gsap.utils.toArray(".card").forEach(item => {
    const post = item.querySelector(".post")
    post.onclick = (event) => {
        fade("./vacancies?pid=" + post.id)
    }
})