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
    <script src="./resources/js/main-page/script-news.js" defer></script>

    <title>News</title>
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
        <div class="news-list"></div>
        <div id="bottom-crosser"></div>
    </div>
</div>
</body>
</html>