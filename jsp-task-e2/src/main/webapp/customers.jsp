<%@ page import=" com.sales.beanclasses.Customer,com.sales.FetchData,java.util.List" %>

<html>
  <body>
        <%! private List<Customer> customers; %>
        <% String field = (String) request.getAttribute("field"); 
        String order = (String)  request.getAttribute("order");
      
        %>

    <% FetchData<Customer> fetchDataInstance = new FetchData<>(); 
      try{
        customers = fetchDataInstance.fetchCollection("customers", field, order);
      
      }catch(Exception e){
        System.out.println(e.getMessage() + e.getStackTrace());
      }
 
    
    %>
    
    
    <h1 style="width: 100%; text-align: center">Customers</h1>

    <table>
      <tbody>
        <tr>
          <th>Customer id</th>
          <th>Customer name</th>
          <th>Customer age</th>
        </tr>

        <% for(Customer c:customers){ %>

        <tr>
          <td><%= c.getId() %></td>
          <td><%= c.getName() %></td>
          <td><%= c.getAge() %></td>
        </tr>
        <% } %>
      </tbody>
    </table>
    <form action="customers" method="get">
      <select name="order" id="">
        <option <%= field !=null && field.equals("customer_name")  ? "selected":"" %> selected value="customer_name">name</option>
        <option <%= field !=null && field.equals("customer_age")  ? "selected":"" %> value="customer_age">age</option>
      </select>

      <input type="submit" name="sort" value="asc"/>
      <input type="submit" name="sort" value="desc" />
    </form>
  </body>
</html>
