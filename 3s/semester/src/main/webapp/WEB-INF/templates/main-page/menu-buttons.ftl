<#macro buttons>
    <div class="main-page__menu">
        <input type="button" class="menu__button" value="Профиль" id="profile">
        <input type="button" class="menu__button" value="Записи" id="vacancies">
        <#if userType == "user">
            <input type="button" class="menu__button" value="Подписки" id="follows">
            <input type="button" class="menu__button" value="Новости" id="news">
            <input type="button" class="menu__button" value="Рекомендации" id="recommendations">
        </#if>
        <#if userType == "company">
            <input type="button" class="menu__button" value="Пользователи" id="users">
        </#if>
        <input type="button" class="menu__button" value="Настройки" id="settings">
        <input type="button" class="menu__button exit" value="Выйти" id="exit">
    </div>
</#macro>