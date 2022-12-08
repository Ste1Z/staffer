<#import "../parts/common.ftlh" as c>


<@c.page>

    <div class="container-fluid d-flex h-100 justify-content-center align-items-center p-0">
        <div class="row bg-light shadow-lg">
            <div class="col border rounded p-4">
                <h3 class="text-center mb-2">Добавить новый запрос для магазина</h3>
                <h3 class="text-center mb-4"><b>${shopName}</b></h3>
                <form name="add_request" method="post" action="/requests/add_request">
                    <div>
                        <label for="numberOfReqStaffers" class="form-label">Необ-мо чел.:</label>
                        <select class="form-select mb-2" aria-required="true" id="numberOfReqStaffers"
                                name="numberOfReqStaffers">
                            <#list numberOfStaffers! as number>
                                <option value="${number}">${number}</option>
                            </#list>
                        </select>
                    </div>
                    <div>
                        <label for="reqPosition" class="form-label">Необ-мая дол-ть.:</label>
                        <select class="form-select mb-2" aria-required="true" id="reqPosition" name="reqPosition">
                            <option value="Продавец">Продавец</option>
                            <option value="Ст. продавец">Ст. продавец</option>
                            <option value="Товаровед">Товаровед</option>
                            <option value="Зам. директора">Зам. директора</option>
                            <option value="Директор">Директор</option>
                        </select>
                    </div>
                    <div class="mb-2">
                        <label for="dateOfWork" class="form-label">Дата работы:</label>
                        <input type="date" class="form-control" name="dateOfWork" id="dateOfWork">
                    </div>
                    <div class="mb-2">
                        <label for="startTime" class="form-label">Время начала:</label>
                        <input type="time" class="form-control" name="startTime" id="startTime">
                    </div>
                    <div class="mb-2">
                        <label for="endTime" class="form-label">Время окончания:</label>
                        <input type="time" class="form-control" name="endTime" id="endTime">
                    </div>
                    <div class="row m-0 mb-3 text-xl-center">
                        <button type="submit" class="btn btn-primary">Добавить</button>
                    </div>
                </form>
            </div>
        </div>
    </div>


</@c.page>