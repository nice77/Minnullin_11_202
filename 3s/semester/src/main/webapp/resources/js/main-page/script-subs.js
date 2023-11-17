$(document).ready(() => {
    let offset = 0

    let options = {
        root: null,
        rootMargin: "0px",
        threshold: 1.0,
    };
    let callback = function (entries, observer) {
        let userRequestType = $('input[name="usersType"]:checked').id === "followers" ? "followers" : "authors";
        setTimeout(() => fetchUsers(userRequestType), 500)
    };

    let observer = new IntersectionObserver(callback, options)
    observer.observe(document.querySelector("#bottom-crosser"))

    const fetchUsers = (usersRequestType, name) => {
        $.ajax({
            method: "GET",
            url: "./followsConnection",
            data: {
                usersRequestType: usersRequestType,
                username: name,
                offset: offset
            },
            success: (resultString) => {
                if (usersRequestType === "unsub") {
                    drawSearchedUsers(JSON.parse(resultString))
                }
                else {
                    offset += 10
                    drawUsers(JSON.parse(resultString))
                }
            }
        })
    }

    const drawSearchedUsers = (users) => {
        const userList = $("#userSearchList")
        userList.get(0).innerHTML = ''

        users.forEach(item => {
            const toAdd = $("<li>", {
                text: item["name"] + " " + item["surname"],
                class: "user-item"
            })
            toAdd.on("click", () => {
                window.location.href = "./profile?userId=" + item["id"]
            })
            userList.append(toAdd)
        })
    }

    const drawUsers = (users) => {
        const userList = $(".subs-list")
        userList.get(0).innerHTML = ''
        if (users.length === 0) {
            userList.append($("<h3>", {
                text: "Никого нет!"
            }))
            return;
        }

        users.forEach(item => {
            const toAdd = $("<div>", {
                class: "content-header",
            }).append($("<img>", {
                class: "profile-picture",
                src: item["avatar"] === "default.png" ? "./resources/assets/images/default.png" : "./profileImages/user/" + item["avatar"]
            })).append($("<h2>", {
                text: item["name"] + " " + item["surname"]
            }))

            toAdd.on("click", () => {
                window.location.href = "./profile?userId=" + item["id"]
            })

            userList.append(toAdd)
        })
    }


    $("#userSearch").on("input", (event) => {
        fetchUsers("unsub", event.target.value)
    })

    $("#authors").on("click", (event) => {
        offset = 0
        fetchUsers("authors")
    })

    $("#followers").on("click", (event) => {
        offset = 0
        fetchUsers("followers")
    })
})