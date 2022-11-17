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
            <legend>Добавить новый запрос для магазина <b>${shopName}</b>:</legend>
            <form name="add_request" method="post" action="/requests/add_request">
                <label>Необ-ое кол-во сотрудников
                    <select name="numberOfReqStaffers" class="form-control" required>
                        <#list numberOfStaffers! as number>
                            <option value="${number}">${number}</option>
                        </#list>
                    </select>
                </label><br/>
                <label>Необ-ая должность
                    <select name="reqPosition" class="form-control" required>
                        <option value="Продавец">Продавец</option>
                        <option value="Старший продавец">Старший продавец</option>
                        <option value="Товаровед">Товаровед</option>
                        <option value="Директор">Директор</option>
                        <option value="Заместитель директора">Заместитель директора</option>
                    </select><br/>
                </label>
                <@t.formInput id="f4" type="date" name="dateOfWork" label="Дата работы"/><br/>
                <@t.formInput id="f5" type="time" name="startTime" label="Время начала"/><br/>
                <@t.formInput id="f6" type="time" name="endTime" label="Время окончания"/><br/>
                <input type="submit" value="Добавить"/>
            </form>
        </fieldset>
    </div>
    <br><br>

</@c.page>