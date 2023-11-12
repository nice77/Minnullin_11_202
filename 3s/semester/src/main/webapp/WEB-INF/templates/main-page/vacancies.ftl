<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Vacancies</title>

    <link rel="stylesheet" href="./resources/css/style.css">
    <link rel="stylesheet" href="./resources/css/style-main-page.css">

    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.12.2/gsap.min.js" defer></script>
    <script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js" defer></script>
    <script src="./resources/js/script-main.js" defer></script>
    <#if currentCardObj??>
        <script src="./resources/js/main-page/script-vacancies-spec.js" defer></script>
    <#else>
        <script src="./resources/js/main-page/script-vacancies.js" defer></script>
    </#if>
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
<div class="main-page">
    <#include "menu-buttons.ftl">
    <@buttons/>
    <div class="main-page__element">
        <#if currentCardObj??>
            <#if userType == "company">
                <#include "page-types-and-cards/current-vacancy.ftl">
                <@currentVacancy/>
            <#elseif userType == "user">
                <#include "page-types-and-cards/current-post.ftl">
                <@currentPost/>
            </#if>
        <#else>
            <#include "page-types-and-cards/vacancies-list.ftl">
            <@vacanciesList/>
        </#if>
    </div>
</div>
</body>
</html>

<#macro vacancies>

</#macro>