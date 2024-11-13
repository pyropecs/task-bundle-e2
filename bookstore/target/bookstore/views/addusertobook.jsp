<%@ page import="com.library.models.Book,com.library.models.User,java.util.List" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create user</title>
           <link rel="stylesheet" href="<c:url value='/styles/style.css' />" />
</head>
<body>

    <div class="mwb-form-main-wrapper">
        <div class="mwb-form-main-container">
            <h1>Add Users To Book</h1>
            <form action="addusers/insert" method="post" onSubmit="return checkEmptySelected()">
                
                <%
                    String message = (String) request.getAttribute("message");
           
                %>
          <% if (message != null) { %>
    	<p class="<%= "something went wrong.please try again later".equals(message) ? "error" : "success" %> text-center" id="message"><%= message %></p>
		<% } %>
                <div class="mwb-form-group select-form no-margin">
                    <select name="bookId" id="bookselect" onchange="getUsers(this)">
                        <option value="" selected hidden disabled>Select Book</option>
                        <%
                            List<Book> books = (List<Book>) request.getAttribute("books");
                            if (books != null && !books.isEmpty()) {
                                for (Book book : books) {
                        %>
                            <option value="<%= book.getId() %>"><%= book.getName() %></option>
                        <%
                                }
                            } else {
                        %>
                            <option value="">No books available</option>
                        <%
                            }
                        %>
                    </select>
                    <div class="mwb-form-error" id="select-error">This Field Required*</div>
                </div>

                <div class="center">
                    <label>Select users to Add Book</label>
                </div>
                <div class="mwb-form-group checkboxes">
                    <%
                        List<User> users = (List<User>) request.getAttribute("users");
                        if (users != null && !users.isEmpty()) {
                            for (User user : users) {
                    %>
                        <div class="checkbox-container">
                            <input type="checkbox" disabled name="userIds" class="checkbox" id="<%= user.getId() %>" value="<%= user.getId() %>" />
                            <label class="user-list" for="<%= user.getId() %>"><%= user.getName() %></label>
                        </div>
                    <%
                            }
                        } else {
                    %>
                        <p>No users found.</p>
                    <%
                        }
                    %>
                </div>

                <div class="mwb-form-group flex">
                    <a href="/bookstore" style="width: 100%;">
                        <button type="button" class="mwb-form-submit-btn submit-back-btn">Back</button>
                    </a>
                    <button type="submit" class="mwb-form-submit-btn">Submit</button>
                </div>
            </form>
        </div>
    </div>
      <script src="<c:url value='/js/adduser.js' />"></script>
</body>
</html>
