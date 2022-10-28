<#import "../parts/common.ftlh" as c>
<#import "../parts/table.ftlh" as t>


<@c.page>

<#--кнопка выхода из профиля -->
    <form method="get" action="auth/logout" name="to_logout" style="position: absolute; top:5px; right: 5px">
        <button>Выйти из профиля</button>
    </form>
    <br>

<#--Кнопка на главную -->
    <br>
    <form method="get" action="http://localhost:8080/main" name="to_main">
        <button>Главная</button>
    </form>
    <br><br>



</@c.page>