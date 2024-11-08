<%@ page import="com.customers.beanclasses.Customer" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>create customer</title>
  </head>
  <body>
    <%! private Customer customer; %> <% customer = (Customer)
    request.getAttribute("customer"); %>

    <form
      action="edit?id=<%=customer.getId() %>"
      onsubmit="return emptyValidation()"
      method="post"
    >
      <div class="">
        <label for="">enter name</label>
        <input
          type="text"
          name="customer_name"
          value="<%= customer.getName() %>"
        />
        <span id="name-error"></span>
      </div>
      <div class="">
        <label for="">enter age</label>
        <input
          type="text"
          name="customer_age"
          oninput="return ValidateForm()"
          value="<%= customer.getAge() %>"
        />
        <span id="age-error"></span>
      </div>
      <div class="">
        <label for="">enter rating</label>
        <input
          type="text"
          name="customer_rating"
          oninput="return ValidateForm()"
          value="<%= customer.getRating() %>"
        />
        <span id="rating-error"></span>
      </div>

      <button type="submit">update</button>
    </form>
    <script type="text/javascript" src="js/validate.js"></script>
  </body>
</html>
