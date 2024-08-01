<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="author" content="Adam, Tony, Josh, David">
    <title>Food Waste Reduction Project</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"> 
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css" type="text/css" />
</head>
<body>
    <div class="container"> 
        <div class="row mt-5"> 
            <div class="col-md-8 offset-md-2 text-center py-4"> 
                <img src="${pageContext.request.contextPath}/asset/logo_trans.png" class="img-fluid w-50 mb-4" alt="Food Waste Reduction Project Logo" /> 
                <h2>Welcome to the Food Waste Reduction Project!</h2>
                <p>Our mission is to connect consumers, retailers, and charitable organizations to minimize food waste and ensure surplus food reaches those in need. </p>
                <p>By working together, we can create a more sustainable and equitable food system.</p>

                <div class="d-grid gap-2 d-md-flex justify-content-md-center mt-4"> 
                    <a href="${pageContext.request.contextPath}/pages/register.jsp" class="btn btn-primary">Register</a>
                    <a href="${pageContext.request.contextPath}/pages/login.jsp" class="btn btn-secondary">Login</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>