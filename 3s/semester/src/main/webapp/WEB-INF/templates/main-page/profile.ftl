<#macro profile>
    <div class="main-page__element main-page__profile-container">
        <div class="profile-header">

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
        <div class="profile-content">
            <#if userType == "user">
                <#assign fieldName="Новая запись дневника">
            <#else>
                <#assign fieldName="Анонс нового испытания">
            </#if>
            <input type="button" class="menu__button profile-button" value="${fieldName}" id="createNewBtn">
        </div>
        <#list posts as post>
            ${post}
        <#else>
            <h3>No posts</h3>
        </#list>
<#--        Добавить список всех записей дневника / вакансий -->
    </div>
</#macro>