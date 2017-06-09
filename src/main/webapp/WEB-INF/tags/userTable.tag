<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="allUsers" type="java.util.List"
              required="true" %>

<p><b>Table of users</b></p>
<table style="width:100%">
    <tr>
        <th>Id</th>
        <th>login</th>
        <th>role: Admin</th>
        <th>Account Creation Time</th>
        <th>Last Login Time</th>
        <th>Last Update Time</th>
    </tr>
    <c:forEach var="user" items="${allUsers}">
        <tr>
            <th>${user.id}</th>
            <th>${user.login}</th>
            <th>${user.admin}</th>
            <th>${user.creationDateTime}</th>
            <th>${user.lastLoginDateTime}</th>
            <th>${user.lastUpdateDateTime}</th>
        </tr>
    </c:forEach>
</table>

