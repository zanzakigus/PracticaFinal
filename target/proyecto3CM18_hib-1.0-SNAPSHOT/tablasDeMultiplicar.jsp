<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="shared/redirect.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP tabla de multiplicar</title>

    <%@include file="shared/resources.jsp" %>
    <link href="estilos/estilos.css" rel="stylesheet">
    <link href="estilos/general.css" rel="stylesheet">

</head>
<body class="body">
<div class="container">
    <%@include file="shared/navbar.jsp" %>
    <section class=" section d-flex align-items-center">
        <div class="mb-3 form-div">
            <h3 class="title">Tablas de multiplicar</h3>
            <table class="table table-striped">
                <%
                    for (int i = 1; i < 10; i++) {
                        out.println("<tr>");

                        for (int j = 1; j < 10; j++) {
                            out.println("<td>" + (i * j) + "</td>");

                        }

                        out.println("</tr>");

                    }
                %>
            </table>
        </div>
    </section>
</div>
</body>
<script defer type="text/javascript">
    navbar_update(1);
</script>
</html>
