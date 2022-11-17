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
                <@t.formInput id="f3" name = "patronymic" label = "Отчество" /><br/>
                <@t.formInput type="date"  id="f4" name = "dateOfBirth" label = "Дата рождения"/><br/>
                <label>Название ММ
                    <select name="homeShopName" class="form-control" required>
                        <option selected disabled>Выберите магазин</option>
                        <#list shopNames! as shopName>
                            <option value="${shopName.getName()}">${shopName.getName()}</option>
                        </#list>
                    </select>
                </label><br/>
                <label>Должность
                    <select id="position" name="position" class="form-control" required>
                        <option value="Продавец">Продавец</option>
                        <option value="Старший продавец">Старший продавец</option>
                        <option value="Товаровед">Товаровед</option>
                        <option value="Директор">Директор</option>
                        <option value="Заместитель директора">Заместитель директора</option>
                    </select>
                </label><br/>
                <@t.formInput id="f6" name = "phoneNumber" label = "Телефонный номер"/><br/>
                <@t.formInput id="f8" name = "username" label = "Логин"/><br/>
                <@t.formInput id="f9" name = "password" label = "Пароль"/><br/>
                <input type="submit" value="Регистрация"/>
            </form>
        </fieldset>
    </div>
    <br>

</@c.page>