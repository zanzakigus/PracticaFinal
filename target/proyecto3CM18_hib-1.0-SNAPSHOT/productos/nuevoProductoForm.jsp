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
        <c:when test="${producto==null}">
            <title>Producto Form</title>
            <c:set var="active" value="active"/>
            <c:set var="styles" value="estilos/general.css"/>
            <c:set var="link" value="ProductoController?accion=guardar"/>
        </c:when>
        <c:otherwise>
            <title>Actualizar Producto Form</title>
            <c:set var="active" value=""/>
            <c:set var="styles" value="estilos/general.css"/>
            <c:set var="link" value="ProductoController?accion=guardar"/>
        </c:otherwise>
    </c:choose>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@include file="../shared/resources.jsp" %>
    <link href="<c:out value="${styles}"/>" rel="stylesheet">
</head>
<body class="body">
<div class="container">
    <%@include file="../shared/navbar.jsp" %>
    <section class=" section d-flex align-items-center">
        <div class="mb-3 form-div">
            <c:choose>
                <c:when test="${producto==null}">
                    <h3 class="title">Crear una nuevo producto </h3>
                </c:when>
                <c:otherwise>
                    <h3 class="title">Actualizar producto</h3>
                </c:otherwise>
            </c:choose>
            <form method="post" action="<c:out value="${link}"/>">
                <c:choose>
                    <c:when test="${producto!=null}">
                        <input value="<c:out value="${producto.getEntidad().getIdProducto()}"/>" type="hidden" id="id"
                               name="id">
                    </c:when>
                </c:choose>

                <label for="txtNombre" class="form-label"> Nombre :</label>
                <input value="<c:out value="${producto.getEntidad().getNombreProducto()}"/>" class="form-control"
                       type="text" name="txtNombre" id="txtNombre"
                       placeholder="Nombre del Producto" required="required">

                <label for="txtDescripcion" class="form-label"> Descripción :</label>
                <input value="<c:out value="${producto.getEntidad().getDescripcionProducto()}"/>" class="form-control"
                       type="text" name="txtDescripcion" id="txtDescripcion"
                       placeholder="Descripción del Producto" required="required">

                <label for="txtPrecio" class="form-label"> Precio :</label>
                <input value="<c:out value="${producto.getEntidad().getPrecioProducto()}"/>" class="form-control"
                       type="text" name="txtPrecio" id="txtPrecio" placeholder="$00.00"
                       required="required">

                <label for="txtExistencia" class="form-label"> Existencia :</label>
                <input value="<c:out value="${producto.getEntidad().getExistenciaProducto()}"/>" class="form-control"
                       type="number" name="txtExistencia" id="txtExistencia" placeholder="0"
                       required="required">

                <label for="txtCategoria" class="form-label"> Categoria :</label>
                <select class="form-select" aria-label="Default select example" name="txtCategoria" id="txtCategoria">
                    <option
                            <c:if test="${producto==null}">
                                selected
                            </c:if>
                    >Seleccionar Categoría
                    </option>
                    <c:forEach var="dto" items="${listaDeCategorias}">
                        <option value="<c:out value="${dto.getEntidad().getIdCategoria()}"/>"
                                <c:if test="${producto!=null && dto.getEntidad().getIdCategoria() == producto.getEntidad().getIdCategoria() }">
                                    selected
                                </c:if>
                        ><c:out value="${dto.getEntidad().getIdCategoria()}"/>-<c:out
                                value="${dto.getEntidad().getNombreCategoria()}"/>
                        </option>
                    </c:forEach>
                </select>

                <br/>
                <c:choose>
                    <c:when test="${producto==null}">
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
    navbar_update(4);
</script>
</html>
