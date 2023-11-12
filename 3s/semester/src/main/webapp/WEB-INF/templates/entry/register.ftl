<#macro register>
    <form method="POST" class="form">
        <h3>Вход FindCure</h3>
        <div class="container">
            <div class="input-column">
                <input type="text" class="form__input" placeholder="email" name="email">
                <input type="password" class="form__input" placeholder="password" name="password">
            </div>
            <div class="input-column">
                <input type="text" class="form__input" placeholder="name" name="name">
                <input type="number" class="form__input" placeholder="phone" name="phone">
            </div>
        </div>
        <input class="checkbox" type="checkbox" id="company" name="company">
        <label for="company">Компания</label>
        <div class="input-row">
            <input type="submit" class="form__button register" value="Зарегистрироваться" id="submit-btn" disabled>
            <input type="button" class="form__button" value="Уже есть аккаунт?" id="redirect-btn-register">
        </div>
    </form>
</#macro>