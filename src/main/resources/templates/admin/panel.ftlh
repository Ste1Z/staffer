<#import "../parts/common.ftlh" as c>

<@c.page>

<#--кнопка выхода из профиля -->
    <form method="post" action="auth/logout" name="to_logout" style="position: absolute; top:5px; right: 5px">
        <button>Выйти из профиля</button>
    </form>
    <br>

<#--кнопка на страницу магазинов -->
    <br>
    <form method="get" action="/admin/shops" name="to_shops">
        <button>Магазины</button>
    </form>
    <br>

<#--кнопка на страницу работников -->
    <form method="get" action="/admin/staffers" name="to_staffers">
        <button>Работники</button>
    </form>
    <br>

<#--кнопка на страницу заявок -->
    <form method="get" action="/admin/all_requests" name="to_requests">
        <button>Заявки</button>
    </form>
    <br>

<#--кнопка на страницу регистрации -->
    <form method="get" action="/auth/registration" name="to_registration">
        <button>Регистрация</button>
    </form>
    <br>



</@c.page>