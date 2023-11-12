<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>FindCure Entry</title>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <link rel="stylesheet" href="./resources/css/style.css">
    <link rel="stylesheet" href="./resources/css/style-entry.css">
    <link rel="icon" href="./resources/assets/svg/leaf.svg">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.12.2/gsap.min.js" defer></script>
    <script src="./resources/js/entry/script.js" defer></script>
</head>
<body>
<div class="main-page">
    <#if path == "/auth">
        <#include "auth.ftl">
        <@auth/>
    <#else>
        <#include "register.ftl">
        <@register/>
    </#if>
</div>
</body>
</html>