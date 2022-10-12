<#import "parts/common.ftlh" as c>
<#import "parts/table.ftlh" as t>

<@c.page>

<#--Кнопка на главную -->
    <br>
    <form method="get" action="http://localhost:8080" name="to_main">
        <button>Главная</button>
    </form>
    <br><br>
<#--Форма добавления нового запроса -->
    <div>
        <fieldset>
            <legend>Добавить новый запрос:</legend>
            <form name="add_request" method="get" action="/requests">
                <@t.formInput id="f1" name = "stafferId" label = "ID сотрудника"/><br/>
                <@t.formInput id="f2" name = "shopName" label = "Название магазина"/><br/>
                <@t.formInput id="f3" name = "dateOfRequest" label = "Дата запроса"/><br/>
                <@t.formInput id="f4" name = "dateOfWork" label = "Дата работы"/><br/>
                <@t.formInput id="f5" name = "startTime" label = "Время начала"/><br/>
                <@t.formInput id="f6" name = "endTime" label = "Время окончания"/><br/>
                <@t.formInput id="f7" name = "confirmation" label = "Подтверждение"/><br/>
                <input type="submit" value="Добавить"/>
            </form>
        </fieldset>
    </div>
    <br><br>
<#--Фильтрация списка запросов-->
    <div>
        <fieldset>
            <legend>Фильтр списка:</legend>
            <form name="filter_request" method="get" action="/requests">
                <@t.formInput id = "f8" name = "filter" label = "Фильтр"/><br/>
                <input type="submit" value="Найти"/>
            </form>
        </fieldset>
    </div>
    <br><br>
<#--Отфильтрованный список заявок. Отображение всего списка без введенного значения -->
    <table border="1px" cellspacing="2" border="1" cellpadding="5">
        <th>№</th>
        <th>ID сотрудника</th>
        <th>Название ММ</th>
        <th>Дата запроса</th>
        <th>Дата работы</th>
        <th>Время начала</th>
        <th>Время окончания</th>
        <th>Подтверждение</th>
        <#list filterRequests![] as request>
            <tr>
                <td>${request?index + 1}</td>
                <td>${request.stafferId}</td>
                <td>${request.shopName}</td>
                <td>${request.dateOfRequest}</td>
                <td>${request.dateOfWork}</td>
                <td>${request.startTime}</td>
                <td>${request.endTime}</td>
                <td>${request.confirmation}</td>
            </tr>
        <#else>
            Ничего не найдено
        </#list>
    </table>

</@c.page>