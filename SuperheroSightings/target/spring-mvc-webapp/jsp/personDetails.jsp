<%-- 
    Document   : personDetails
    Created on : Jan 3, 2019, 2:55:45 PM
    Author     : Dan's Laptop
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Super Details</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/modals.css" rel="stylesheet">
    </head>
    <body>
        <nav class="navbar navbar-default navbar-static-top bg-info">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="navigation">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/">SuperHero Sightings</a>
                </div>
                <div class="collapse navbar-collapse" id="naviagation">
                    <ul class="nav navbar-nav">
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/">Home</a></li>
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/sighting">Sightings</a></li>
                        <li class="active"><a class="nav-link" href="${pageContext.request.contextPath}/person">Superheroes and Supervillains</a></li>
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/location">Locations</a></li>
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/organization">Organizations</a></li>
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/superpower">Superpowers</a></li>
                    </ul>
                        <a href="${pageContext.request.contextPath}/reportSighting" class="btn btn-success navbar-btn navbar-right">Report Sighting</a>
                </div>
            </div>
        </nav>
        <div class="container">
            <h2 class="text-center">Person Details</h2>
            <div class="row">
                <div class="col-sm-4">
                    <h2 class="toggleHeader"></h2>
                </div>
            </div>
            <div id="showPeopleDiv">
                <div class="table-responsive">
                    <table class="table table-bordered table-hover table-striped">
                        <thead>
                            <tr>
                                <th>Person Name</th>
                                <th>Person Description</th>
                                <th>Are they a hero?</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td><c:out value="${person.personName}"></c:out></td> 
                                <td><c:out value="${person.personDescription}"></c:out></td> 
                                <td><c:out value="${person.isHero ? 'Yes' : 'No'}"></c:out></td> 
                            </tr>  
                        </tbody>
                    </table>
                            
                    <table class="table table-bordered table-hover table-striped">
                        <thead>
                            <tr>
                                <th>This person has these superpowers</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="currentSuperpower" items="${superpowers}">
                            <tr>
                                <td><a href="${pageContext.request.contextPath}/getSuperpowerDetails/${currentSuperpower.superpowerID}"><c:out value="${currentSuperpower.superpowerName}"></c:out></a></td>    
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                            
                    <table class="table table-bordered table-hover table-striped">
                        <thead>
                            <tr>
                                <th>This person belongs to these organizations</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="currentOrganization" items="${organizations}">
                            <tr>
                                <td><a href="${pageContext.request.contextPath}/getOrganizationDetails/${currentOrganization.organizationID}"><c:out value="${currentOrganization.orgName}"></c:out></a></td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                   <table class="table table-bordered table-hover table-striped">
                        <thead>
                            <tr>
                                <th>This locations this person has been spotted</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="currentLocation" items="${locations}">
                            <tr>
                                <td><a href="${pageContext.request.contextPath}/getLocationDetails/${currentLocation.locationID}"><c:out value="${currentLocation.locationName}"></c:out></a></td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>


                    
                </div>
            </div>
        </div>

                 
        
        
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
