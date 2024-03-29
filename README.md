# Staffer

<h2>Описание:</h2>
Сервис представляет из себя агрегатор заявок для сотрудников магазинов (например, Магнит). Пример: магазину требуется
сотрудник(и) для подработки, администратор
создаёт заявку с нужными критерями, сотрудники с других магазинов видят эту заявку и могут подписаться, администратор
отмечает всех подписавшихся, после чего
сотрудник выходит на работу.
<details>
<summary><b><h2>Скриншоты приложения:</h2></b></summary>
<div align="center">
<img src="https://github.com/Ste1Z/staffer/blob/master/screenshots/login.png?raw=true"><br>
Окно логина<br>
<img src="https://github.com/Ste1Z/staffer/blob/master/screenshots/registration.png?raw=true"><br>
Окно регистрации<br>
<img src="https://github.com/Ste1Z/staffer/blob/master/screenshots/allRequests.png?raw=true"><br>
Все заявки<br>
<img src="https://github.com/Ste1Z/staffer/blob/master/screenshots/addRequest.png?raw=true"><br>
Страница создания заявки<br>
<img src="https://github.com/Ste1Z/staffer/blob/master/screenshots/staffersList.png"><br>
Страница с откликнувшимися на заявку работниками<br>
<img src="https://github.com/Ste1Z/staffer/blob/master/screenshots/admin.png?raw=true"><br>
Панель администратора<br>
</div>
</details>

<h2>Технологический стек:</h2>
<li>Java + Spring Boot + Hibernate</li>
<li>Безопасность: Spring Security</li>
<li>База данных: MySQL</li>
<li>Логирование: Log4J2</li>
<li>Тестирование: JUnit</li>
<li>Веб: Thymeleaf + Bootstrap</li>
<li>Дополнительно: Lombok + Spring DevTools</li>

<h2>Возможности сервиса:</h2>
Возможности открываются после регистрации. На выбор две роли: сотрудник и администратор. Для сотрудника доступны: поиск
подработок по
городу/заданному компанией району/внутреннему названию магазина, добавление в список желающих подработать. Для
администратора доступны: возможности сотрудника,
создание заявок на подработку, просмотр списка сотрудников в заявке на своём магазине, подтверждение заявок на свой
магазин.

<h2>План развития сервиса:</h2>
<table border="1px" cellspacing="2" border="1" cellpadding="5">

<tr>
<th>Функциональность</th>
<th>Готовность</th>
</tr>

<tr>
<td>Регистрация</td>
<td>90%</td>
</tr>
<tr>
<td>Добавление магазина</td>
<td>80%</td>
</tr>

<tr>
<td>Добавление сотрудника</td>
<td>70%</td>
</tr>

<tr>
<td>Добавление заявки</td>
<td>80%</td>
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
<td>Обработка ошибок</td>
<td>50%</td>
</tr>

<tr>
<td>...</td>
<td>...</td>
</tr>

<tr>
<td>Наведение красоты</td>
<td>50%</td>
</tr>

</table>

<h2>Планы после релиза:</h2>
<li>Развитие API для интеграции с сервисами компаний</li>
<li>Добавление сервиса для управления заявками от лица отдела кадров</li>
<li>Telegram-бот</li>
