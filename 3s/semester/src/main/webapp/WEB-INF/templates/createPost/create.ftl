<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="./resources/css/style.css">
    <link rel="stylesheet" href="./resources/css/style-main-page.css">
    <link rel="stylesheet" href="./resources/css/style-create.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.12.2/gsap.min.js" defer></script>
    <script src="./resources/js/jquery.js" defer></script>
    <script src="./resources/js/script-main.js" defer></script>
    <script src="./resources/js/createPost/script.js" defer></script>
    <title>Create post</title>
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
    <#if modified>
        <#assign action="./create?postId=" + post.getId()>
    <#else>
        <#assign action="./create">
    </#if>
    <form action="" class="main-page__element main-page__settings-form" method="post" enctype="multipart/form-data">
        <#if modified>
            <h2>Изменить запись</h2>
        <#else>
            <h2>Добавить запись</h2>
        </#if>
        <#if userType == "user">
            <#include "user-post-form.ftl">
            <@userPostForm/>
        <#elseif userType == "company">
            <#include "company-post-form.ftl">
            <@companyPostForm/>
        </#if>
        <input type="submit" value="Сохранить изменения" class="submit-button">
    </form>
</div>
</body>
</html>