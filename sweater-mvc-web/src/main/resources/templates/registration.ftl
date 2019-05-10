<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<div class="mb-1">Добавление нового пользователя</div>
    <div class="alert alert-danger" role="alert" >
    ${message?ifExists}
    </div>
    <@l.login "/registration" true />
</@c.page>