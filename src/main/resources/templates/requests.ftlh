<#import "parts/common.ftlh" as c>
<#import "parts/table.ftlh" as t>

<@c.page>
    <br>
    <form method="get" action="http://localhost:8080" name="to_main">
        <button>Главная</button>
    </form>
    <br><br>
    <div>
        <fieldset>
            <legend>Добавить новый запрос:</legend>
            <form name="add_request" method="get" action="/requests">
                <@t.formInput id="t1" name = "stafferId" label = "ID сотрудника"/><br/>
                <@t.formInput id="t2" name = "shopName" label = "Название магазина"/><br/>
                <@t.formInput id="t3" name = "dateOfRequest" label = "Дата запроса"/><br/>
                <@t.formInput id="t4" name = "dateOfWork" label = "Дата работы"/><br/>
                <@t.formInput id="t5" name = "startTime" label = "Время начала"/><br/>
                <@t.formInput id="t6" name = "endTime" label = "Время окончания"/><br/>
                <@t.formInput id="t7" name = "confirmation" label = "Подтверждение"/><br/>
                <input type="submit" value="Добавить"/>
            </form>
        </fieldset>
    </div>
    <br><br>

    <div>
        <fieldset>
            <legend>Фильтр списка:</legend>
            <form name="filter_request" method="get" action="/requests">
                <@t.formInput id = "t8" name = "filter" label = "Фильтр"/><br/>
                <input type="submit" value="Найти"/>
            </form>
        </fieldset>
    </div>
    <br><br>
    <@t.table id = "t9" rows = requests![]/>

</@c.page>