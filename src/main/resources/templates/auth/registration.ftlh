<#import "parts/common.ftlh" as c>

<@c.page>

<#--TODO поменять тип пароля на пароль и подтверждение двух паролей для проверки -->

    <div class="container-fluid d-flex h-100 justify-content-center align-items-center p-0">
        <div class="row bg-light shadow-lg">
            <div class="col border rounded p-4">
                <h3 class="text-center mb-3">Регистрация</h3>
                <form name="registration_form" method="post" action="/auth/registration">
                    <div class="mb-2">
                        <label for="name" class="form-label">Имя:</label>
                        <input type="text" class="form-control" name="name" id="name">
                    </div>
                    <div class="mb-2">
                        <label for="surname" class="form-label">Фамилия:</label>
                        <input type="text" class="form-control" name="surname" id="surname">
                    </div>
                    <div class="mb-2">
                        <label for="patronymic" class="form-label">Отчество:</label>
                        <input type="text" class="form-control" name="patronymic" id="patronymic">
                    </div>
                    <div class="mb-2">
                        <label for="dateOfBirth" class="form-label">Дата рождения:</label>
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
                            <option value="Ст. продавец">Ст. продавец</option>
                            <option value="Товаровед">Товаровед</option>
                            <option value="Зам. директора">Зам. директора</option>
                            <option value="Директор">Директор</option>
                        </select>
                    </div>
                    <div class="mb-2">
                        <label for="phoneNumber" class="form-label">Номер телефона:</label>
                        <input type="number" class="form-control" name="phoneNumber" id="phoneNumber">
                    </div>
                    <div class="mb-2">
                        <label for="username" class="form-label">Логин:</label>
                        <input type="text" class="form-control" name="username" id="username">
                    </div>
                    <div class="mb-2">
                        <label for="password" class="form-label">Пароль:</label>
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