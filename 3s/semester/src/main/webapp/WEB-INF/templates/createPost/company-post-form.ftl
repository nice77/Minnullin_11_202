<#macro companyPostForm>
    <#include "user-post-form.ftl">
    <@userPostForm/>
    <input type="date" value="Дата встречи" name="meetingDate">
    <input type="text" placeholder="Место встречи" name="meetingPlace">
</#macro>