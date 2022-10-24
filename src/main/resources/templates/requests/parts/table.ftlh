<#macro formInput id name label type="text" value="">
    <label for="${id}">${label}</label>
    <input type="${type}" id="${id}" name="${name}" value="${value}">
</#macro>

<#macro table id rows>
    <table id="${id}" border="1px" cellspacing="2" border="1" cellpadding="5">
        <#list rows as row>
            <tr>
                <td>${row}</td>
            </tr>
        </#list>
    </table>
</#macro>