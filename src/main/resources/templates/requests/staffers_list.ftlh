<#import "../parts/common.ftlh" as c>


<@c.page>

<#--навигация по страницам-->
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb m-3">
            <li class="breadcrumb-item"><a href="/requests">Заявки</a></li>
            <li class="breadcrumb-item active" aria-current="page">Список сотрудников</li>
        </ol>
    </nav>

<#--Общий список сотрудников, готовых выйти на работу -->
    <div class="m-3">
        <h2>Список сотрудников, готовых выйти на работу</h2>
    </div>
    <div class="container-fluid mb-2 d-flex">
        <div class=" shadow border rounded" style="background-color: #fcff7f">
            <div class="row flex-row  p-2">
                <#list notApprovedStaffers![] as staffer>
                    <div class="col">
                        <#--карточка с сотрудником-->
                        <div class="card shadow mb-2" style="width: 20rem; height: 22rem">
                            <div class="card-header bg-transparent text-center">
                                <h4>${staffer.getFullName()}</h4>
                            </div>
                            <div class="card-body text-center">
                                <div>
                                    <h5><img class="mb-3" src="/images/dateOfBirth_min.png" width="20" height="20"
                                             alt="dateOfBirth_min">${staffer.getDateOfBirth()?string["dd.MM.yyyy"]}</h5>
                                </div>
                                <div>
                                    <h5><img class="mb-3" src="/images/homeShop_min.png" width="20" height="20"
                                             alt="homeShop_min">${staffer.getHomeShopName()}</h5>
                                </div>
                                <div>
                                    <h5><img class="mb-3" src="/images/branch_min.png" width="20" height="20"
                                             alt="branch_min">${staffer.getBranch()}</h5>
                                </div>
                                <div>
                                    <h5><img class="mb-3" src="/images/position_min.png" width="20" height="20"
                                             alt="position_min">${staffer.getPosition()}</h5>
                                </div>
                                <div>
                                    <h5><img src="/images/phoneNumber_min.png" width="20" height="20"
                                             alt="phoneNumber_min">${staffer.getPhoneNumber()}</h5>
                                </div>
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

                <#--TODO добавить обработку пустого фильтра-->
                </#list>
            </div>
        </div>
    </div>


<#--Список одобренных к выходу сотрудников-->
    <div class="m-3">
        <h2>Список одобренных к выходу сотрудников</h2>
    </div>
    <div class="container-fluid mb-2 d-flex">
        <div class=" shadow border rounded" style="background-color: #b0f7a6">
            <div class="row flex-row  p-2">
                <#list approvedStaffers![] as staffer>
                    <div class="col">
                        <#--карточка с сотрудником-->
                        <div class="card shadow mb-2" style="width: 20rem; height: 22rem">
                            <div class="card-header bg-transparent text-center">
                                <h5>${staffer.getFullName()}</h5>
                            </div>
                            <div class="card-body text-center">
                                <div>
                                    <h5><img class="mb-3" src="/images/dateOfBirth_min.png" width="20" height="20"
                                             alt="dateOfBirth_min">${staffer.getDateOfBirth()?string["dd.MM.yyyy"]}</h5>
                                </div>
                                <div>
                                    <h5><img class="mb-3" src="/images/homeShop_min.png" width="20" height="20"
                                             alt="homeShop_min">${staffer.getHomeShopName()}</h5>
                                </div>
                                <div>
                                    <h5><img class="mb-3" src="/images/branch_min.png" width="20" height="20"
                                             alt="branch_min">${staffer.getBranch()}</h5>
                                </div>
                                <div>
                                    <h5><img class="mb-3" src="/images/position_min.png" width="20" height="20"
                                             alt="position_min">${staffer.getPosition()}</h5>
                                </div>
                                <div>
                                    <h5><img src="/images/phoneNumber_min.png" width="20" height="20"
                                             alt="phoneNumber_min">${staffer.getPhoneNumber()}</h5>
                                </div>
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

                <#--TODO добавить обработку пустого фильтра-->
                </#list>
            </div>
        </div>
    </div>

</@c.page>