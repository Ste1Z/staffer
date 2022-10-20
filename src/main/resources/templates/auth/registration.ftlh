<#import "parts/common.ftlh" as c>
<#import "parts/table.ftlh" as t>

<@c.page>

<#--Форма регистрации-->
<#--TODO поменять тип пароля на пароль -->

<#--Форма добавления нового сотрудника -->
    <div>
        <fieldset>
            <legend>Регистрация:</legend>
            <form name="registration" method="post" action="/auth/registration">
                <@t.formInput id="f1" name = "name" label = "Имя"/><br/>
                <@t.formInput id="f2" name = "surname" label = "Фамилия"/><br/>
                <@t.formInput id="f3" name = "patronymic" label = "Отчество"/><br/>
                <@t.formInput type="date"  id="f4" name = "dateOfBirth" label = "Дата рождения"/><br/>
                <@t.formInput id="f5" name = "homeShop" label = "Родной магазин"/><br/>
                <@t.formInput id="f6" name = "phoneNumber" label = "Телефонный номер"/><br/>
                <#--TODO Добавить выбор роли-->
                <@t.formInput id="f8" name = "username" label = "Логин"/><br/>
                <@t.formInput id="f9" name = "password" label = "Пароль"/><br/>
                <input type="submit" value="Регистрация"/>
            </form>
        </fieldset>
    </div>
    <br>

</@c.page>