document.getElementById("clickToUpload").onclick = (event) => {
    document.getElementById("inputFileUploader").click()
    document.getElementById("inputFileUploader").oninput = (eventInput) => {
        const filePath = document.getElementById("inputFileUploader").value.split("\\")
        document.getElementById("clickToUpload").value = filePath[filePath.length - 1]
    }
}