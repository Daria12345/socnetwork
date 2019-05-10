<#import "parts/common.ftl" as c>


<@c.page>
<form method="post">
   <h3> ${message?ifExists} </h3>

  <div>
      <h2>Вид поста:</h2>
    <#list kind as listOfKind>
    <div class="form-check">
      <input type="checkbox" class="form-check-input" name="kinds" id="${listOfKind.nameOfCategory}" value="${listOfKind.nameOfCategory}">
      <label class="form-check-label" for="${listOfKind.nameOfCategory}">${listOfKind.nameOfCategory}</label>
    </div>
    <#else>
    No organisations
    </#list>
  </div>
  <div>
      <h2>Тематика поста:</h2>
    <#list theme as listOfTheme>
        <div class="form-check">
            <input type="checkbox" class="form-check-input" name="themes" id="${listOfTheme.nameOfCategory}" value="${listOfTheme.nameOfCategory}">
            <label class="form-check-label" for="${listOfTheme.nameOfCategory}">${listOfTheme.nameOfCategory}</label>
        </div>
    <#else>
    No organisations
    </#list>
  </div>
  <div>
      <h2>Категория значимости поста:</h2>
    <#list category as listOfCategory>
        <div class="form-check">
            <input type="checkbox" class="form-check-input" name="categories" id="${listOfCategory.nameOfCategory}" value="${listOfCategory.nameOfCategory}">
            <label class="form-check-label" for="${listOfCategory.nameOfCategory}">${listOfCategory.nameOfCategory}</label>
        </div>
    <#else>
    Нет категорий
    </#list>
  </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button class="btn btn-primary" type="submit">Добавить категории</button>
</form>
</@c.page>