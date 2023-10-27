<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="./resources/css/style.css">
    <link rel="stylesheet" href="./resources/css/style-create.css">
    <title>Create</title>
</head>
<body>
<div class="main-page">

</div>
<#if userType == "user">
    user
<#elseif userType == "company">
    company
</#if>
</body>
</html>