<#import "parts/common.ftl" as c>
<@c.page>
<form method="post">
<div>Список категорий</div>
    <#list categories as postCategories>
    <div class="form-check">
        <input type="checkbox" class="form-check-input" name="delOrgs" id="${postCategories.nameOfCategory}" value="${postCategories.nameOfCategory}">
        <label class="form-check-label" for="${postCategories.nameOfCategory}">${postCategories.nameOfCategory}</label>
    </div>
    <#else>
    Категории не настроены
    </#list>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button class="btn btn-primary" type="submit">Удалить категорию</button>
</form>
</@c.page>