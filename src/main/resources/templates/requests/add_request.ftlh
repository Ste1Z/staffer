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

<#--Форма добавления нового запроса -->
<#--TODO перенести форму в админку-->
    <div>
        <fieldset>
            <legend>Добавить новый запрос:</legend>
            <form name="add_request" method="post" action="/requests/add_request">
                <@t.formInput id="f1" name="shopName" label="Название магазина"/><br/>
                <@t.formInput id="f2" name="numberOfReqStaffers" label="Необ-ое кол-во сотрудников"/><br/>
                <#--TODO добавить выбор должности-->
                <@t.formInput id="f4" type="date" name="dateOfWork" label="Дата работы"/><br/>
                <@t.formInput id="f5" type="time" name="startTime" label="Время начала"/><br/>
                <@t.formInput id="f6" type="time" name="endTime" label="Время окончания"/><br/>
                <input type="submit" value="Добавить"/>
            </form>
        </fieldset>
    </div>
    <br><br>

</@c.page>