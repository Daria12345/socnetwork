<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<@c.page>

    <div class="text-center ">
        <h2><p class="text-center "> ${event.name}</p></h2>
    <div class="row">


        <div class="col">
            <#if event.filename??>
                <img class="event-image img-responsive img-rounded text-center" src="/img/${event.filename}"
                     alt="картинка бутстрап">
            </#if>
        </div>


        <div class="col">
            <div class="list-group">
                <h5><p class="text-center list-group-item list-group-item-action list-group-item-primary">
                        Тег: ${event.place}</p></h5>
                <br>
                <h5><p class="text-center list-group-item list-group-item-action list-group-item-primary">
                        Дата: ${event.date}</p></h5>
                <br>
                <h5><p class="text-center list-group-item list-group-item-action list-group-item-primary"> Количество
                        просмотров ${event.numberofguests}</h5>
                </p>
                <h5><p class="text-center list-group-item list-group-item-action list-group-item-primary">
                        Описание: ${event.requiredage}</h5>
                </p>
                <h5><p class="text-center list-group-item list-group-item-action list-group-item-primary"> Категории:

                        <#list categories as category >
                            ${category.nameOfCategory},
                        <#else>
                            Категории не настроены
                        </#list>
                </h5>
                </p>
            </div>


    <div class="text-center">
        <a class="btn btn-primary  " data-toggle="collapse" href="#collapseExample" role="button"
           aria-expanded="false" aria-controls="collapseExample">
            Отправить на email!
        </a>
    </div>
    <div class="collapse <#if guest??>show</#if>" id="collapseExample">
        <div>
            <form method="post" enctype="multipart/form-data">
                <div class="form-group mt-3">
                    <input type="text" class="form-control ${(guestnameError??)?string('is-invalid', '')}"
                           value="<#if guest??>${guest.guestname}</#if>" name="guestname"
                           placeholder="Введите ваше имя"/>
                    <#if guestnameError??>
                        <div class="invalid-feedback">
                            ${guestnameError}
                        </div>
                    </#if>
                </div>
                <div class="form-group mt-3">
                    <input type="email" name="email" class="form-control ${(emailError??)?string('is-invalid', '')}"
                           value="<#if guest??>${guest.email}</#if>" placeholder="email@address.com"/>
                    <#if emailError??>
                        <div class="invalid-feedback">
                            ${emailError}
                        </div>
                    </#if>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group">
                    <button class="btn btn-primary button" type="submit">Отправить</button>
                </div>
            </form>
        </div>
    </div>
    <div class=" justify-content-around event-button-container">
        <div class="d-flex flex-row justify-content-between">
        <#if isAdmin>
            <div class="text-center">
                <form method="get" action="/addpersonal/${event.name}">
                    <button class="btn btn-primary mt-3 button" type="submit">Добавить категорию</button>
                </form>
            </div>
        </#if>
        <#if isAdmin>
            <div class="text-center">

                <form method="get" action="/listOfpersonal/${event.name}">
                    <button class="btn btn-primary mt-3 button" type="submit">Просмотреть категории</button>
                </form>
            </div>
        </#if>
        </div>
        <div class="d-flex flex-row justify-content-between">
        <#if isUser>
            <div class="text-center">
                <form method="get" action="/events/update/${event.name}">
                    <button class="btn btn-primary mt-3 button" type="submit">Редактировать</button>
                </form>
            </div>
        </#if>
        <#if isFavorite>
            <div class="text-center">

                <form method="post" action="/favorites/add/${event.name}">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button class="btn btn-primary mt-3 button" type="submit">Добавить в избранное</button>
                </form>
            </div>
        </#if>
        </div>
        <#if !isFavorite>
            <div class="text-center">
                <form method="post" action="/favorites/delete/${event.name}">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button class="btn btn-primary mt-3 button" type="submit">Убрать из избранного</button>
                </form>
            </div>
        </#if>
    </div>
    </div>
    </div>
</@c.page>