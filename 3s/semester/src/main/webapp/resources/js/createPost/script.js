document.getElementById("clickToUpload").onclick = (event) => {
    document.getElementById("inputFileUploader").click()
}

let edited = false
document.getElementById("bodyInput").oninput = (event) => {
    if (!edited) {
        edited = true
        const elem = document.getElementById("bodyInput")
        elem.innerText = event.target.innerText.substring(event.target.innerText.length - 1)

        if(document.createRange)
        {
            range = document.createRange()
            range.selectNodeContents(elem)
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

    const bodyInputField = document.getElementById("hiddenInputField")
    bodyInputField.value = document.getElementById("bodyInput").innerText
}