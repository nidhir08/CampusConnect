<%--
  Created by IntelliJ IDEA.
  User: nidhirawat
  Date: 30/04/25
  Time: 1:48 am
  To change this template use File | Settings | File Templates.
--%>
<header class="header" style="background-color: #041538;  color: white;">
  <div class="admin-header">
    <h3 class="greet">Welcome, Admin!</h3>
    <a href="${pageContext.request.contextPath}/admin/LogoutServlet" class="logout">Logout</a>

  </div>
</header>

<style>

  .logout{
    background-color: #721c24;
    padding: 3px;
    color: white;


  }
  .greet{
    margin-left: 20px;
    margin-right: 800px;
  }
  .admin-header{
    display: flex;
    justify-content: space-between;
  }
</style>