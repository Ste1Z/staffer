<#import "parts/common.ftlh" as c>
<#import "parts/table.ftlh" as t>

<@c.page>

<#--Форма регистрации-->
<#--TODO поменять тип пароля на пароль -->
    <br>
    <div>
        <fieldset>
            <legend>Регистрация:</legend>
            <form name="registration" method="post" action="/auth/registration">
                <@t.formInput id="f1" name = "username" label = "Имя пользователя"/><br/>
                <@t.formInput id="f2" name = "password" label = "Пароль"/><br/>
                <input type="submit" value="Регистрация"/>
            </form>
        </fieldset>
    </div>

</@c.page>