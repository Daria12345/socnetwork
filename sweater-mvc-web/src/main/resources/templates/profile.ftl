<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<h5>${username}</h5>
<form method="post">
     <div class="form-group row">
         <label class="col-sm-2 col-form-label">Password:</label>
         <div class="col-sm-6">
             <input type="password" name="password"
                    class="form-control ${(passwordError??)?string('is-invalid', '')}"
                    placeholder="Password" />
            <#if passwordError??>
                <div class="invalid-feedback">
                    ${passwordError}
                </div>
            </#if>
         </div>
     </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-6">
                <input type="password" name="password2"
                       class="form-control ${(password2Error??)?string('is-invalid', '')}"
                       placeholder="Retype password" />
                <#if password2Error??>
                    <div class="invalid-feedback">
                        ${password2Error}
                    </div>
                </#if>
            </div>
        </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button class="btn btn-primary" type="submit">Изменить пароль</button>
    <h5>${message?ifExists}</h5>
</form>
</@c.page>