<#macro vacancyListType>
    <h3>Активные заявки</h3>
    <div class="content-container">
        <#list posts as post>
            <div class="card">
                <div class="clickable vacancy" id="${post.getId()}">
                    <h3>${post.getHeader()}</h3>
                    <h4>${post.getBody()}</h4>
                </div>
            </div>
        <#else>
            <h3>No posts</h3>
        </#list>
    </div>
</#macro>