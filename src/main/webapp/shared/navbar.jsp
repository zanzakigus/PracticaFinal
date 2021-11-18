<%@page contentType="text/html" pageEncoding="UTF-8" %>
<div>
    <nav class="navbar navbar-expand-lg navbar-light">
        <div class="container-fluid">

            <!-- <a class="navbar-brand" href="index.html">Navbar</a> -->
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="UsuarioController?accion=index">Inicio</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="tablasDeMultiplicar.jsp">Tabla</a>
                    </li>
                    <c:if test="${sessionScope.tipo=='admin'}">
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="CategoriaController?accion=nuevo">Crear nueva categoría</a>
                        </li>
                    </c:if>

                    <li class="nav-item">
                        <a class="nav-link" href="CategoriaController?accion=listadoDeCategorias">Listado de categorías</a>
                    </li>
                    <c:if test="${sessionScope.tipo=='admin'}">
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="ProductoController?accion=nuevo">Crear nueva producto</a>
                        </li>
                    </c:if>

                    <li class="nav-item">
                        <a class="nav-link" href="ProductoController?accion=listadoDeProductos">Listado de productos</a>
                    </li>
                    <c:if test="${sessionScope.tipo=='admin'}">
                        <li class="nav-item">
                            <a class="nav-link" href="UsuarioController?accion=listadoDeUsuarios">Usuarios</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="UsuarioController?accion=nuevo">Crear usuario</a>
                        </li>
                    </c:if>
                    <li class="nav-item">
                        <a class="nav-link" href="UsuarioController?accion=crearEmail">Enviar email</a>
                    </li>

                </ul>
                <form method="post"  class="log-out d-flex" action="UsuarioController?accion=logOut">
                    <button class="btn btn-outline-success" type="submit">Cerrar sesión</button>
                </form>

            </div>
        </div>
    </nav>
</div>