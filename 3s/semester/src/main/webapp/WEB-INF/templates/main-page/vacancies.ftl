<#macro vacancies>
    <div class="main-page__element vacancies">
        <h3>Доступные записи</h3>
        <#list vacanciesList as vacancy>
            ${vacancy}
        <#else>
            <h3>Доступных записей нет!</h3>
        </#list>
    </div>
</#macro>