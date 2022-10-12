<#import "parts/common.ftlh" as c>

<@c.page>
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
</@c.page>