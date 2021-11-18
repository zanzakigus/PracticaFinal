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
    <%@include file="../shared/redirect.jsp" %>
    <c:set var="styles" value="estilos/general.css"/>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@include file="../shared/resources.jsp" %>
    <link href="<c:out value="${styles}"/>" rel="stylesheet">
</head>
<body class="body">
<div id="pageMessages"></div>
<div class="container">
    <%@include file="../shared/navbar.jsp" %>
    <section class=" section d-flex align-items-center">
        <div class="mb-3 form-div">
            <h3 class="title">Enviar correo electronico</h3>
            <form method="post" action="UsuarioController?accion=enviarEmail">
                <label for="txtDestinatario" class="form-label"> Destinatario :</label>
                <select class="form-select" aria-label="Default select example" name="txtDestinatario"
                        id="txtDestinatario">
                    <option selected>Seleccionar destinatario</option>
                    <c:forEach var="dto" items="${listaDeUsuarios}">
                        <option value="<c:out value="${dto.getEntidad().getEmail()}"/>">
                            <c:out value="${dto.getEntidad().getEmail()}"/>
                        </option>
                    </c:forEach>
                </select>
                <label for="txtAsunto" class="form-label"> Asunto :</label>
                <input class="form-control"
                       type="text" name="txtAsunto" id="txtAsunto" placeholder="Asunto del correo"
                       required="required">
                <label for="txtMensaje" class="form-label"> Mensaje:</label>
                <textarea  class="form-control" name="txtMensaje" id="txtMensaje"
                          placeholder="Mensaje" required="required"></textarea>

                <br/>
                <input class=" form-control btn btn-success" type="submit" value="Enviar" name="cmdEnviar"/>
            </form>
        </div>
    </section>

</div>
</body>
<script defer type="text/javascript">

    <c:if test="${sessionScope.tipo!='admin'}">
    navbar_update(4);
    </c:if>
    <c:if test="${sessionScope.tipo=='admin'}">
    navbar_update(8);
    </c:if>

    <c:if test="${msj!=null && tipoR}">
    document.addEventListener("DOMContentLoaded", (event) => {
        createAlert('Succeed!', 'Todo bien', '<c:out value="${msj}"/>', 'success', false, true, 'pageMessages');
    });
    </c:if>

    <c:if test="${msj!=null && !tipoR}">
    document.addEventListener("DOMContentLoaded", (event) => {
        createAlert('Opps!', 'Something went wrong', '<c:out value="${msj}"/>', 'danger', false, true, 'pageMessages');
    });
    </c:if>
</script>
</html>
