<#import "parts/common.ftl" as c>
<@c.page>
<div>Список пользователей</div>
    <ul class="list-group">
    <#list users as user>
        <li class="list-group-item list-group-item-dark">
    <div>
        ${user.username}
        <a href="/admin/users/${user.id}">Блокировать</a>
    </div>
        </li>
    </#list>
    </ul>
</@c.page>