<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>

    <#if isMain>
<div class="form-row">
    <div class="form-group col-md-6">
        <form method="get" action="/main" class="form-inline">
            <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Поиск по тегу">
            <button type="submit" class="btn btn-primary ml-2">Искать</button>
        </form>
    </div>
</div>
        <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
            Добавить новый пост
        </a>
        <div class="collapse <#if event??>show</#if>" id="collapseExample">
            <div>
                <form method="post" action="/main" enctype="multipart/form-data">
                    <div class="form-group mt-3">
                        <input type="text"  class="form-control ${(placeError??)?string('is-invalid', '')}"
                               value="<#if event??>${event.place}</#if>" name="place" placeholder="Введите тег" />
                        <#if placeError??>
                            <div class="invalid-feedback">
                                ${placeError}
                            </div>
                        </#if>
                    </div>

                    <div class="form-group">
                        <input type="text" class="form-control ${(nameError??)?string('is-invalid', '')}"
                               value="<#if event??>${event.name}</#if>" name="name" placeholder="Введите заголовок" />
                        <#if nameError??>
                            <div class="invalid-feedback">
                                ${nameError}
                            </div>
                        </#if>
                    </div>
                    <div class="form-group mt-3">
                        <input type="text"class="form-control ${(requiredageError??)?string('is-invalid', '')}"
                               value="<#if event??>${event.requiredage}</#if>" name="requiredage" placeholder="Введите описание" />
                        <#if requiredageError??>
                            <div class="invalid-feedback">
                                Поле не должно быть пустым
                            </div>
                        </#if>
                    </div>
                    <div class="form-group">
                        <div class="custom-file">
                            <input type="file" name="file" id="customFile">
                            <label class="custom-file-label" for="customFile">Choose file</label>
                        </div>
                    </div>

                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    <div class="form-group">
                        <button  class="btn btn-primary"type="submit">Добавить</button>
                    </div>
                </form>
            </div>
        </div>
    </#if>


<div class="card-columns">
    <#list events as event>
        <div class="card my-3 text-center">
        <#if event.filename??>
        <img src="/img/${event.filename}" class="card-img-top">
        </#if>
            <div class="m-2">
                Название:  <a href="/main/${event.name}" >${event.name}</a> <br>
                <span> Тэг:  <i>${event.place}</i> </span><br>
                <span> Дата добавления: <i>${event.date}</i> </span><br>
                <span> Автор: <i>${event.user}</i> </span><br>
            </div>
        </div>
    <#else>
    Нет постов
    </#list>
</div>
</@c.page>