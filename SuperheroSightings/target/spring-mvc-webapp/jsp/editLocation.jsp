<%-- 
    Document   : editLocation
    Created on : Jan 2, 2019, 4:01:27 PM
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
        <title>Edit Location</title>
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
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/person">Superheroes and Supervillains</a></li>
                        <li class="active"><a class="nav-link" href="${pageContext.request.contextPath}/location">Locations</a></li>
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/organization">Organizations</a></li>
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/superpower">Superpowers</a></li>
                    </ul>
                        <a href="${pageContext.request.contextPath}/reportSighting" class="btn btn-success navbar-btn navbar-right">Report Sighting</a>
                </div>
            </div>
        </nav>
        <div class="container" style="background-color: aliceblue;">            
            <h2 class="text-center">Edit Location</h2>
            <form action="${pageContext.request.contextPath}/updateLocation" method="post">
                <div class="form-group">
                    <label for="locationName">Location Name:</label>
                    <input name="locationName" type="text" maxlength="45" class="form-control" value="${locationToEdit.locationName}" required/>                            
                </div>                        
                <div class="form-group">
                    <label for="locationDescription">Description:</label>
                    <textarea name="locationDescription" maxlength="300" class="form-control" rows="10" required>${locationToEdit.locationDescription}</textarea>
                </div>                                                
                <div class="form-group">
                    <label for="locationAddress">Address:</label>
                    <input name="locationAddress" type="text" maxlength="45" class="form-control" value="${locationToEdit.locationAddress}" required/>                            
                </div>           
                <div class="form-group">
                    <label for="locationLatitude">Latitude:</label>
                    <input name="locationLatitude" type="text" class="form-control" value="${locationToEdit.locationLatitude}" required/>                            
                </div>     
                <div class="form-group">
                    <label for="locationLongitude">Longitude:</label>
                    <input name="locationLongitude" type="text" class="form-control" value="${locationToEdit.locationLongitude}" required/>                            
                </div>     
                <input type="hidden" name="locationID" value="${locationToEdit.locationID}"/>
                <button type="submit" class="btn btn-primary">Submit</button> 
            </form>
        </div>        
        
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
