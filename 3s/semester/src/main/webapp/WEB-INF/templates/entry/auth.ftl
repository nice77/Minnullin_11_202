<#macro auth>
    <form method="POST" class="form">
        <h3>Вход FindCure</h3>
        <input type="text" class="form__input" placeholder="email" name="email">
        <input type="password" class="form__input" placeholder="password" name="password">

        <input class="checkbox" type="checkbox" id="company" name="company">
        <label for="company">Компания</label>

        <input class="checkbox" type="checkbox" id="remember-me" name="remember-me">
        <label for="remember-me">Запомнить меня</label>

        <input type="submit" class="form__button" value="Войти">
        <input type="button" class="form__button register" value="Зарегистрироваться" id="redirect-btn">
    </form>
</#macro>