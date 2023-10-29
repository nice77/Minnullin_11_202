<#macro diaryListType>
    <div class="content-container">
        <#list posts as post>
            <div class="card">
                <div class="clickable post" id="${post.getId()}">
                    <h3>${post.getHeader()}</h3>
                    <#if post.getBody()?length != 0>
                        <h4>${post.getBody()}</h4>
                    </#if>
                </div>
            </div>
        <#else>
            <h3>No posts</h3>
        </#list>
    </div>
</#macro>