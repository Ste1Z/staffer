<#import "parts/common.ftlh" as c>

<@c.page>

    <div class="container-fluid d-flex h-100 justify-content-center align-items-center p-0">
        <div class="row bg-light shadow-lg">
            <div class="col border rounded p-4">
                <h3 class="text-center mb-4">Вход</h3>
                <form name="login_form" method="post" action="/process_login">
                    <div class="mb-3">
                        <label for="username" class="form-label">Имя пользователя:</label>
                        <input type="text" class="form-control" name="username" id="username">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Пароль:</label>
                        <input type="password" class="form-control" name="password" id="password">
                    </div>
                    <div class="row m-0 mb-3 text-start">
                        <button type="submit" class="btn btn-primary">Вход</button>
                    </div>
                    <div class="row m-0 mb-3 text-end">
                        <a href="/auth/registration" class="btn btn-primary">Регистрация</a>
                    </div>
                </form>
            </div>
        </div>
    </div>


</@c.page>