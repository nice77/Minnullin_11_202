<#macro buttons>
    <div class="main-page__menu">
        <input type="button" class="menu__button" value="Профиль" id="profile">
        <input type="button" class="menu__button" value="Записи" id="vacancies">
        <#if userType == "user">
            <input type="button" class="menu__button" value="Подписки" id="follows">
        </#if>
        <input type="button" class="menu__button" value="Настройки" id="settings">
    </div>
</#macro>