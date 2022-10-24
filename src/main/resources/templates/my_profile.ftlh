<#import "parts/common.ftlh" as c>
<#import "parts/table.ftlh" as t>

<@c.page>

<#--кнопка на главную -->
    <form method="get" action="/main" name="to_main">
        <button>На главную</button>
    </form>

<#--Кнопка выхода из профиля -->
    <form method="get" action="auth/logout" name="to_logout" style="position: absolute; top:5px; right: 5px">
        <button>Выйти из профиля</button>
    </form>

<#--Форма данных пользователя -->
    <br>
    <div>
        <fieldset>
            <legend>Мои данные:</legend>
            <form name="my_profile" method="post" action="/my_profile">
                <@t.formInput id="f1" name="name" value="${staffer.getName()}" label="Имя"/><br/>
                <@t.formInput id="f2" name="surname" value="${staffer.getSurname()}" label="Фамилия"/><br/>
                <@t.formInput id="f3" name="patronymic" value="${staffer.getPatronymic()}" label="Отчество"/><br/>
                <@t.formInput type="date"  id="f4" name="dateOfBirth" value="${staffer.getDateOfBirth()?string.iso}" label="Дата рождения"/><br/>
                <@t.formInput id="f5" name="homeShopName" value="${staffer.getHomeShopName()}" label="Родной магазин"/><br/>
                <@t.formInput id="f6" name="phoneNumber" value="${staffer.getPhoneNumber()}" label="Номер телефона"/><br/>
                <input type="submit" value="Сохранить"/>
            </form>
        </fieldset>
    </div>


</@c.page>