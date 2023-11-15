gsap.utils.toArray(".card").forEach(item => {
    if (item.querySelector(".vacancy") !== null) {
        const element = item.querySelector(".vacancy")
        element.onclick = (event) => {
            fade("./vacancies?vid=" + element.id)
        }
        $("#btn").on("click", (event) => {
            removeAjax({
                id: $("#btn").attr("data-id")
            })
        })
    }
})

const checkbox = document.getElementById("checkbox")

checkbox.onchange = (event) => {
    if (checkbox.checked) {
        fetch()
    }
    else {
        removeAll()
    }
}

const removeAll = () => {
    $('#all-vacancies > *').remove()
}

const fetch = () => {
    $.ajax({
        url: "./vacanciesConnection",
        method: "GET",
        data: {
            getAll: true
        },
        success: (result) => {
            const vacancies = JSON.parse(result)
            vacancies.forEach((vacancy) => {
                drawVacancies(vacancy)
            })
        }
    })
}

const drawVacancies = (vacancy) => {
    const userType = $("#vacancies-list").attr("data-userType")
    const toAdd = $('<div>', {
        class: 'card',
        id: 'card-' + vacancy.id
    }).append(
        $('<div>', {
            class: 'clickable',
            id: vacancy.id
            // поправить параметры ссылки
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
    if (userType !== "company") {
        toAdd.append(
            $('<input>', {
                type: 'button',
                value: 'Подписаться',
                id: 'btn-' + vacancy.id
            }).click(() => {
                inputAjax(vacancy)
            })
        )
    }
    toAdd.appendTo("#all-vacancies")
}

const inputAjax = (vacancy) => {
    $.ajax({
        url: "./subsConnection",
        method: "POST",
        data: {
            vacancyId: vacancy.id
        },
        success: () => {
            $('#unsubError').remove()
            $('#card-' + vacancy.id).remove()
            $('<div>', {
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
            ).append(
                $('<input>', {
                    type: 'button',
                    value: 'Отписаться',
                    id: 'btn-' + vacancy.id
                }).click(() => {
                    console.log(4)
                    removeAjax(vacancy)
                })
            ).appendTo("#subbed-vacancies")
        }
    })
}

const removeAjax = (vacancy) => {
    $.ajax({
        url: "./subsConnection",
        method: "POST",
        data: {
            vacancyId: vacancy.id,
            toRemove: true
        },
        success: () => {
            $('#subbed-vacancies > #card-' + vacancy.id).remove()
            $('#all-vacancies > *').remove()
            if (checkbox.checked) {
                fetch()
            }
        }
    })
}