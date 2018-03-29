<#-- @ftlvariable name="cities" type="java.util.List<com.example.lab3.domain.model.City>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>List of Cities</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
        <li><a href="/city/create">Create a new city</a></li>
    </ul>
</nav>

<h1>List of Cities</h1>

<table>
    <thead>
    <tr>
        <th>Name</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <#list cities as city>
    <tr>
        <td>${city.name}</td>
        <td><input type="button"  onclick="location.href='/city/remove/${city.id}'" value="Remove City"></td>
    </tr>
    </#list>
    </tbody>
</table>
</body>
</html>