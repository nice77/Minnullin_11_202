<#macro settingsUser>
    <input type="text" placeholder="Изменить имя" name="name" value="${user.getName()}">
    <input type="text" placeholder="Изменить фамилию" name="surname" value="${user.getSurname()}">
    <input type="text" placeholder="Изменить отчество" name="patronymic" value="${user.getPatronymic()}">
    <input type="text" placeholder="Изменить телефон" name="phoneNumber " value="${user.getPhoneNumber()}">
    <input type="text" placeholder="Изменить email" name="email" value="${user.getEmail()}">
    <input type="text" placeholder="Изменить пароль" name="hashedPassword" value="${user.getHashedPassword()}">
    <input type="text" placeholder="Изменить описание" name="about" value="${user.getAbout()}">
</#macro>