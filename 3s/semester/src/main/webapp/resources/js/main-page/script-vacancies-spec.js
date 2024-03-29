$(document).ready(() => {
    if ($("#bottom-crosser").get(0) !== undefined) {
        let options = {
            root: null,
            rootMargin: "0px",
            threshold: 1.0,
        };
        let callback = function (entries, observer) {
            setTimeout(() => fetchCommentaries(), 500)
        };
        let observer = new IntersectionObserver(callback, options)
        observer.observe($("#bottom-crosser").get(0))
    }

    const drawComments = (commentsList, toTop=false) => {
        let i = 0
        commentsList.forEach(item => {
            const toAdd = $("<div>", {
                class: "comment",
            }).append($("<a>", {
                class: "link",
                text: item['name'] + " " + item['surname'],
                click: () => window.location.href = "./profile?userId=" + item['user_id']
            })).append($("<h3>", {
                text: item['text']
            })).append($("<h6>", {
                text: item['create_date']
            }))

            toTop ? $(".comments-list").prepend(toAdd) : $(".comments-list").append(toAdd)
        })
    }

    let offset = 0
    let startOffset = 0
    const commentariesButton = $("#getCommentaries")
    const bottomCrosser = $("#bottom-crosser")
    const fetchCommentaries = () => {
        $.ajax({
            url: "./commentsConnection",
            method: "GET",
            data: {
                data: bottomCrosser.attr('data-postId'),
                offset: offset
            },
            success: (resultString) => {
                if (resultString === "[]") {
                    bottomCrosser.css("display", "none")
                }
                else if ($(".comments-list").children.length !== 0) {
                    drawComments(JSON.parse(resultString).slice(startOffset))
                }
                else {
                    drawComments(JSON.parse(resultString))
                }
                offset += 10
            }
        })
    }

    const postCommentary = (commentaryText) => {
        $.ajax({
            url: "./commentsConnection",
            method: "POST",
            data: {
                text: commentaryText,
                post: bottomCrosser.attr('data-postId')
            },
            success: (resultString) => {
                drawComments(JSON.parse(resultString), true)
                startOffset += 1
            }
        })
    }

    const removeVacancy = (vacancyId) => {
        $.ajax({
            url: "./vacanciesConnection",
            method: "POST",
            data: {
                vacancyId: vacancyId
            },
            success: () => {
                window.location.href = "./vacancies"
            }
        })
    }

    const removePost = (postId) => {
        $.ajax({
            url: "./postsConnection",
            method: "POST",
            data: {
                postId: postId
            },
            success: () => {
                window.location.href = "./profile"
            }
        })
    }

    let edited = false
    $("#bodyInput").on("input", (event) => {
        if (!edited) {
            edited = true
            const elem = $("#bodyInput")
            elem.get(0).innerText = event.target.innerText.substring(event.target.innerText.length - 1)

            if(document.createRange)
            {
                range = document.createRange()
                range.selectNodeContents(elem.get(0))
                range.collapse(false)
                selection = window.getSelection()
                selection.removeAllRanges()
                selection.addRange(range)
            }
            else if(document.selection)
            {
                range = document.body.createTextRange()
                range.moveToElementText(elem)
                range.collapse(false)
                range.select()
            }
        }

        const bodyInputField = $("#hiddenInputField")
        bodyInputField.value = $("#bodyInput").innerText
    })

    $("#postCommentary").on("click", (event) => {
        postCommentary($("#bodyInput").get(0).innerText)
        $("#bodyInput").get(0).innerText = "Комментарий"
        edited = false
    })

    $("#editPost").on("click", (event) => {
        window.location.href = "./create?postId=" + $("#editPost").attr("data-post-id")
    })

    const removePostBtn = $("#removePostBtn")
    removePostBtn.on("click", (event) => {
        removePost(removePostBtn.attr("data-post-id"))
    })

    const removeVacancyBtn = $("#removeVacancyBtn")
    removeVacancyBtn.on("click", (event) => {
        removeVacancy(removeVacancyBtn.attr("data-vacancy-id"))
    })
})