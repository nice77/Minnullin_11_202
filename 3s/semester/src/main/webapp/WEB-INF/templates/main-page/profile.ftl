<#macro profile>
    <div class="main-page__element">
        <div class="content-header">

            <#if user.getAvatar() == "default.png">
                <img class="profile-picture" src="./resources/assets/images/default.png" alt="No avatar">
            <#else>
                <img class="profile-picture" src="./profileImages/${userType}/${user.getAvatar()}" alt="No avatar">
            </#if>

            <#if userType == "user">
                <h2>${user.getName() + " " + user.getSurname()}</h2>
            <#elseif userType == "company">
                <h2>Company: ${user.getName()}</h2>
            </#if>

        </div>
        <#if currentUser>
            <div class="content-container">
                <#if userType == "user">
                    <#assign fieldName="Новая запись дневника">
                <#else>
                    <#assign fieldName="Анонс нового испытания">
                </#if>
                <input type="button" class="menu__button profile-button" value="${fieldName}" id="createNewBtn">
            </div>
        </#if>

        <#if userType == "user">
            <#include "./profile-list-types/diary-list-type.ftl">
            <@diaryListType/>
        <#elseif userType == "company">
            <#include "./profile-list-types/vacancy-list-type.ftl">
            <@vacancyListType/>
        </#if>
    </div>
</#macro>