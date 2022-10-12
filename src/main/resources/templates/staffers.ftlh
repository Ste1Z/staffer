<#import "parts/common.ftlh" as c>
<#import "parts/table.ftlh" as t>

<@c.page>

<#--Кнопка на главную -->
    <br>
    <form method="get" action="http://localhost:8080" name="to_main">
        <button>Главная</button>
    </form>
    <br><br>
<#--Форма добавления нового сотрудника -->
    <div>
        <fieldset>
            <legend>Добавить нового сотрудника:</legend>
            <form name="add_staffer" method="get" action="/staffers">
                <@t.formInput id="f1" name = "name" label = "Имя"/><br/>
                <@t.formInput id="f2" name = "surname" label = "Фамилия"/><br/>
                <@t.formInput id="f3" name = "patronymic" label = "Отчество"/><br/>
                <@t.formInput id="f4" name = "dateOfBirth" label = "Дата рождения"/><br/>
                <@t.formInput id="f5" name = "homeShop" label = "Родной магазин"/><br/>
                <input type="submit" value="Добавить"/>
            </form>
        </fieldset>
    </div>
    <br><br>
<#--Фильтрация списка сотрудников-->
    <div>
        <fieldset>
            <legend>Фильтр списка:</legend>
            <form name="filter_shop" method="get" action="/staffers">
                <@t.formInput id = "f6" name = "filter" label = "Фильтр"/><br/>
                <input type="submit" value="Найти"/>
            </form>
        </fieldset>
    </div>
    <br><br>
<#--Отфильтрованный список сотрудников. Отображение всего списка без введенного значения -->
    <table border="1px" cellspacing="2" border="1" cellpadding="5">
        <th>№</th>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Отчество</th>
        <th>Дата рождения</th>
        <th>Родной магазин</th>
        <#list filterStaffers![] as staffer>
            <tr>
                <td>${staffer?index + 1}</td>
                <td>${staffer.name}</td>
                <td>${staffer.surname}</td>
                <td>${staffer.patronymic}</td>
                <td>${staffer.dateOfBirth}</td>
                <td>${staffer.homeShop}</td>
            </tr>
        <#else>
            Ничего не найдено
        </#list>
    </table>

</@c.page>