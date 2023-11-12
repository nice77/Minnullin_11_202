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
    <span type="text" id="bodyInput" class="textarea" role="textbox" contenteditable>Комментарий</span>
    <input type="button" value="Отправить" id="postCommentary">
    <div class="comments-list"></div>
<#--    <input type="button" value="Get commentaries" id="getCommentaries" data-postId="${postObj.getId()}">-->
    <div id="bottom-crosser" data-postId="${postObj.getId()}"></div>
</#macro>