<%@ page import=" com.sales.beanclasses.Product,com.sales.FetchProduct" %>

<html>
  <body>
    <% String requestParameter = request.getParameter("id"); int id =
    Integer.parseInt(requestParameter); FetchProduct fp = new FetchProduct();
    Product product = fp.getProduct(id); %>
    <h1 style="width: 100%; text-align: center">Product</h1>

    <table>
      <tbody>
        <tr>
          <th>Product id</th>
          <th>Product name</th>
          <th>Mrp</th>
          <th>Rating</th>
          <th>Supplier name</th>
          <th>quantity</th>
        </tr>

        <tr>
          <td><%= product.getPid() %></td>
          <td><%= product.getProductName() %></td>
          <td><%= product.getMrp() %></td>
          <td><%= product.getRating() %></td>
          <td><%= product.getSupplierName() %></td>
          <td><%= product.getQuantity() %></td>
        </tr>
      </tbody>
    </table>
    <a href="/sales"><button>back</button></a>
  </body>
</html>
