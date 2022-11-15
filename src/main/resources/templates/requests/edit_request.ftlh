<#import "../parts/common.ftlh" as c>
<#import "../parts/table.ftlh" as t>

<@c.page>

<#--Форма редактирования запроса по id -->
    <div>
        <fieldset>
            <legend>Редактировать запрос для магазина <b>${request.getShopName()}</b>:</legend>
            <form name="edit_request" method="post" action="/requests/edit_request/${request.getRequestId()}">
                <label>Необ-ое кол-во сотрудников
                    <select name="numberOfReqStaffers" class="form-control" required>
                        <option disabled selected value="${request.getNumberOfReqStaffers()}">${request.getNumberOfReqStaffers()}</option>
                        <#list numberOfStaffers! as number>
                            <option value="${number}">${number}</option>
                        </#list>
                    </select>
                </label><br/>
                <#--TODO добавить выбор должности-->
                <@t.formInput id="f4" type="date" name="dateOfWork" value="${request.getDateOfWork()?string.iso}" label="Дата работы"/><br/>
                <@t.formInput id="f5" type="time" name="startTime" value="${request.getStartTime()}" label="Время начала"/><br/>
                <@t.formInput id="f6" type="time" name="endTime" value="${request.getEndTime()}" label="Время окончания"/><br/>
                <input type="submit" value="Сохранить"/>
            </form>
        </fieldset>
    </div>

</@c.page>