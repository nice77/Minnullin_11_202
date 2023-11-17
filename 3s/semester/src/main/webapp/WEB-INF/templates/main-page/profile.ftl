<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link rel="stylesheet" href="./resources/css/style.css">
    <link rel="stylesheet" href="./resources/css/style-main-page.css">
    <link rel="icon" href="./resources/assets/svg/leaf.svg">

    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.12.2/gsap.min.js" defer></script>
    <script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js" defer></script>
    <script src="./resources/js/script-main.js" defer></script>
    <script src="./resources/js/main-page/script-profile-user.js" defer></script>
    <script src="./resources/js/main-page/script-profile-company.js" defer></script>
    <title>Profile</title>
</head>
<body>
<header>
    <div id="expand_button">
        <span></span>
        <span></span>
        <span></span>
    </div>
    <h2>FindCure</h2>
</header>
<dialog id="hidden">
    <div class="dialog-content">
        <#if user.getAbout()?length == 0>
            <h5>Ничего нет!</h5>
        <#else>
            <h5>${user.getAbout()}</h5>
        </#if>
        <input type="button" id="closeDialog" value="close">
    </div>
</dialog>
<div class="main-page">
    <#include "menu-buttons.ftl">
    <@buttons/>

    <div class="main-page__element">
        <div class="content-header">

            <#if user.getAvatar() == "default.png">
                <img class="profile-picture" src="./resources/assets/images/default.png" alt="No avatar">
            <#else>
                <img class="profile-picture" src="./profileImages/${userType}/${user.getAvatar()}" alt="No avatar">
            </#if>

            <div class="name-field">
                <#if userType == "user">
                    <h2>${user.getName() + " " + user.getSurname()}</h2>
                <#elseif userType == "company">
                    <h2>Company: ${user.getName()}</h2>
                </#if>
                <h5 id="expandAbout">Show more</h5>
            </div>

        </div>
        <div class="content-container">
            <#if currentUser>
                <#if userType == "user">
                    <#assign fieldName="Новая запись дневника">
                <#else>
                    <#assign fieldName="Анонс нового испытания">
                </#if>
                <input type="button" class="menu__button profile-button" value="${fieldName}" id="createNewBtn">
            <#else>
                <#if userType == "user">
                    <#if isSubscribed>
                        <#assign fieldName="Отписаться">
                    <#else>
                        <#assign fieldName="Подписаться">
                    </#if>
                    <input type="button" class="menu__button profile-button" value="${fieldName}" id="subscribeBtn" data-user-id="${user.getId()}" data-is-subscribed="${isSubscribed?c}">
                </#if>
            </#if>
        </div>

        <#if userType == "user">
            <#include "./profile-list-types/diary-list-type.ftl">
            <@diaryListType/>
        <#elseif userType == "company">
            <#include "./profile-list-types/vacancy-list-type.ftl">
            <@vacancyListType/>
        </#if>
    </div>

</div>
</body>
</html>
