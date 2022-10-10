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
            <legend>Добавить нового сотрудника:</legend>
            <form name="add_staffer" method="get" action="/staffers">
                <@t.formInput id="t1" name = "name" label = "Имя"/><br/>
                <@t.formInput id="t2" name = "surname" label = "Фамилия"/><br/>
                <@t.formInput id="t3" name = "patronymic" label = "Отчество"/><br/>
                <@t.formInput id="t4" name = "dateOfBirth" label = "Дата рождения"/><br/>
                <@t.formInput id="t5" name = "homeShop" label = "Родной магазин"/><br/>
                <input type="submit" value="Добавить"/>
            </form>
        </fieldset>
    </div>
    <br><br>

    <div>
        <fieldset>
            <legend>Фильтр списка:</legend>
            <form name="filter_staffer" method="get" action="/staffers">
                <@t.formInput id = "t6" name = "filter" label = "Фильтр"/><br/>
                <input type="submit" value="Найти"/>
            </form>
        </fieldset>
    </div>
    <br><br>
    <@t.table id = "t7" rows = staffers![]/>

</@c.page>