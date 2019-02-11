<%-- 
    Document   : report
    Created on : Dec 31, 2018, 3:37:04 PM
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
        <title>Report A Sighting</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body background="${pageContext.request.contextPath}/backgrounds/Wolverine.jpg">
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
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/person">Superheroes and Supervillains</a></li>
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/location">Locations</a></li>
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/organization">Organizations</a></li>
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/superpower">Superpowers</a></li>
                    </ul>
                        <a href="${pageContext.request.contextPath}/reportSighting" class="btn btn-success navbar-btn navbar-right">Report Sighting</a>
                </div>
            </div>
        </nav>
        <div class="container">    
            <h2 class="text-center">Report Sighting</h2>
            <form action="${pageContext.request.contextPath}/addSighting" method="post">                        
                <div class="form-group">
                    <label for="sightingDate">Date:</label>
                    <input name="sightingDate" type="date" class="form-control" value="${today}" required/>                            
                </div>    
                <div class="form-group">
                    <label for="personID">Person:</label>
                    <select class="form-control" name="personID" required>
                        <c:forEach var="person" items="${people}">
                            <option value="${person.personID}">${person.personName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="locationID">Location:</label>
                    <select class="form-control" name="locationID" required>
                        <c:forEach var="location" items="${locations}">
                            <option value="${location.locationID}">${location.locationName}, ${location.locationAddress}</option>
                        </c:forEach>
                    </select>
                </div>                      
                <button type="submit" class="btn btn-primary">Submit</button> 
            </form>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
