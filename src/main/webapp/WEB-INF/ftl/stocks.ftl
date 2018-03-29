<#-- @ftlvariable name="stocks" type="java.util.List<com.example.lab3.domain.model.Stock>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>List of Stocks</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
        <li><a href="/stock/create">Create a new stock</a></li>
    </ul>
</nav>

<h1>List of Stocks</h1>

<table>
    <thead>
    <tr>
        <th>Number</th>
        <th>Adres</th>
        <th>Phone</th>
        <th>Site</th>
        <th>City</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <#list stocks as stock>
    <tr>
        <td>${stock.number}</td>
        <td>${stock.adres}</td>
        <td>${stock.phone}</td>
        <td>${stock.site}</td>
        <td>${stock.city.name}</td>
        <td><input type="button"  onclick="location.href='/stock/remove/${stock.stockId}'" value="Remove Stock"></td>
    </tr>
    </#list>
    </tbody>
</table>
</body>
</html>