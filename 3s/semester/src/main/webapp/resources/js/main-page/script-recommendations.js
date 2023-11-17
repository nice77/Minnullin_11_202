$(document).ready(() => {
    let options = {
        root: null,
        rootMargin: "0px",
        threshold: 1.0,
    };
    let callback = function (entries, observer) {
        setTimeout(() => fetch(), 500)
    };

    let observer = new IntersectionObserver(callback, options)
    observer.observe(document.querySelector("#bottom-crosser"))

    const userList = $(".users-list");
    let offset = 0

    const fetch = () => {
        if ($("#usersRecommendation").is(":checked")) {
            fetchUsers()
        }
        else {
            fetchVacancies()
        }
    }

    const fetchUsers = () => {
        $.ajax({
            url: "./followsConnection",
            method: "GET",
            data: {
                offset: offset,
                ignoreUserType: true
            },
            success: (resultString) => {
                offset += 10
                drawUsers(JSON.parse(resultString))
            }
        })
    }

    const drawUsers = (users) => {
        const userList = $(".recommends-list")
        if (users.length === 0) {
            const toAdd = $("<h3>", {
                text: "Подписывайтесь на пользователей, чтобы находить новых пользователей!"
            })
            $(".recommends-list").append(toAdd)
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

    const fetchVacancies = () => {
        $.ajax({
            url: "./vacanciesConnection",
            method: "GET",
            data: {
                offset: offset
            },
            success: (resultString) => {
                offset += 10
                drawVacancies(JSON.parse(resultString))
            }
        })
    }

    const drawVacancies = (vacancies) => {
        if (vacancies.length === 0) {
            const toAdd = $("<h3>", {
                text: "Подписывайтесь на пользователей, чтобы получать рекомендации!"
            })
            $(".recommends-list").append(toAdd)
        }
        else {
            vacancies.forEach((vacancy) => {
                const toAdd = $('<div>', {
                    class: 'card',
                    id: 'card-' + vacancy.id
                }).append(
                    $('<div>', {
                        class: 'clickable',
                        id: vacancy.id
                    }).click(() => fade("./vacancies?vid=" + vacancy.id)).append(
                        $('<h3>', {
                            text: vacancy['header']
                        })
                    ).append(
                        $('<h4>', {
                            text: vacancy['body']
                        })
                    )
                )
                $(".recommends-list").append(toAdd)
            })
        }
    }

    $("#vacanciesRecommendation").on("click", () => {
        offset = 0
        $(".recommends-list").get(0).innerHTML = ""
        fetchVacancies()
    })

    $("#usersRecommendation").on("click", () => {
        offset = 0
        $(".recommends-list").get(0).innerHTML = ""
        fetchUsers()
    })
})