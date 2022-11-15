# Staffer

<h2>Описание:</h2>
Сервис представляет из себя агрегатор заявок для сотрудников магазинов (например, Магнит). Пример: магазину требуется сотрудник(и) для подработки, администратор
создаёт заявку с нужными критерями, сотрудники с других магазинов видят эту заявку и могут подписаться, администратор отмечает всех подписавшихся, после чего
сотрудник выходит на работу.

<h2>Технологический стек:</h2>
<li>Java + Spring Boot + Hibernate</li>
<li>Безопасность: Spring Security</li>
<li>База данных: MySQL</li>
<li>Логирование: Log4J</li>
<li>Тестирование: JUnit</li>
<li>Веб: шаблонизатор FreeMarker</li>

<h2>Возможности сервиса:</h2>
Возможности открываются после регистрации. На выбор две роли: сотрудник и администратор. Для сотрудника доступны: поиск подработок по 
городу/заданному компанией району/внутреннему названию магазина, добавление в список желающих подработать. Для администратора доступны: возможности сотрудника,
создание заявок на подработку, просмотр списка сотрудников в заявке на своём магазине, подтверждение заявок на свой магазин.

<h2>План развития сервиса:</h2>
<table border="1px" cellspacing="2" border="1" cellpadding="5">

<tr>
<th>Функциональность</th>
<th>Готовность</th>
</tr>

<tr>
<td>Базовая регистрация</td>
<td>90%</td>
</tr>
<tr>
<td>Базовое добавление магазина</td>
<td>80%</td>
</tr>

<tr>
<td>Базовое добавление сотрудника</td>
<td>70%</td>
</tr>

<tr>
<td>Базовое добавление заявки</td>
<td>60%</td>
</tr>

<tr>
<td>Связь между пользователем и его профилем</td>
<td>80%</td>
</tr>

<tr>
<td>Добавление сотрудников в заявки</td>
<td>80%</td>
</tr>

<tr>
<td>Фильтрация заявок</td>
<td>20%</td>
</tr>

<tr>
<td>Список заявок для каждого сотрудника</td>
<td>80%</td>
</tr>

<tr>
<td>Редактируемый профиль пользователя</td>
<td>70%</td>
</tr>

<tr>
<td>...</td>
<td>...</td>
</tr>

<tr>
<td>Наведение красоты</td>
<td></td>
</tr>

</table>

<h2>Планы после релиза:</h2>
<li>Развитие API для интеграции с сервисами компаний</li>
<li>Добавление сервиса для управления заявками от лица отдела кадров</li>
<li>Telegram-бот</li>
