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
    <%@include file="../shared/levelRedirect.jsp" %>
    <c:choose>
        <c:when test="${categoria==null}">
            <title>Categor&iacute;a Form</title>
            <c:set var = "active"  value="active" />
            <c:set var = "styles"  value="estilos/general.css" />
            <c:set var = "link"  value="CategoriaController?accion=guardar" />
        </c:when>
        <c:otherwise>
            <title>Actualizar categoría Form</title>
            <c:set var = "active"  value="" />
            <c:set var = "styles"  value="estilos/general.css" />
            <c:set var = "link"  value="CategoriaController?accion=guardar" />
        </c:otherwise>
    </c:choose>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@include file="../shared/resources.jsp"%>
    <link href="<c:out value="${styles}"/>" rel="stylesheet">
</head>
<body class="body">
<div class="container">
    <%@include file="../shared/navbar.jsp"%>
    <section class=" section d-flex align-items-center">
        <div class="mb-3 form-div">
            <c:choose>
                <c:when test="${categoria==null}">
                    <h3 class="title">Crear una nueva categoría </h3>
                </c:when>
                <c:otherwise>
                    <h3 class="title">Actualizar categoría</h3>
                </c:otherwise>
            </c:choose>
            <form method="post" action="<c:out value="${link}"/>">
                <c:choose>
                    <c:when test="${categoria!=null}">
                        <input value="<c:out value="${categoria.getEntidad().getIdCategoria()}"/>" type="hidden" id="id" name="id">
                    </c:when>
                </c:choose>
                <label for="txtNombre" class="form-label"> Nombre :</label>
                <input value="<c:out value="${categoria.getEntidad().getNombreCategoria()}"/>" class="form-control" type="text" name="txtNombre" id="txtNombre"
                       placeholder="Nombre de la Categoría" required="required"/>

                <label  for="txtDescripcion" class="form-label"> Descripción :</label>
                <input value="<c:out value="${categoria.getEntidad().getDescripcionCategoria()}"/>" class="form-control" type="text" name="txtDescripcion" id="txtDescripcion"
                       placeholder="Descripción de la Categoría" required="required"/>
                <br/>
                <c:choose>
                    <c:when test="${categoria==null}">
                        <input class=" form-control btn btn-success" type="submit" value="Guardar" name="cmdEnviar"/>
                    </c:when>
                    <c:otherwise>
                        <input class=" form-control btn btn-success" type="submit" value="Actualizar" name="cmdEnviar"/>
                    </c:otherwise>
                </c:choose>

            </form>
        </div>
    </section>

</div>
</body>
<script defer type="text/javascript">
    navbar_update(2);
</script>
</html>
