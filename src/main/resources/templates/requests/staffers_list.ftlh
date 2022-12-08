<#import "../parts/common.ftlh" as c>


<@c.page>

<#--Общий список сотрудников, готовых выйти на работу -->
    <div>
    <#list notApprovedStaffers![] as staffer>
    <h2>Список сотрудников, готовых выйти на работу</h2>
    <div class="row row-cols-1 row-cols-md-3 g-4">
        <div class="col ">
            <div class="card h-100 shadow-lg container-fluid d-flex justify-content-center p-0 text-bg-secondary">
                <div class="card-header bg-transparent text-center">
                    <h5>${staffer.getSurname()}</h5>
                    <h5>${staffer.getName()}</h5>
                    <h5>${staffer.getPatronymic()}</h5>
                </div>
                <div class="card-body text-center">
                    <h6>Дата рождения:</h6> ${staffer.getDateOfBirth()}
                    <h6>Родной магазин:</h6> ${staffer.getHomeShopName()}
                    <h6>Филиал:</h6> ${staffer.getBranch()}
                    <h6>Должность:</h6> ${staffer.getPosition()}
                    <h6>Номер телефона:</h6> ${staffer.getPhoneNumber()}
                </div>
                <div class="card-footer">
                    <form name="approve_staffer" method="post"
                          action="/requests/staffers_list/${requestId}/${staffer.getStafferId()}">
                        <div class="col-md-12 mb-2 text-center">
                            <button type="submit" class="btn btn-primary">Одобрить</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <#else>
            Ничего не найдено
        </#list>
    </div>


    <#--Список одобренных к выходу сотрудников-->
    <div>
        <#list approvedStaffers![] as staffer>
        <h2>Список одобренных к выходу сотрудников</h2>

        <div class="row row-cols-1 row-cols-md-3 g-4">
            <div class="col ">
                <div class="card h-100 shadow-lg container-fluid d-flex justify-content-center p-0 text-bg-success">
                    <div class="card-header bg-transparent text-center">
                        <h5>${staffer.getSurname()}</h5>
                        <h5>${staffer.getName()}</h5>
                        <h5>${staffer.getPatronymic()}</h5>
                    </div>
                    <div class="card-body text-center">
                        <h6>Дата рождения:</h6> ${staffer.getDateOfBirth()}
                        <h6>Родной магазин:</h6> ${staffer.getHomeShopName()}
                        <h6>Филиал:</h6> ${staffer.getBranch()}
                        <h6>Должность:</h6> ${staffer.getPosition()}
                        <h6>Номер телефона:</h6> ${staffer.getPhoneNumber()}
                    </div>
                    <div class="card-footer">
                        <form name="approve_staffer" method="post"
                              action="/requests/staffers_list/${requestId}/${staffer.getStafferId()}">
                            <div class="col-md-12 mb-2 text-center">
                                <button type="submit" class="btn btn-primary">Отменить выход</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <#else>
                Ничего не найдено
            </#list>
        </div>
</@c.page>