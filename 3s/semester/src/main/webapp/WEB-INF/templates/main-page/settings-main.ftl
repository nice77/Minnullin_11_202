<#macro settingsMain>
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
</#macro>