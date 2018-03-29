<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="form" type="com.example.lab3.domain.model.dummies_forms.StockForm" -->
<#-- @ftlvariable name="cities" type="java.util.List<com.example.lab3.domain.model.City>" -->

<#import  "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Create a new stock</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
    </ul>
</nav>

<h1>Create a new Stock</h1>

<form role="form" name="form" action="" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <div>
        <label for="number">Number</label>
        <input type="text" name="number" id="number" value="" required autofocus/>
    </div>
    <div>
        <label for="phone">Phone (Only characters 0-9)</label>
        <input type="text" name="phone" id="phone" value="${form.phone}" required />
    </div>
    <div>
        <label for="adres">Adress</label>
        <input type="text" name="adres" id="adres" value="${form.adres}" required />
    </div>
    <div>
        <label for="site">Web site</label>
        <input type="text" name="site" id="site" value="${form.site}" required />
    </div>
    <div>
        <label for="city">City</label>

        <select name="city" id="city" required>
            <#list cities as val>
                <option value="${val.name}" <#if form.city == val.name>selected</#if> >${val.name}</option>
            </#list>
        </select>

    </div>
    <button type="submit">Save</button>
</form>

<@spring.bind "form" />
<#if spring.status.error>
<ul>
    <#list spring.status.errorMessages as error>
        <li>${error}</li>
    </#list>
</ul>
</#if>

</body>
</html>