<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create user</title>
        <link rel="stylesheet" href="<c:url value='/styles/style.css' />" />

</head>
<body>
    
<div class="mwb-form-main-wrapper">

	<div  class="mwb-form-main-container">
		<%String path =  (String) request.getAttribute("path"); %>		
			<h1>Create <%=path.substring(0, 1).toUpperCase() + path.substring(1,path.length() - 1)%></h1>
	
			<form action="<%=path%>/add" data-path="<%=path%>" id="createform" method="post" onSubmit="return checkAllFields()"  >
			<%
				String[] fields = path.equals("books") 
                   ? new String[]{"name", "author", "price"} 
                   : new String[]{"name", "department", "designation"};

				String message = (String) request.getAttribute("message");
				
				
			%>
			 
		<% if (message != null) { %>
    	<p class="<%= "something went wrong.please try again later".equals(message) ? "error" : "success" %> text-center" id="message"><%= message %></p>
		<% } %>

			<% for(String field : fields){ %>
			<div class="mwb-form-group">
					<input type="text" class="mwb-form-control" placeholder="Enter your <%=field%>*" value="" name="<%=field%>" id="<%=field%>">
					<div class="mwb-form-error" id="<%=field%>-error"></div>
				</div>
			<% } %>	
				<div class="mwb-form-group flex">
				<a href="/bookstore" style="width: 100%;"> <button type="button" class="mwb-form-submit-btn submit-back-btn" >Back</button></a>	
					<button type="submit"  class="mwb-form-submit-btn" >Submit</button>
				</div>
			</form>
		</div>
	</div>
<script src="<c:url value='/js/index.js' />"></script>
</body>
</html>