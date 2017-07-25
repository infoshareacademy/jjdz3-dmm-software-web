<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="appMode" type="com.dmmsoft.webconfiguration.AppMode" required="true" %>
<c:choose>
    <c:when test="${appMode.slave}">
        <P><b><font color="red">WARNING! APP SLAVE MODE! </font> Customers Service Disabled.</b></P><br>
    </c:when>
</c:choose>

