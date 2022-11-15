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
    <form method="get" action="http://localhost:8080/main" name="to_main">
        <button>Главная</button>
    </form>
    <br><br>

<#--кнопка добавления новой заявки -->
    <form method="get" action="/requests/add_request" name="to_add_request">
        <button>Новая заявка</button>
    </form>
    <br>

<#--Фильтрация списка запросов-->
    <div>
        <fieldset>
            <legend>Фильтр списка:</legend>
            <form name="filter_request" method="get" action="/requests">
                <@t.formInput id = "f7" name = "filter" label = "Фильтр"/><br/>
                <input type="submit" value="Найти"/>
            </form>
        </fieldset>
    </div>
    <br><br>

<#--Отфильтрованный список заявок. Отображение всего списка без введенного значения -->
    <table border="1px" cellspacing="2" border="1" cellpadding="5">
        <th>№</th>
        <th>Название ММ</th>
        <th>Необ-ое кол-во сотрудников</th>
        <th>Дата запроса</th>
        <th>Дата работы</th>
        <th>Время начала</th>
        <th>Время окончания</th>
        <th>Поданные заявки</th>
        <th>Редактировать заявку</th>
        <th>Подать заявку</th>
        <#list filterRequests![] as request>
            <tr>
                <td>${request?index + 1}</td>
                <td>${request.getShopName()}</td>
                <td>${request.getNumberOfReqStaffers()}</td>
                <td>${request.getDateOfRequest()?string("dd.MM.yyyy")}</td>
                <td>${request.getDateOfWork()?string("dd.MM.yyyy")}</td>
                <td>${request.getStartTime()?string("HH:mm")}</td>
                <td>${request.getEndTime()?string("HH:mm")}</td>
                <#--TODO добавить ссылку на список сотрудников в заявке, счетчик заявок в значении ссылки-->
                <td>
                    <a href="/requests/staffers_list/${request.getRequestId()}">${request.getCountOfStaffersInRequest()}</a>
                </td>
                <td>
                    <form name="edit_request" method="get" action="/requests/edit_request/${request.getRequestId()}">
                        <input type="submit" value="Редактировать"/>
                    </form>
                </td>
                <td>
                    <form name="add_staffer_to_request" method="post" action="/requests/${request.getRequestId()}">
                        <input type="submit" value="Подать заявку"/>
                    </form>
                </td>
            </tr>
        <#else>
            Ничего не найдено
        </#list>
    </table>

</@c.page>