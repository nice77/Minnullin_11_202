<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Nothing!</title>
    <link rel="stylesheet" href="./style.css">
</head>
<body>
<div class="main">
    <#if user>
        <#include "vote_done.ftl">
        <@vote_done/>
    <#else>
        <#include "vote_undone.ftl">
        <@vote_undone/>
    </#if>
</div>
</body>
</html>