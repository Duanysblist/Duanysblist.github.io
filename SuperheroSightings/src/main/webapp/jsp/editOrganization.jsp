<%-- 
    Document   : editOrganization
    Created on : Jan 2, 2019, 4:13:14 PM
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
        <title>Edit Organization</title>
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
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/location">Locations</a></li>
                        <li class="active"><a class="nav-link" href="${pageContext.request.contextPath}/organization">Organizations</a></li>
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/superpower">Superpowers</a></li>
                    </ul>
                        <a href="${pageContext.request.contextPath}/reportSighting" class="btn btn-success navbar-btn navbar-right">Report Sighting</a>
                </div>
            </div>
        </nav>
        <div class="container" style="background-color: aliceblue;">            
            <h2 class="text-center">Edit Organization</h2>
            <form action="${pageContext.request.contextPath}/updateOrganization" method="post">
                <div class="form-group">
                    <label for="orgName">Organization Name:</label>
                    <input name="orgName" type="text" maxlength="45" class="form-control" value="${orgToEdit.orgName}" required/>                            
                </div>                        
                <div class="form-group">
                    <label for="orgDescription">Description:</label>
                    <textarea name="orgDescription" maxlength="300" class="form-control" rows="10" required>${orgToEdit.orgDescription}</textarea>
                </div>                                                
                <div class="form-group">
                    <label for="orgContactInfo">Contact Info:</label>
                    <input name="orgContactInfo" type="text" maxlength="45" class="form-control" value="${orgToEdit.orgContactInfo}" required/>                            
                </div>
                <div class="form-group">
                    <label for="isHeroOrg">A Hero Organization:</label>
                        <input type="checkbox" name="isHeroOrg" value="TRUE" checked="checked" class="form-control">Yes<br/>
                        <input type="checkbox" name="isHeroOrg" value="FALSE" class="form-control">No
                </div>    
                <input type="hidden" name="organizationID" value="${orgToEdit.organizationID}"/>
                <button type="submit" class="btn btn-primary">Submit</button> 
            </form>
        </div>        
        
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
