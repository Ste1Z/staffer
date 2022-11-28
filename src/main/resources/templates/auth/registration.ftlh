<#import "parts/common.ftlh" as c>

<@c.page>

<#--Форма регистрации-->
<#--TODO поменять тип пароля на пароль и подтверждение двух паролей для проверки -->

    <div class="container-fluid d-flex h-100 justify-content-center align-items-center p-0">
        <div class="row bg-light shadow-lg">
            <div class="col border rounded p-4">
                <h3 class="text-center mb-3">Регистрация</h3>
                <form name="registration_form" method="post" action="/auth/registration">
                    <div class="mb-2">
                        <label for="nameInput" class="form-label">Имя:</label>
                        <input type="text" class="form-control" name="name" id="name">
                    </div>
                    <div class="mb-2">
                        <label for="surnameInput" class="form-label">Фамилия:</label>
                        <input type="text" class="form-control" name="surname" id="surname">
                    </div>
                    <div class="mb-2">
                        <label for="patronymicInput" class="form-label">Отчество:</label>
                        <input type="text" class="form-control" name="patronymic" id="patronymic">
                    </div>
                    <div class="mb-2">
                        <label for="dateOfBirthInput" class="form-label">Дата рождения:</label>
                        <input type="date" class="form-control" name="dateOfBirth" id="dateOfBirth">
                    </div>

                    <div>
                        <label for="homeShopName" class="form-label">Ваш магазин:</label>
                        <select class="form-select mb-2" aria-required="true" id="homeShopName" name="homeShopName">
                            <#list shopNames! as shopName>
                                <option value="${shopName.getName()}">${shopName.getName()}</option>
                            </#list>
                        </select>
                    </div>
                    <#--TODO если вашего магазина нету в списке, нажмите здесь-->
                    <div>
                        <label for="position" class="form-label">Ваша должность:</label>
                        <select class="form-select mb-2" aria-required="true" id="position" name="position">
                            <option value="Продавец">Продавец</option>
                            <option value="Старший продавец">Старший продавец</option>
                            <option value="Товаровед">Товаровед</option>
                            <option value="Зам. директора">Зам. директора</option>
                            <option value="Директор">Директор</option>
                        </select>
                    </div>
                    <div class="mb-2">
                        <label for="phoneNumberInput" class="form-label">Номер телефона:</label>
                        <input type="number" class="form-control" name="phoneNumber" id="phoneNumber">
                    </div>
                    <div class="mb-2">
                        <label for="usernameInput" class="form-label">Логин:</label>
                        <input type="text" class="form-control" name="username" id="username">
                    </div>
                    <div class="mb-2">
                        <label for="passwordInput" class="form-label">Пароль:</label>
                        <input type="text" class="form-control" name="password" id="password">
                    </div>

                    <div class="col-md-12 mb-2 text-center">
                        <button type="submit" class="btn btn-primary">Зарегистрироваться</button>
                    </div>
                    <#--TODO уже зарегистрированы? Можете войти в аккаунт-->
                </form>
            </div>
        </div>
    </div>

</@c.page>