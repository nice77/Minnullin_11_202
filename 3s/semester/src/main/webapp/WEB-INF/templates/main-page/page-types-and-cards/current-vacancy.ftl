<#macro currentVacancy>
    <#assign vacancyObj = currentCardObj[0]>
    <div class="content-container card">
        <h2>
            ${vacancyObj.getHeader()}
        </h2>
        <h4>
            О записи: ${vacancyObj.getBody()}<br><br>
            Место проведения: ${vacancyObj.getMeetingPlace()}<br>
            Дата: ${vacancyObj.getMeetingDate()}<br>
            Компания: <a class="link" href="./company?companyId=${currentCardObj[1].getId()}">${currentCardObj[1].getName()}</a>
        </h4>
    </div>
</#macro>