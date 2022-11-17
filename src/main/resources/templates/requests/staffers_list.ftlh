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

<#--Общий список сотрудников, готовых выйти на работу -->
    <h2>Список сотрудников, готовых выйти на работу</h2>
    <table border="1px" cellspacing="2" border="1" cellpadding="5">
        <th>№</th>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Отчество</th>
        <th>Дата рождения</th>
        <th>Родной магазин</th>
        <th>Филиал</th>
        <th>Должность</th>
        <th>Номер телефона</th>
        <th>Одобрить выход</th>
        <#list notApprovedStaffers![] as staffer>
            <tr>
                <td>${staffer?index + 1}</td>
                <td>${staffer.getName()}</td>
                <td>${staffer.getSurname()}</td>
                <td>${staffer.getPatronymic()}</td>
                <td>${staffer.getDateOfBirth()}</td>
                <td>${staffer.getHomeShopName()}</td>
                <td>${staffer.getBranch()}</td>
                <td>${staffer.getPosition()}</td>
                <td>${staffer.getPhoneNumber()}</td>
                <td>
                    <form name="approve_staffer" method="post" action="/requests/staffers_list/${requestId}/${staffer.getStafferId()}">
                        <input type="submit" value="Одобрить"/>
                    </form>
                </td>
            </tr>
        <#else>
            Ничего не найдено
        </#list>
    </table>
    <br><br>

<#--Список одобренных к выходу сотрудников-->
    <h2>Список одобренных к выходу сотрудников</h2>
    <table border="1px" cellspacing="2" border="1" cellpadding="5">
        <th>№</th>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Отчество</th>
        <th>Дата рождения</th>
        <th>Родной магазин</th>
        <th>Филиал</th>
        <th>Должность</th>
        <th>Номер телефона</th>
        <th>Отменить выход</th>
        <#list approvedStaffers![] as staffer>
            <tr>
                <td>${staffer?index + 1}</td>
                <td>${staffer.getName()}</td>
                <td>${staffer.getSurname()}</td>
                <td>${staffer.getPatronymic()}</td>
                <td>${staffer.getDateOfBirth()}</td>
                <td>${staffer.getHomeShopName()}</td>
                <td>${staffer.getBranch()}</td>
                <td>${staffer.getPosition()}</td>
                <td>${staffer.getPhoneNumber()}</td>
                <td>
                    <form name="cancel_staffer" method="post" action="/requests/staffers_list/${requestId}/${staffer.getStafferId()}">
                        <input type="submit" value="Отменить выход"/>
                    </form>
                </td>
            </tr>
        <#else>
            Ничего не найдено
        </#list>
    </table>


</@c.page>