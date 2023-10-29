<#macro vacancies>
    <div class="main-page__element">
    <#if currentCardObj??>
        <#if userType == "company">
            <#include "page-types-and-cards/current-vacancy.ftl">
            <@currentVacancy/>
        <#elseif userType == "user">
            <#include "page-types-and-cards/current-post.ftl">
            <@currentPost/>
        </#if>
    <#else>
        <#include "page-types-and-cards/vacancies-list.ftl">
        <@vacanciesList/>
    </#if>
</#macro>