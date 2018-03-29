<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="form" type="com.example.lab3.domain.model.dummies_forms.ItemForm" -->
<#-- @ftlvariable name="stocks" type="java.util.List<com.example.lab3.domain.model.Stock>" -->

<#import  "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Create a new item</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
    </ul>
</nav>

<h1>Create a new Item</h1>

<form role="form" name="form" action="" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <div>
        <label for="name">Name</label>
        <input type="text" name="name" id="name" value="" required autofocus/>
    </div>
    <div>
        <label for="producer">Producer</label>
        <input type="text" name="producer" id="producer" value="" required />
    </div>
    <div>
        <label for="price">Price</label>
        <input type="text" name="price" id="price" value="" required />
    </div>
    <div>
        <label for="quantity">Quantity</label>
        <input type="text" name="quantity" id="quantity" value="" required />
    </div>
    <div>
        <label for="minQuantity">Minimal quantity</label>
        <input type="text" name="minQuantity" id="minQuantity" value="" required />
    </div>
    <div>
        <label for="stock">Stock</label>

        <select name="stock" id="stock" required>
        <#list stocks as val>
            <option value="${val.getInfo()}"
                    <#if form.stock == val.getInfo()>selected</#if> >${val.getInfo()}</option>
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