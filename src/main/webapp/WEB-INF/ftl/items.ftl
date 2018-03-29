<#-- @ftlvariable name="items" type="java.util.List<com.example.lab3.domain.model.Item>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>List of Items</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
        <li><a href="/item/create">Create a new item</a></li>
    </ul>
</nav>

<h1>List of Items</h1>

<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Producer</th>
        <th>Prise</th>
        <th>Quantity</th>
        <th>Min. Quantity</th>
        <th>Stock City</th>
        <th>Stock number</th>
        <th>Stock adres</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <#list items as item>
    <tr>
        <td>${item.name}</td>
        <td>${item.producer}</td>
        <td>${item.price}</td>
        <td>${item.quantity}</td>
        <td>${item.minQuantity}</td>
        <td>${item.stock.city.name}</td>
        <td>${item.stock.number}</td>
        <td>${item.stock.adres}</td>
        <td><input type="button"  onclick="location.href='/item/remove/${item.itemId}'" value="Remove Item"></td>
    </tr>
    </#list>
    </tbody>
</table>
</body>
</html>