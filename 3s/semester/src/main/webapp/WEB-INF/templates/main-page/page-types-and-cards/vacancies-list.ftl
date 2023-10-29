<#macro vacanciesList>
    <div class="content-header">
        <div class="header-one">
            <h3>Доступные записи</h3>
            <input type="checkbox" id="checkbox">
            <label for="checkbox">Отобразить все</label>
        </div>
    </div>
    <div class="content-container" id="vacancies-list">
        <div id="subbed-vacancies">
            <#list vacanciesMapList as vacancy>
                <div class="card" id="card-${vacancy["id"]}">
                    <div class="clickable vacancy" id="${vacancy["id"]}">
                        <h3>${vacancy["header"]}</h3>
                        <h4>${vacancy["body"]}</h4>
                    </div>
                    <input type="button" value="Отписаться" id="btn-${vacancy["id"]}">
                </div>
            <#else>
                <h3 id="unsubError">Вы ни на что не подписаны!</h3>
            </#list>
        </div>
        <div id="all-vacancies">

        </div>
    </div>
</#macro>