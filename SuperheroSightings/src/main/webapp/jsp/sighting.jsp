<%-- 
    Document   : sighting
    Created on : Dec 31, 2018, 3:35:48 PM
    Author     : Dan's Laptop
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Sightings</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body background="${pageContext.request.contextPath}/backgrounds/Superman.jpg">
        <nav class="navbar navbar-default navbar-static-top bg-info">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="navigation">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/">SuperHero Sightings</a>
                    <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <p>Hello : ${pageContext.request.userPrincipal.name}
                        | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
                    </p>
                        </c:if>
                </div>
                <div class="collapse navbar-collapse" id="naviagation">
                    <ul class="nav navbar-nav">
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/">Home</a></li>
                        <li class="active"><a class="nav-link" href="${pageContext.request.contextPath}/sighting">Sightings</a></li>
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/person">Superheroes and Supervillains</a></li>
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/location">Locations</a></li>
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/organization">Organizations</a></li>
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/superpower">Superpowers</a></li>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <li role="presentation">
                            <a href="${pageContext.request.contextPath}/displayUserList">
                                User Admin
                            </a>
                        </li>                        
                    </sec:authorize>
                    </ul>
                        <a href="${pageContext.request.contextPath}/reportSighting" class="btn btn-success navbar-btn navbar-right">Report Sighting</a>
                </div>
            </div>
        </nav>
                
        <div class="container" style="background-color: aliceblue;">
            <h2 class="text-center">Sightings</h2>
            <div class="table-responsive">
                <table class="table table-bordered table-hover table-striped">
                    <thead>
                        <tr>
                            <th>Date</th>
                            <th>Person</th>
                            <th>Location Name</th>
                        </tr>                            
                    </thead>
                    <tbody>
                        <c:forEach var="sighting" items="${sightings}">
                            <tr>
                                <td><a href="getSightingDetails/${sighting.sightingsID}">${sighting.sightingsDate}</a></td>
                                <td><a href="getPersonDetails/${sighting.person.personID}">${sighting.person.personName}</a></td>
                                <td><a href="getLocationDetails/${sighting.location.locationID}">${sighting.location.locationName}</a></td>
                                <td>
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <a href="deleteSighting/${sighting.sightingsID}">Delete</a> 
                                    <a href="editSighting/${sighting.sightingsID}">Edit</a>
                                    </sec:authorize>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>        
            
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
