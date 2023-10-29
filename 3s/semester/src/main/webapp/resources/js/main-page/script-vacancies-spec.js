document.getElementById("#getCommentaries").onclick = () => {
    $ajax({
        url: "/commentsConnection"
    })
}