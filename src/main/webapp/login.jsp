<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@include file="shared/resources.jsp" %>
    <link href="estilos/general.css" rel="stylesheet">
</head>
<body class="body">
<div id="pageMessages"></div>
<div class="container">
    <section class=" section d-flex align-items-center">
        <div class="mb-3 form-div">
            <h3 class="title">Login</h3>
            <form method="post" action="${pageContext.request.contextPath}/UsuarioController?accion=requestLogin">
                <label for="txtUsuario" class="form-label"> Nombre :</label>
                <input class="form-control" type="text" name="txtUsuario" id="txtUsuario"
                       placeholder="Nombre de usuario" required="required"/>

                <label for="txtPassword" class="form-label"> Descripción :</label>
                <input class="form-control" type="password" name="txtPassword" id="txtPassword"
                       placeholder="Password" required="required"/>
                <br/>
                <input class=" form-control btn btn-success" type="submit" value="Iniciar sesión" name="cmdEnviar"/>
            </form>
        </div>
    </section>

</div>
<script>
    <c:choose>
    <c:when test="${error!=null}">
    document.addEventListener("DOMContentLoaded", (event) => {
        createAlert('Opps!', 'Something went wrong', '<c:out value="${error}"/>', 'danger', false, true, 'pageMessages');
    });
    </c:when>
    </c:choose>

</script>
</body>
</html>

