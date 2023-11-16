$(document).ready(() => {
    let options = {
        root: null,
        rootMargin: "0px",
        threshold: 1.0,
    };
    let callback = function (entries, observer) {
        setTimeout(() => fetchPosts(), 500)
    };
    let observer = new IntersectionObserver(callback, options)
    observer.observe($("#bottom-crosser").get(0))
    let offset = 0

    const fetchPosts = () => {
        $.ajax({
            url: "./postsConnection",
            method: "GET",
            data: {
                offset: offset
            },
            success: (resultString) => {
                offset += 10
                console.log(JSON.parse(resultString))
                drawPosts(JSON.parse(resultString))
            }
        })
    }

    const drawPosts = (posts) => {
        posts.forEach((post) => {
            const toAdd = $("<div>", {
                class: "card"
            }).append($("<div>"), {
                class: "clickable post",
                id: post["id"]
            }).append($("<h3>", {
                text: post["header"]
            }))

            if (post["body"].length !== 0) {
                toAdd.append($("<h4>", {
                    text: post["body"]
                }))
            }

            toAdd.on("click", () => {
                window.location.href = "./vacancies?pid=" + post["id"]
            })

            $(".news-list").append(toAdd)
        })

    }
})