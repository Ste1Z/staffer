<#import "parts/common.ftlh" as c>


<@c.page>

    <div class="container-fluid d-flex h-100 justify-content-center align-items-center p-0 mb-4">
        <div class="row bg-light shadow-lg">
            <div class="col border rounded p-4">
                <h5 class="text-center mb-3">Поиск по названию ММ</h5>
                <form name="filter_request" method="get" action="/requests/my_requests">
                    <input type="text" class="form-control mb-2" name="filter" id="filter">
                    <div class="col-md-12 text-center">
                        <button type="submit" class="btn btn-primary">Найти</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <#list filterRequests![] as request>
        <div class="row row-cols-1 row-cols-md-3 g-4">
        <div class="col ">
            <div class="card h-100 shadow-lg container-fluid d-flex justify-content-center p-0">
                <div class="card-header bg-transparent text-center">
                    <h5>${request.getShopName()}</h5>
                    <h5>${request.getDateOfWork()?string("dd.MM.yyyy")}</h5>
                </div>
                <div class="card-body text-center">
                    <h6>Необ-мо чел.:</h6> ${request.getNumberOfReqStaffers()}
                    <h6>Необ-мая дол-ть.:</h6> ${request.getReqPosition()}
                    <h6>Время работы:</h6> ${request.getStartTime()?string("HH:mm")}
                    - ${request.getEndTime()?string("HH:mm")}
                    <h6>Поданные заявки:</h6>
                    <a href="/requests/staffers_list/${request.getRequestId()}">${request.getCountOfNotApprovedStaffersInRequest()}</a>
                </div>
                <div class="card-footer">
                    <form name="add_staffer_to_request" method="post" action="/requests/${request.getRequestId()}">
                        <div class="col-md-12 mb-2 text-center">
                            <button type="submit" class="btn btn-primary">Подать заявку</button>
                        </div>
                    </form>
                    <form name="edit_request" method="get" action="/requests/edit_request/${request.getRequestId()}">
                        <div class="col-md-12 mb-2 text-center">
                            <button type="submit" class="btn btn-primary">Редактировать</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    <#else>
        Ничего не найдено
    <#--TODO добавить обработку пустого фильтра-->
    </#list>

    </div>


</@c.page>