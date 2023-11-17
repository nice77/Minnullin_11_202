$(document).ready(() => {
    let options = {
        root: null,
        rootMargin: "0px",
        threshold: 1.0,
    };
    let callback = function (entries, observer) {
        setTimeout(() => fetchUsers(), 500)
    };

    let observer = new IntersectionObserver(callback, options)
    observer.observe(document.querySelector("#bottom-crosser"))

    const userList = $(".users-list");
    let offset = 0

    const fetchUsers = () => {
        $.ajax({
            url: "./subsConnection",
            method: "GET",
            data: {
                offset: offset
            },
            success: (resultString) => {
                offset += 10
                console.log(JSON.parse(resultString))
                drawUsers(JSON.parse(resultString))
            }
        })
    }

    const drawUsers = (users) => {
        users.forEach((user) => {
            const toAdd = $("<div>", {
                class: "content-header"
            }).append($("<img>", {
                class: "profile-picture",
                src: user["avatar"] === "default.png" ? "./resources/assets/images/default.png" : "./profileImages/user/" + user["avatar"]
            })).append($("<h2>", {
                text: user["name"] + " " + user["surname"] + " " + user["patronymic"]
            }))

            toAdd.on("click", () => {
                window.location.href = "./profile?userId=" + user["id"]
            })

            userList.append(toAdd)
        })
    }
})