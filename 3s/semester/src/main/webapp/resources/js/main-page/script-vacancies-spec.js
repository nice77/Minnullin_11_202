const fetchCommentaries = () => {
    $.ajax({
        url: "./commentsConnection",
        method: "GET",
        data: {
            data: commentariesButton.attr('data-postId')
        },
        success: (resultString) => {
            console.log(resultString)
        }
    })
}

const commentariesButton = $('#getCommentaries')
console.log(commentariesButton)

$(document).on('click', commentariesButton,() => {
    fetchCommentaries()
});