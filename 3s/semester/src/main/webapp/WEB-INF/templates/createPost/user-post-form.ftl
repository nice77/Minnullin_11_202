<#macro userPostForm>
    <#if post??>
        <input type="text" class="textarea" value="${post.getHeader()}" role="textbox" name="header">
        <input type="text" id="hiddenInputField" name="body" value="${post.getBody()}">
        <span id="bodyInput" class="textarea" role="textbox" contenteditable>${post.getBody()}</span>
    <#else>
        <input type="text" class="textarea" placeholder="Заголовок" role="textbox" name="header">
        <input type="text" id="hiddenInputField" name="body">
        <span id="bodyInput" class="textarea" role="textbox" contenteditable>Текст</span>
    </#if>
</#macro>