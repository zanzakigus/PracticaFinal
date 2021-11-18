<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: zanzakigus
  Date: 08/10/21
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <%@include file="../shared/redirect.jsp" %>
    <title>Ver Categoria</title>
    <%@include file="../shared/resources.jsp"%>
    <link href="estilos/general.css" rel="stylesheet">
</head>
<body class="body">
<div class="container">
    <%@include file="../shared/navbar.jsp"%>

    <section class=" section d-flex align-items-center">
        <div class="mb-3 form-div form-mediano">
            <h3 class="title">Detalle de producto</h3>
            <table class="table table-striped">
                <tbody>
                <tr>
                    <td> ID:</td>
                    <td><c:out value="${producto.getEntidad().getIdProducto()}"/></td>
                </tr>
                <tr>
                    <td>Nombre de la categoría:</td>
                    <td><c:out value="${producto.getEntidad().getNombreProducto()}"/></td>
                </tr>
                <tr>
                    <td> Descripcion de la categoría:</td>
                    <td><c:out value="${producto.getEntidad().getDescripcionProducto()}"/></td>
                </tr>
                <tr><td> Precios: </td>
                    <td> <c:out value="${producto.getEntidad().getPrecioProducto()}"/> </td>
                </tr>
                <tr><td> Existencia: </td>
                    <td> <c:out value="${producto.getEntidad().getExistenciaProducto()}"/> </td>
                </tr>
                <tr><td> ID de la categoría: </td>
                    <td> <a href="CategoriaController?accion=ver&id=<c:out value="${producto.getEntidad().getIdCategoria()}"/>" type="button" class="btn btn-warning"><c:out value="${producto.getEntidad().getIdCategoria()}"/> </a> </td>
                </tr>
                </tbody>
            </table>
        </div>
    </section>
    <a class="link-success" href="CategoriaController?accion=listadoDeCategorias">
        <div class="alert alert-success" role="alert">
            Dar click para regresar a Lista de Productos
        </div>
    </a><a>
</a></div>
<a>


</a></body>
</html>
