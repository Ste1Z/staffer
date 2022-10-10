<#import "parts/common.ftlh" as c>

<@c.page>
    <br>
    <form method="get" action="/shops" name="to_shops">
        <button>Магазины</button>
    </form>
    <br>
    <form method="get" action="/staffers" name="to_staffers">
        <button>Работники</button>
    </form>
    <br>
    <form method="get" action="/requests" name="to_requests">
        <button>Заявки</button>
    </form>
</@c.page>