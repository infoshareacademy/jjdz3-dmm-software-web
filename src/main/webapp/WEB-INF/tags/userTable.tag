<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="allUsers" type="java.util.List"
              required="true" %>

<p><b>Table of users</b></p>
<table style="width:100%">
    <tr>
        <th>userId</th>
        <th>login</th>
        <th>roleIsAdmin</th>
        <th>created</th>
    </tr>
    <c:forEach var="user" items="${allUsers}">
        <tr>
            <th>${user.id}</th>
            <th>${user.login}</th>
            <th>${user.admin}</th>
            <th>TODO</th>
        </tr>
    </c:forEach>
</table>

