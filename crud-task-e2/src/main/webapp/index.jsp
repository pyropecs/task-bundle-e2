<%@ page import=" com.customers.beanclasses.Customer,java.util.List, com.customers.FetchCustomers" %>

<html>
  <body>
        <%! private List<Customer> customers; %>
        

    <%  
      try{
       
        FetchCustomers fetchCustomersInstance = new FetchCustomers();
        customers = fetchCustomersInstance.getCustomers();
   
      }catch(Exception e){
        System.out.println("something went wrong in customers jsp");
        System.out.println(e.getMessage());
      }
 
    
    %>
    
    
    <h1 style="width: 100%; text-align: center">Customers</h1>
      <a href="add">
        <button>
                Add
        </button>
        </a>
    <table>
      <thead>
        <tr>
          <th>Customer id</th>
          <th>Customer name</th>
          <th>Customer age</th>
          <th>Customer rating</th>
          <th>Edit</th>
          <th>Delete</th>
        </tr>
      </thead>
      <tbody>
        <%
        if(customers.isEmpty()){
         %>  
        <tr>
         <td>no customers found</td>
        </tr>
       
        <% } %>

        <% for(Customer customer:customers){ %>

        <tr>

          <td><%= customer.getId() %></td>
          <td><%= customer.getName() %></td>
          <td><%= customer.getAge() %></td>
          <td><%= customer.getRating() %></td>
        <td><a href="edit?id=<%=customer.getId() %>" ><button>edit</button></a></td>
        <td><a href="delete?id=<%=customer.getId() %>" onclick="return confirm('are you sure you want to delete <%= customer.getName() %> ? ')"><button>delete</button></a></td>
        </tr>
        <% } %>

      </tbody>
    </table>

  </body>
</html>