<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"  uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Vendors - Wedding Planner</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background: #fdf2f8; min-height: 100vh; }
        .navbar-brand { font-weight: 700; color: #be185d !important; }
        .table-card {
            border: none;
            border-radius: 16px;
            box-shadow: 0 4px 20px rgba(190, 24, 93, 0.08);
            overflow: hidden;
        }
        .badge-premium { background: #be185d; }
        .badge-standard { background: #6b7280; }
        .btn-danger { background: #be185d; border-color: #be185d; }
        .btn-danger:hover { background: #9d174d; border-color: #9d174d; }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg bg-white shadow-sm">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/">Wedding Planner</a>
        <div class="ms-auto">
            <a href="${pageContext.request.contextPath}/addVendor"
               class="btn btn-danger btn-sm">+ Add Vendor</a>
        </div>
    </div>
</nav>

<div class="container py-5">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h3 class="m-0">All Vendors</h3>
    </div>

    <%-- Status messages --%>
    <c:if test="${param.msg eq 'added'}">
        <div class="alert alert-success">Vendor added successfully.</div>
    </c:if>
    <c:if test="${param.msg eq 'updated'}">
        <div class="alert alert-success">Vendor updated successfully.</div>
    </c:if>
    <c:if test="${param.msg eq 'deleted'}">
        <div class="alert alert-warning">Vendor deleted.</div>
    </c:if>
    <c:if test="${param.msg eq 'notfound'}">
        <div class="alert alert-danger">Vendor not found.</div>
    </c:if>

    <div class="card table-card mb-4">
        <div class="card-body">
            <form method="get" action="${pageContext.request.contextPath}/listVendors"
                  class="row g-2">
                <div class="col-md-9">
                    <input type="text" name="keyword" value="${keyword}"
                           class="form-control"
                           placeholder="Search by name, service, or company...">
                </div>
                <div class="col-md-3 d-flex gap-2">
                    <button type="submit" class="btn btn-danger flex-grow-1">Search</button>
                    <a href="${pageContext.request.contextPath}/listVendors"
                       class="btn btn-outline-secondary">Reset</a>
                </div>
            </form>
        </div>
    </div>

    <div class="card table-card">
        <div class="table-responsive">
            <table class="table table-hover m-0 align-middle">
                <thead class="table-light">
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Service</th>
                        <th>Company</th>
                        <th>Category</th>
                        <th class="text-end">Base Price</th>
                        <th class="text-end">Final Fee</th>
                        <th>Availability</th>
                        <th class="text-end">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="v" items="${vendors}">
                        <tr>
                            <td><small class="text-muted">${v.id}</small></td>
                            <td>
                                <strong>${v.name}</strong><br>
                                <small class="text-muted">${v.email}</small>
                            </td>
                            <td>${v.serviceType}</td>
                            <td>${v.company}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${v.category eq 'Premium'}">
                                        <span class="badge badge-premium">Premium</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge badge-standard">Standard</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td class="text-end">
                                LKR <fmt:formatNumber value="${v.basePrice}"
                                                     type="number" minFractionDigits="2"/>
                            </td>
                            <td class="text-end">
                                <strong>
                                    LKR <fmt:formatNumber value="${v.serviceFee}"
                                                         type="number" minFractionDigits="2"/>
                                </strong>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${v.availability eq 'Available'}">
                                        <span class="badge bg-success">Available</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge bg-secondary">${v.availability}</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td class="text-end">
                                <a class="btn btn-sm btn-outline-primary"
                                   href="${pageContext.request.contextPath}/editVendor?id=${v.id}">
                                    Edit
                                </a>
                                <a class="btn btn-sm btn-outline-danger"
                                   href="${pageContext.request.contextPath}/deleteVendor?id=${v.id}"
                                   onclick="return confirm('Delete this vendor?');">
                                    Delete
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty vendors}">
                        <tr>
                            <td colspan="9" class="text-center text-muted py-4">
                                No vendors found.
                                <a href="${pageContext.request.contextPath}/addVendor">Add one</a>.
                            </td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>
