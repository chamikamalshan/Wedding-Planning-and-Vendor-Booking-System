<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Wedding Planning and Vendor Booking System</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #fdf2f8 0%, #fce7f3 100%);
            min-height: 100vh;
        }
        .hero {
            padding: 80px 0 40px;
            text-align: center;
        }
        .hero h1 {
            color: #831843;
            font-weight: 700;
        }
        .hero p {
            color: #9d174d;
            font-size: 1.15rem;
        }
        .feature-card {
            border: none;
            border-radius: 16px;
            box-shadow: 0 4px 20px rgba(190, 24, 93, 0.08);
            transition: transform .2s ease, box-shadow .2s ease;
        }
        .feature-card:hover {
            transform: translateY(-4px);
            box-shadow: 0 10px 30px rgba(190, 24, 93, 0.15);
        }
        .icon-circle {
            width: 64px; height: 64px;
            border-radius: 50%;
            display: inline-flex;
            align-items: center;
            justify-content: center;
            background: #fce7f3;
            color: #be185d;
            font-size: 1.6rem;
            margin-bottom: 12px;
        }
        .navbar-brand { font-weight: 700; color: #be185d !important; }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg bg-white shadow-sm">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/">
            Wedding Planner
        </a>
        <div class="ms-auto">
            <a href="${pageContext.request.contextPath}/listVendors"
               class="btn btn-outline-danger btn-sm me-2">View Vendors</a>
            <a href="${pageContext.request.contextPath}/addVendor"
               class="btn btn-danger btn-sm">Add Vendor</a>
        </div>
    </div>
</nav>

<section class="hero">
    <div class="container">
        <h1>Plan Your Perfect Wedding</h1>
        <p class="mt-3">
            Manage photographers, caterers, florists and every other vendor in one place.
        </p>
        <div class="mt-4">
            <a href="${pageContext.request.contextPath}/listVendors"
               class="btn btn-danger btn-lg me-2">Browse Vendors</a>
            <a href="${pageContext.request.contextPath}/addVendor"
               class="btn btn-outline-danger btn-lg">Register a Vendor</a>
        </div>
    </div>
</section>

<section class="container py-5">
    <div class="row g-4">
        <div class="col-md-4">
            <div class="card feature-card p-4 h-100 text-center">
                <div class="icon-circle">+</div>
                <h5>Add Vendors</h5>
                <p class="text-muted mb-0">
                    Register new wedding service providers with category and pricing.
                </p>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card feature-card p-4 h-100 text-center">
                <div class="icon-circle">&#128269;</div>
                <h5>Search &amp; Browse</h5>
                <p class="text-muted mb-0">
                    Find the right vendor by name, service type, or company.
                </p>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card feature-card p-4 h-100 text-center">
                <div class="icon-circle">&#9998;</div>
                <h5>Update &amp; Manage</h5>
                <p class="text-muted mb-0">
                    Edit vendor details or remove vendors no longer available.
                </p>
            </div>
        </div>
    </div>
</section>

<footer class="text-center py-4 text-muted">
    <small>SE1020 - Object Oriented Programming Assignment</small>
</footer>

</body>
</html>
