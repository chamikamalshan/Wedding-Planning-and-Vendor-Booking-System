<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.Arrays,java.util.List" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%
    List<String> serviceOptions = Arrays.asList(
        "Photography","Catering","Florist","Decoration",
        "Music / DJ","Venue","Cake","Bridal Wear","Make-up");
    request.setAttribute("serviceOptions", serviceOptions);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Vendor - Wedding Planner</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background: #fdf2f8; min-height: 100vh; }
        .navbar-brand { font-weight: 700; color: #be185d !important; }
        .form-card {
            border: none;
            border-radius: 16px;
            box-shadow: 0 4px 20px rgba(190, 24, 93, 0.08);
        }
        .btn-danger { background: #be185d; border-color: #be185d; }
        .btn-danger:hover { background: #9d174d; border-color: #9d174d; }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg bg-white shadow-sm">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/">Wedding Planner</a>
        <div class="ms-auto">
            <a href="${pageContext.request.contextPath}/listVendors"
               class="btn btn-outline-danger btn-sm">View Vendors</a>
        </div>
    </div>
</nav>

<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-lg-8">
            <div class="card form-card p-4">
                <h3 class="mb-1">Edit Vendor</h3>
                <p class="text-muted">Update the details and click "Save Changes".</p>

                <c:if test="${not empty error}">
                    <div class="alert alert-danger">${error}</div>
                </c:if>

                <form method="post" action="${pageContext.request.contextPath}/editVendor">
                    <input type="hidden" name="id" value="${vendor.id}">

                    <div class="row g-3">
                        <div class="col-md-6">
                            <label class="form-label">Vendor ID</label>
                            <input type="text" class="form-control" value="${vendor.id}" disabled>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Full Name *</label>
                            <input type="text" name="name" value="${vendor.name}"
                                   class="form-control" required>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Email *</label>
                            <input type="email" name="email" value="${vendor.email}"
                                   class="form-control" required>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Phone *</label>
                            <input type="text" name="phone" value="${vendor.phone}"
                                   class="form-control" required>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Service Type *</label>
                            <select name="serviceType" class="form-select" required>
                                <c:forEach var="opt" items="${serviceOptions}">
                                    <option value="${opt}"
                                        <c:if test="${opt eq vendor.serviceType}">selected</c:if>>${opt}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Company</label>
                            <input type="text" name="company" value="${vendor.company}"
                                   class="form-control">
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Category *</label>
                            <select name="category" class="form-select" required>
                                <option value="Standard"
                                    <c:if test="${vendor.category eq 'Standard'}">selected</c:if>>
                                    Standard (5% tax)
                                </option>
                                <option value="Premium"
                                    <c:if test="${vendor.category eq 'Premium'}">selected</c:if>>
                                    Premium (20% surcharge + 5% tax)
                                </option>
                            </select>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Base Price (LKR) *</label>
                            <input type="number" name="basePrice" value="${vendor.basePrice}"
                                   min="0" step="0.01" class="form-control" required>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Availability</label>
                            <select name="availability" class="form-select">
                                <option <c:if test="${vendor.availability eq 'Available'}">selected</c:if>>Available</option>
                                <option <c:if test="${vendor.availability eq 'Booked'}">selected</c:if>>Booked</option>
                            </select>
                        </div>
                    </div>

                    <div class="mt-4 d-flex gap-2">
                        <button type="submit" class="btn btn-danger">Save Changes</button>
                        <a href="${pageContext.request.contextPath}/listVendors"
                           class="btn btn-outline-secondary">Cancel</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
