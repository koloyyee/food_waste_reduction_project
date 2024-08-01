<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%-- <link href="${contextPath}/resource/bootstrap.min.css" rel="stylesheet">  --%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="author" content="Adam, Tony, Josh, David">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/global.css"
          type="text/css"/>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
            crossorigin="anonymous">
    <title>Food Waste Reduction Project</title>
</head>
<body>
<main id="index-main">
    <section id="welcome-message">
        <img src="${pageContext.request.contextPath}/asset/logo_trans.png"
             id="index-logo"/>
    </section>
    <c:import url="/fragments/login_form.jsp"/>
		<a href="${pageContext.request.contextPath}/">Home</a>
    <footer class="mt-5 text-center">

        <h3>Welcome to the Food Waste Reduction Project!</h3>
        <h4>Let's work together to reduce food waste.</h4>
    </footer>
</main>
</body>
</html>
