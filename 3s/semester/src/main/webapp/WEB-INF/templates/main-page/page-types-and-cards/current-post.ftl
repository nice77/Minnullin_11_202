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
    <#if currentUserId == postObj.getUserId() && userType == "user">
        <input type="button" value="Изменить пост" id="editPost" data-post-id="${postObj.getId()}">
        <input type="button" value="Удалить пост" id="removePostBtn" data-post-id="${postObj.getId()}">
    </#if>
    <#if userType == "user">
        <span type="text" id="bodyInput" class="textarea" role="textbox" contenteditable>Комментарий</span>
        <input type="button" value="Отправить" id="postCommentary">
    </#if>
    <h3>Комментарии</h3>
    <div class="comments-list"></div>
    <div id="bottom-crosser" data-postId="${postObj.getId()}"></div>
</#macro>