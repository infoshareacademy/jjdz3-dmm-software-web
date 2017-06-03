<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="user" type="com.dmmsoft.user.User" required="true" %>
<p>logged user: ${user.login}</p>
