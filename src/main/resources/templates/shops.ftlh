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
            <legend>Добавить новый магазин:</legend>
            <form name="add_shop" method="get" action="/shops">
                <@t.formInput id="t1" name = "name" label = "Название"/><br/>
                <@t.formInput id="t2" name = "code" label = "Код магазина"/><br/>
                <input type="submit" value="Добавить"/>
            </form>
        </fieldset>
    </div>
    <br><br>

    <div>
        <fieldset>
            <legend>Фильтр списка:</legend>
            <form name="filter_shop" method="get" action="/shops">
                <@t.formInput id = "t3" name = "filter" label = "Фильтр"/><br/>
                <input type="submit" value="Найти"/>
            </form>
        </fieldset>
    </div>
    <br><br>
    <@t.table id = "t4" rows = shops![]/>

</@c.page>