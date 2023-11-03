<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Settings</title>

    <link rel="icon" href="./resources/assets/svg/leaf.svg">

    <link rel="stylesheet" href="./resources/css/style.css">
    <link rel="stylesheet" href="./resources/css/style-main-page.css">

    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.12.2/gsap.min.js" defer></script>
    <script src="./resources/js/script-main.js" defer></script>
    <script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js" defer></script>
    <script src="./resources/js/main-page/script-settings.js" defer></script>

</head>
<body>
<header>
    <h2>FindCure</h2>
</header>
<div class="main-page">
    <#include "menu-buttons.ftl">
    <@buttons/>
    <form class="main-page__element main-page__settings-form" method="post" enctype="multipart/form-data">
        <h2>Изменить профиль</h2>
        <#if userType == "user">
            <#include "settings-user.ftl">
            <@settingsUser/>
        <#elseif userType == "company">
            <#include "settings-company.ftl">
            <@settingsCompany/>
        </#if>
        <input type="file" id="inputFileUploader" name="avatar">
        <input type="button" id="clickToUpload" value="Изменить аватарку">
        <input type="submit" value="Сохранить изменения" class="submit-button">
    </form>
</div>
</body>
</html>




<#--<#macro settingsMain>-->
<#--    -->
<#--</#macro>-->