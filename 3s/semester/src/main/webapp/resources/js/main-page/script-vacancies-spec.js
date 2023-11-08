$(document).ready(() => {
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
    const fetchCommentaries = () => {
        $.ajax({
            url: "./commentsConnection",
            method: "GET",
            data: {
                data: commentariesButton.attr('data-postId'),
                offset: offset
            },
            success: (resultString) => {
                if (resultString === "[]") {
                    $("#getCommentaries").css("display", "none")
                    alert("Комментариев более нет!")
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
                post: commentariesButton.attr('data-postId')
            },
            success: (resultString) => {
                drawComments(JSON.parse(resultString), true)
                startOffset += 1
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

    commentariesButton.on("click", () => fetchCommentaries())
    $("#postCommentary").on("click", (event) => {
        postCommentary($("#bodyInput").get(0).innerText)
        $("#bodyInput").get(0).innerText = "Комментарий"
        edited = false
    })
})