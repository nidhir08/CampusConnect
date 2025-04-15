<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Add Society - Campus Connect</title>
  <link rel="stylesheet" href="../css/styles.css">
</head>
<body>
<div class="container">
  <jsp:include page="../include/header.jsp" />

  <main class="main-content">
    <h2>Add New Society</h2>

    <% if (request.getAttribute("message") != null) { %>
    <div class="success-message">
      <%= request.getAttribute("message") %>
    </div>
    <% } %>

    <form action="<%=request.getContextPath()%>/AddSocietyServlet" method="post">
      <div class="form-group">
        <label for="name">Society Name</label>
        <input type="text" id="name" name="name" required>
      </div>

      <div class="form-group">
        <label for="description">Description</label>
        <textarea id="description" name="description" rows="4" required></textarea>
      </div>

      <div class="form-group">
        <label for="logoUrl">Logo URL (optional)</label>
        <input type="text" id="logoUrl" name="logoUrl">
      </div>

      <div class="form-actions">
        <button type="submit" class="btn btn-primary">Add Society</button>
      </div>
    </form>
  </main>

  <jsp:include page="../include/footer.jsp" />
</div>
</body>
</html>