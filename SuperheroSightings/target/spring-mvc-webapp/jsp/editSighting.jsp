<%-- 
    Document   : editSighting
    Created on : Jan 2, 2019, 4:22:26 PM
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
        <title>Edit Sighting</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
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
                        <li class="active"><a class="nav-link" href="${pageContext.request.contextPath}/sighting">Sightings</a></li>
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/person">Superheroes and Supervillains</a></li>
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/location">Locations</a></li>
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/organization">Organizations</a></li>
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/superpower">Superpowers</a></li>
                    </ul>
                        <a href="${pageContext.request.contextPath}/reportSighting" class="btn btn-success navbar-btn navbar-right">Report Sighting</a>
                </div>
            </div>
        </nav>
        <div class="container" style="background-color: aliceblue;">            
            <h2 class="text-center">Edit Sighting</h2>
            <form action="${pageContext.request.contextPath}/updateSighting" method="post">                        
                <div class="form-group">
                    <label for="sightingDate">Date:</label>
                    <input name="sightingDate" type="date" class="form-control" value="${sightingToEdit.sightingsDate}" required/>                            
                </div>    
                <div class="form-group">
                    <label for="personID">Person:</label>
                    <select class="form-control" name="personID">
                        <c:forEach var="person" items="${people}">
                            <c:choose>
                                <c:when test="${person.personID == sightingToEdit.person.personID}">
                                    <option value="${sightingToEdit.person.personID}" selected>${sightingToEdit.person.personName}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${person.personID}">${person.personName}</option>
                                </c:otherwise>
                            </c:choose>                            
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label for="locationID">Location:</label>
                    <select class="form-control" name="locationID">
                        <c:forEach var="location" items="${locations}">
                            <c:choose>
                                <c:when test="${location.locationID == sightingToEdit.location.locationID}">
                                    <option value="${sightingToEdit.location.locationID}" selected>${sightingToEdit.location.locationName}, ${sightingToEdit.location.locationAddress}</option>                                    
                                </c:when>
                                <c:otherwise>
                                    <option value="${location.locationID}">${location.locationName}, ${location.locationAddress}</option>
                                </c:otherwise>
                            </c:choose>                  
                        </c:forEach>
                    </select>
                </div>          
                <input name="sightingID" type="hidden" value="${sightingToEdit.sightingsID}"/>
                <button type="submit" class="btn btn-primary">Submit</button> 
            </form>            
        </div>        
        
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        
    </body>
</html>
