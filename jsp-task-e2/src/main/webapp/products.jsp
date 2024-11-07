<%@ page import="com.sales.beanclasses.Product,com.sales.FetchData,java.util.List" %>

<html>
  <body>
    <%  FetchData<Product> fetchDataInstance = new FetchData<>();
    List<Product> products = fetchDataInstance.fetchCollection("products"); %>
    <h1 style="width: 100%; text-align: center">Product</h1>

    <table>
      <tbody>
        <tr>
          <th>Product id</th>
          <th>Product name</th>
          <th>Price</th>
          <th>Delete</th>
        </tr>

 <%
 if(products.size() == 0){
  %>  
 <tr>
  <td>no products found</td>
 </tr>

 <% } %>


<% for(Product p:products){ %>
        <tr>
          <td><%= p.getId() %></td>
          <td><%= p.getName() %></td>
          <td><%= p.getPrice() %></td>
          <td><a href="products/deleteItem?id=<%= p.getId() %>" onclick="return confirm('Are you sure you want to delete this item?')">
    <img src="images/delete.png" alt="Delete" height="32" width="32">
</a>
</td>
        </tr>
<% } %>        
      </tbody>
    </table>
    <a href="/sales"><button>back</button></a>
  </body>
</html>
