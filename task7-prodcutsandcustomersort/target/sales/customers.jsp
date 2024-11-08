<%@ page import=" com.sales.beanclasses.Customer,com.sales.FetchCustomers" %>

<html>
<body>
<% 
FetchCustomers fc = new FetchCustomers();
Customer[] customers = fc.getCustomers();
%>
        <h1 style="width:100%; text-align: center;">Customers</h1>

<table>
        <tbody>

        
<tr>
        <th>Customer id</th>
        <th>Customer name</th>
        <th>Customer Mobile</th>
        <th>City</th>
        <th>Product id</th>
</tr>

<%
for(Customer c:customers){


%>

 <tr>
            <td><%= c.getCid() %></td>
            <td><%= c.getCustomerName()  %></td>
            <td><%= c.getCustomerMobile() %></td>
            <td><%= c.getCity() %></td>
            <td><a href='/sales/products?id=<%=c.getPid() %>' > <%= c.getPid() %> </a></td>
</tr>
<%
}
%>
</tbody>
</table>

</body>
</html>
