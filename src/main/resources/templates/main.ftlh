<#import "parts/common.ftlh" as c>

<@c.page>

<#--кнопка профиля -->
    <form method="get" action="/my_profile" name="to_my_profile" style="position: absolute; top:5px; right: 5px">
        <button>Мой профиль</button>
    </form>

<#--кнопка выхода из профиля -->
    <form method="post" action="auth/logout" name="to_logout" style="position: absolute; top:40px; right: 5px">
        <button>Выйти из профиля</button>
    </form>

<#--кнопка на страницу поиска заявок -->
    <br>
    <form method="get" action="/requests" name="to_requests">
        <button>Поиск заявок</button>
    </form>
    <br>

<#--кнопка на страницу заявок пользователя -->
    <br>
    <form method="get" action="/requests/add_request" name="to_add_request">
        <button>Мои заявки WIP</button>
    </form>
    <br>

</@c.page>