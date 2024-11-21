<%@ page import="java.util.List,java.util.Set,java.util.Iterator,com.library.models.Book,com.library.models.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>View Books</title>
    <link rel="stylesheet" href="<c:url value='/styles/style.css' />" />
</head>
<body>
    <div class="wrap align-center column">
    <div class="bgc justify-right">
  <div class="contain">
  
    <input class="inp" name="text" type="text" id="book-input" placeholder="search book name" />

  </div>
</div>
        <table>
            <thead>
                <tr>
                    <th>Book Name</th>
                    <th>Users</th>
                </tr>
            </thead>
            <tbody id="book-names">
                <%
                    List<Book> books = (List<Book>) request.getAttribute("books");
                    if ( !books.isEmpty()) {
                        for (Book book : books) {
                %>

                
                <tr>
                 
                    <td><%= book.getName() %></td>
                 
                    <td>
                        <%
                            Set<User> users = book.getUsers();
                            if (users.isEmpty()) {
                        %>
                            No users found
                        <%
                            } else {
                                Iterator<User> iterator = users.iterator();
                                while (iterator.hasNext()) {
                                    User user = iterator.next();
                        %>
                                    <%= user.getName() %><%= iterator.hasNext() ? ", " : "" %>
                        <%
                                }
                            }
                        %>
                    </td>
                
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td>No Books found</td>
                    <td></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <a href="/bookstore" class="back-btn-link">
            <button class="btn back-btn">Back</button>
        </a>
    </div>
     <script src="<c:url value='/js/viewTable.js' />"></script>
</body>
</html>
