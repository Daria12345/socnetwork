<#import "parts/common.ftl" as c>
<@c.page>
<form method="get" action="/admin/users">
    <button  class="btn btn-secondary btn-lg" type="submit">Список пользователей</button>
</form>
    <p> </p>
<form method="get" action="/admin/events">
    <button class="btn btn-secondary btn-lg" type="submit">Список постов</button>
</form>
</@c.page>