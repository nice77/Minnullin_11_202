<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>FindCure</title>
    <link rel="icon" href="./resources/assets/svg/leaf.svg">
    <link rel="stylesheet" href="./resources/css/style.css">
    <link rel="stylesheet" href="./resources/css/style-profile.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.12.2/gsap.min.js" defer></script>
    <script src="./resources/js/main-page/script.js" defer></script>
</head>
<body>
<header>
    <h2>FindCure</h2>
</header>

<div class="main-page">
    <div class="main-page__menu">
        <input type="button" class="menu__button" value="Профиль" id="profile">
        <input type="button" class="menu__button" value="Записи" id="vacancies">
        <input type="button" class="menu__button" value="Настройки" id="settings">
    </div>
    <#if path == "/profile" || path == "/company">
        <#include "profile.ftl">
        <@profile/>
    <#elseif path == "/vacancies">
        <#include "vacancies.ftl">
        <@vacancies/>
    <#elseif path == "/settings">
        <#include "settings-main.ftl">
        <@settingsMain/>
    </#if>
</div>
</body>
</html>