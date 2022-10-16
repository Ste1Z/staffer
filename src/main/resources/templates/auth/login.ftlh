<#import "../parts/common.ftlh" as c>

<@c.page>

<#--Форма ввода логина и пароля-->
    <form name="login_form" method="post" action="/process_login">
<#--        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
        <label for="username">Введите имя пользователя:</label>
        <input type="text" name="username" id="username"/>
        <br/>
        <label for="password">Введите пароль:</label>
        <input type="password" name="password" id="password"/>
        <br/>
        <input type="submit" value="Вход">
    </form>
    <br>

<#--Кнопка регистрации-->
    <form method="get" action="http://localhost:8080/auth/registration" name="to_registration">
        <button>Регистрация</button>
    </form>

</@c.page>