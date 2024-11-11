<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Book Store</title>
    <link rel="stylesheet" href="<c:url value='/styles/style.css' />" />
  </head>
  <body>
    <div class="wrap">
      <div class="container">
        <h1 class="booktitle">Book Store Application</h1>
        <div class="btn-container">
          <a href="users"><button class="btn">Create User</button></a>

          <a href="books"><button class="btn">Create Book</button></a>
          <a href="addusers"><button class="btn">Add User Book</button></a>
          <a href="view"><button class="btn">View User List</button></a>
        </div>
      </div>
    </div>
  </body>
</html>
