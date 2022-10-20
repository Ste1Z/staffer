<#import "parts/common.ftlh" as c>
<#import "parts/table.ftlh" as t>

<@c.page>
<#--Кнопка выхода из профиля -->
    <form method="get" action="auth/logout" name="to_logout" style="position: absolute; top:5px; right: 5px"
          xmlns="http://www.w3.org/1999/html">
        <button>Выйти из профиля</button>
    </form>

<#--Форма данных пользователя -->
    <br>
    <div>
        <fieldset>
            <legend>Мои данные:</legend>
            <form name="add_staffer" method="post" action="/staffers">
                <@t.formInput id="f1" name="name" value="${staffer.name}" label="Имя"/><br/>
                <@t.formInput id="f2" name="surname" value="${staffer.surname}" label="Фамилия"/><br/>
                <@t.formInput id="f3" name="patronymic" value="${staffer.patronymic}" label="Отчество"/><br/>
                <@t.formInput type="date"  id="f4" name="dateOfBirth" value="${staffer.dateOfBirth}" label="Дата рождения"/>
                <@t.formInput id="f5" name="homeShop" value="${staffer.homeShop}" label="Родной магазин"/><br/>
                <@t.formInput id="f6" name="phoneNumber" value="${staffer.phoneNumber}" label="Номер телефона"/><br/>
                <input type="submit" value="Сохранить"/>
            </form>
        </fieldset>
    </div>


</@c.page>