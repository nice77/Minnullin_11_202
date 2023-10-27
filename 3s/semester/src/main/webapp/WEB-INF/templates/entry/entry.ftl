<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>FindCure Entry</title>
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