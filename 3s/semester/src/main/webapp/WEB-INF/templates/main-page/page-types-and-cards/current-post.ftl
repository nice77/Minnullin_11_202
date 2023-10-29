<#macro currentPost>
    <#assign postObj = currentCardObj[0]>
    <div class="content-container card">
        <h2>
            ${postObj.getHeader()}
        </h2>
        <h4>
            <#if postObj.getBody()?length != 0>
                ${postObj.getBody()}<br><br>
            </#if>
            Автор: <a class="link" href="./profile?userId=${currentCardObj[1].getId()}">${currentCardObj[1].getName()}</a>
        </h4>
    </div>
    <input type="button" value="Get commentaries" id="getCommentaries">
    <div class="comments-list"></div>
</#macro>