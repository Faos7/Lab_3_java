<#-- @ftlvariable name="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable name="currentUser" type="com.example.lab3.domain.security.CurrentUser" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Home page</title>
</head>
<body>
<nav role="navigation">
    <ul>
    <#if !currentUser??>
        <li><a href="/login">Log in</a></li>
        <li><a href="/public/create">Register</a> </li>
    </#if>
    <#if currentUser??>
        <li>
            <form action="/logout" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit">Log out</button>
            </form>
        </li>
        <li><a href="/cities">View all cities</a></li>
        <li><a href="/stocks">View all stocks</a></li>
        <li><a href="/items">View all items</a></li>
        <#--<li><a href="/libraries">View all libraries</a></li>-->
        <#--<li><a href="/faculties">View all faculties</a></li>-->
        <#--<li><a href="/groups">View all groups</a></li>-->
    </#if>
    </ul>
</nav>
</body>
</html>