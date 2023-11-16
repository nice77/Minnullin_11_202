$(document).ready(() => {
    const subscribeBtn = $("#subscribeBtn")
    const createNewBtn = $("#createNewBtn")

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