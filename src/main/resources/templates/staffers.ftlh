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

<#--Форма добавления нового сотрудника -->
    <div>
        <fieldset>
            <legend>Добавить нового сотрудника:</legend>
            <form name="add_staffer" method="post" action="/staffers">
                <@t.formInput id="f1" name = "name" label = "Имя"/><br/>
                <@t.formInput id="f2" name = "surname" label = "Фамилия"/><br/>
                <@t.formInput id="f3" name = "patronymic" label = "Отчество"/><br/>
                <@t.formInput type="date"  id="f4" name = "dateOfBirth" label = "Дата рождения"/><br/>
                <@t.formInput id="f5" name = "homeShopName" label = "Родной магазин"/><br/>
                <@t.formInput id="f6" name = "phoneNumber" label = "Номер телефона"/><br/>
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
                <@t.formInput id = "f7" name = "filter" label = "Фильтр"/><br/>
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
        <th>Номер телефона</th>
        <#list filterStaffers![] as staffer>
            <tr>
                <td>${staffer?index + 1}</td>
                <td>${staffer.getName()}</td>
                <td>${staffer.getSurname()}</td>
                <td>${staffer.getPatronymic()}</td>
                <td>${staffer.getDateOfBirth()}</td>
                <td>${staffer.getHomeShopName()}</td>
                <td>${staffer.getPhoneNumber()}</td>
            </tr>
        <#else>
            Ничего не найдено
        </#list>
    </table>

</@c.page>