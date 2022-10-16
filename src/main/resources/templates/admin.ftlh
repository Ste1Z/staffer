<#import "parts/common.ftlh" as c>

<@c.page>

<#--кнопка выхода из профиля -->
    <form method="post" action="auth/logout" name="to_logout" style="position: absolute; top:5px; right: 5px">
        <button>Выйти из профиля</button>
    </form>
    <br>

<#--кнопка на страницу магазинов -->
    <br>
    <form method="get" action="/shops" name="to_shops">
        <button>Магазины</button>
    </form>
    <br>

<#--кнопка на страницу работников -->
    <form method="get" action="/staffers" name="to_staffers">
        <button>Работники</button>
    </form>
    <br>

<#--кнопка на страницу заявок -->
    <form method="get" action="/requests" name="to_requests">
        <button>Заявки</button>
    </form>
    <br>

<#--кнопка входа в профиль -->
    <form method="get" action="/auth/login" name="to_login">
        <button>Войти в профиль</button>
    </form>
    <br>



<#--кнопка на страницу регистрации -->
    <form method="get" action="/auth/registration" name="to_registration">
        <button>Регистрация</button>
    </form>
    <br>



</@c.page>