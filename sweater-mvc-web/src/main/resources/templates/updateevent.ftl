<#import "parts/common.ftl" as c>
<@c.page>

         <div>
             ${event.place}
             ${event.name}
             <div>
            <#if event.filename??>
                <img src="/img/${event.filename}"
            </#if>
             </div>

         </div>
<div class="collapse <#if event??>show</#if>" id="collapseExample">
    <div>
        <form method="post" enctype="multipart/form-data">
            <div class="form-group mt-3">
                <input type="text"  class="form-control ${(placeError??)?string('is-invalid', '')}"
                       value="<#if event??>${event.place}</#if>" name="place" placeholder="Введите тэг" />
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
                <button  class="btn btn-primary"type="submit">Обновить</button>
            </div>
        </form>
    </div>
</div>

</@c.page>