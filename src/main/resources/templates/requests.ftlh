<#import "parts/common.ftlh" as c>
<#import "parts/table.ftlh" as t>


<@c.page>

<#--кнопка выхода из профиля -->
    <form method="get" action="auth/logout" name="to_logout" style="position: absolute; top:5px; right: 5px">
        <button>Выйти из профиля</button>
    </form>
    <br>

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
            <form name="add_request" method="post" action="/requests">
                <@t.formInput id="f1" name="shopName" label="Название магазина"/><br/>
                <@t.formInput id="f2" name="numberOfReqStaffers" label="Необходимое кол-во сотрудников"/><br/>
                <@t.formInput id="f3" name="numberOfReqStaffers" label="Должность сотрудника(ов)"/><br/>
                <@t.formInput id="f4" type="date" name="dateOfWork" label="Дата работы"/><br/>
                <@t.formInput id="f5" type="time" name="startTime" label="Время начала"/><br/>
                <@t.formInput id="f6" type="time" name="endTime" label="Время окончания"/><br/>
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
                <@t.formInput id = "f7" name = "filter" label = "Фильтр"/><br/>
                <input type="submit" value="Найти"/>
            </form>
        </fieldset>
    </div>
    <br><br>

<#--Отфильтрованный список заявок. Отображение всего списка без введенного значения -->
    <table border="1px" cellspacing="2" border="1" cellpadding="5">
        <th>№</th>
        <th>Название ММ</th>
        <th>Дата запроса</th>
        <th>Дата работы</th>
        <th>Время начала</th>
        <th>Время окончания</th>
        <#list filterRequests![] as request>
            <tr>
                <td>${request?index + 1}</td>
                <td>${request.getShopName()}</td>
                <td>${request.getDateOfRequest()}</td>
                <td>${request.getDateOfWork()}</td>
                <td>${request.getStartTime()}</td>
                <td>${request.getEndTime()}</td>
            </tr>
        <#else>
            Ничего не найдено
        </#list>
    </table>

</@c.page>