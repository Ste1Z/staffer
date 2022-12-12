<#import "parts/common.ftlh" as c>

<@c.page>
<#--навигация по страницам-->
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb m-3">
            <li class="breadcrumb-item active" aria-current="page">Заявки</li>
        </ol>
    </nav>

<#--форма фильтра-->
    <div class="container-fluid mb-3">
        <div class="bg-light shadow">
            <div class="col border rounded p-3">
                <h5 class="text-start mb-2">Фильтрация</h5>
                <form class="row g-2 mb-2" name="filter_request" method="get" action="/requests">
                    <label for="filter">Поиск по названию ММ</label>
                    <div class="col-auto">
                        <input type="text" class="form-control" name="filter" id="filter">
                    </div>
                    <div class="col-auto">
                        <button type="submit" class="btn btn-primary">Найти</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
<#--список заявок-->
    <div class="container-fluid mb-2 d-flex">
        <div class="bg-light shadow border rounded">
            <div class="row flex-row  p-2">
                <#--карточка с добавлением заявки-->
                <div class="col">
                    <div class="card shadow mb-2 justify-content-center text-center"
                         style="width: 20rem; height: 20rem">
                        <a class="stretched-link" href="requests/add_request"></a>
                        <i class="bi bi-file-plus opacity-50" style="font-size: 17rem;"></i>
                    </div>
                </div>

                <#list filterRequests![] as request>
                    <div class="col">
                        <#--карточка с заявкой-->
                        <div class="card shadow mb-2" style="width: 20rem; height: 20rem">
                            <div class="card-header bg-transparent text-center">
                                <h4><b>${request.getShopName()}</b></h4>
                            </div>
                            <div class="card-body text-center">
                                <#--<a class="stretched-link" href="/requests/staffers_list/${request.getRequestId()}"></a>-->

                                <#--отображение необходимого кол-ва сотрудников, поданных заявок и одобренных заявок-->
                                <div class="mb-3">
                                    <h5>
                                        <a href="/requests/staffers_list/${request.getRequestId()}"
                                           style="color: #f9d403">
                                            <img src="images/staffer_search_min.png"
                                                 alt="staffer_search_min">${request.getNumberOfReqStaffers()}
                                        </a>
                                        <a href="/requests/staffers_list/${request.getRequestId()}"
                                           style="color: #7f7f7f">
                                            <img src="images/staffer_not_approved_min.png"
                                                 alt="staffer_not_approved_min">
                                            ${request.getCountOfNotApprovedStaffersInRequest()}
                                        </a>
                                        <a href="/requests/staffers_list/${request.getRequestId()}"
                                           style="color: #07c967">
                                            <img src="images/staffer_approved_min.png"
                                                 alt="staffer_approved_min">
                                            ${request.getCountOfApprovedStaffersInRequest()}
                                        </a>
                                    </h5>
                                </div>
                                <#--требуемая должность-->
                                <div class="mb-3">
                                    <h5><i class="bi bi-search p-1"></i>${request.getReqPosition()}</h5>
                                </div>
                                <#--требуемая дата выхода на работу-->
                                <div class="mb-3">
                                    <h5>
                                        <i class="bi bi-calendar2-date-fill p-1"></i>${request.getDateOfWork()?string("dd.MM.yyyy")}
                                    </h5>
                                </div>
                                <#--требуемое время работы-->
                                <div class="mb-3">
                                    <h5>
                                        <i class="bi bi-clock-fill p-1"></i>
                                        ${request.getStartTime()?string("HH:mm")}
                                        - ${request.getEndTime()?string("HH:mm")}
                                    </h5>
                                </div>
                            </div>
                            <div class="card-footer">
                                <div class="row justify-content-end">
                                    <#--кнопка добавления залогиненного пользователя в заявку-->
                                    <div class="col-auto">
                                        <form name="add_staffer_to_request" method="post"
                                              action="/requests/${request.getRequestId()}">
                                            <button type="submit" class="btn btn-primary">Подать заявку</button>
                                        </form>
                                    </div>
                                    <#--кнопка редактирования заявки-->
                                    <div class="col-auto">
                                        <form name="edit_request" method="get"
                                              action="/requests/edit_request/${request.getRequestId()}">
                                            <button type="submit" class="btn">
                                                <i class="bi bi-pencil-square"></i>
                                            </button>
                                        </form>
                                    </div>
                                </div>
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