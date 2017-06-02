<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title><b> Investment Revenue </b></title>


    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
        $(function () {
            $(".datePicker").datepicker({
                dateFormat: "yy-mm-dd",
                changeMonth: true,
                changeYear: true,
            }).val()
        });
    </script>
</html>

</head>
<body>
<p><b> Analysis: Investment Revenue</b></p>
<tags:userLogin user="${sessionScope.authenticatedUser}"/>
<jsp:include page="../partials/formheader.jsp"/>

<form method="post" action="../analyzer/investmentrevenue">
    <p><b>form:</b></p>
    <p>1. <input type="text" name="investmenName" style="width:170px" id="inputForm" placeholder="inv.name, e.g. USD"/>
    </p>
    <p>2. <input type="text" pattern="[0-9]*" name="capital" style="width:170px" placeholder="inv.capital, e.g. 10000"/>
    </p>
    <p>3. <input type="text" class="datePicker" name="buyDate" readonly='true' style="width:85px"
                 placeholder="buy date"/></p>
    <p>4. <input type="text" class="datePicker" name="sellDate" readonly='true' style="width:85px"
                 placeholder="sell date"/></p>
    <p><input type="checkbox" name="isFavourite"/> add to favourites</p>
    <p>
        <button type="submit">Submit!</button>
    </p>
</form>

<tags:analysisResult displayWrapper="${displayWrapper}"/>

<jsp:include page="../partials/footer.jsp"/>
</body>
</html>