<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>

    <c:choose>
    <c:when test="${sessionScope.usuario!=null && sessionScope.tipo!='admin'}">
    //window.location.href="/UsuarioController?accion=login";
    window.location.replace("/UsuarioController?accion=login");
    //window.location.replace("/proyecto3CM18_hib/UsuarioController?accion=index");
    </c:when>
    </c:choose>

</script>