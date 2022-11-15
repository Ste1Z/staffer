<#import "parts/common.ftlh" as c>
<#import "parts/table.ftlh" as t>

<@c.page>

<#--Кнопка выхода из профиля -->
    <form method="get" action="auth/logout" name="to_logout" style="position: absolute; top:5px; right: 5px"
          xmlns="http://www.w3.org/1999/html">
        <button>Выйти из профиля</button>
    </form>
    <br>

<#--Кнопка на главную -->
    <br>
    <form method="get" action="http://localhost:8080" name="to_main">
        <button>Главная</button>
    </form>
    <br><br>

<#--Форма добавления нового магазина -->
    <div>
        <fieldset>
            <legend>Добавить новый магазин:</legend>
            <form name="add_shop" method="post" action="/shops">
                <@t.formInput id="f1" name = "name" label = "Название"/><br/>
                <@t.formInput id="f2" name = "code" label = "Код магазина"/><br/>
                <@t.formInput id="f3" name = "branch" label = "Филиал"/><br/>
                <input type="submit" value="Добавить"/>
            </form>
        </fieldset>
    </div>
    <br><br>

<#--Фильтрация списка магазинов-->
    <div>
        <fieldset>
            <legend>Фильтр списка:</legend>
            <form name="filter_shop" method="get" action="/shops">
                <@t.formInput id = "f4" name = "filter" label = "Фильтр"/><br/>
                <input type="submit" value="Найти"/>
            </form>
        </fieldset>
    </div>
    <br><br>

<#--Отфильтрованный список магазинов. Отображение всего списка без введенного значения -->
    <table border="1px" cellspacing="2" border="1" cellpadding="5">
        <th>№</th>
        <th>Название</th>
        <th>Код</th>
        <th>Филиал</th>
            <#list filterShops![] as shop>
                <tr>
                    <td>${shop?index + 1}</td>
                    <td>${shop.getName()}</td>
                    <td>${shop.getCode()}</td>
                    <td>${shop.getBranch()}</td>
                </tr>
            <#else>
                Ничего не найдено
            </#list>
    </table>

</@c.page>