<#import "parts/common.ftl" as c>
<@c.page>
<div>Список постов</div>
    <ul class="list-group">
    <#list events as event>
        <li class="list-group-item list-group-item-dark">
    <div>
        ${event.name}
        ${event.place}
        <a href="/admin/events/${event.name}">Удалить</a>
        <a href="/admin/events/update/${event.name}">Редактировать</a>
    </div>
        </li>
    </#list>
    </ul>
</@c.page>