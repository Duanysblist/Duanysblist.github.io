<%-- 
    Document   : editPerson
    Created on : Jan 2, 2019, 3:55:59 PM
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
        <title>Edit Person</title>
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
        <div class="container" style="background-color: aliceblue;">            
            <h2 class="text-center">Edit Person</h2>
                <sf:form class="form-horizontal" role="form" modelAttribute="person" action="${pageContext.request.contextPath}/updatePerson" method="POST">
                     <div class="form-group">
                                <label for="personName" class="col-md-4 control-label">Person Name:</label>

                               <sf:input path="personName" required="required"/>                            

                            </div>
                            
                            <div class="form-group">
                                <label for="personDescription" class="col-md-4 control-label">Person Description:</label>
                                <div class="col-md-8">
                                <sf:textarea path="personDescription" name="personDescription" class="form-control" rows="10" ></sf:textarea>
                                </div>
                            </div>
                                
                            <div class="form-group">
                                <label for="isHero" class="col-md-4 control-label">Are they a hero?</label><br/>
                                <div class="col-md-8">
                            <sf:radiobutton path="isHero" name="isHero" value="TRUE" class="form-control"/>Yes<br/>
                            <sf:radiobutton path="isHero" name="isHero" value="FALSE" class="form-control"/>No
                                </div>
                            </div>
                                
                            <div class="form-group">
                                <label for="superpowerID" class="col-md-4 control-label">Superpowers: (hold shift to select more than one)</label>
                                <div class="col-md-8">
                                    
                                <sf:select path="listOfSuperpowerIDs">
                                    <sf:options items="${superpowers}" itemLabel="superpowerName" itemValue="superpowerID"/>
                                </sf:select>
                                </div>
                            </div>
                                
                            <div class="form-group">
                                <label for="organizationID" class="col-md-4 control-label">Organizations: (hold shift to select more than one)</label>
                                <div class="col-md-8">
                                <sf:select path="listOfOrganizationIDs">
                                    <sf:options items="${organizations}" itemLabel="orgName" itemValue="organizationID"/>
                                </sf:select>
                                </div>
                            </div>
                            <input name="personID" type="hidden" value="${person.personID}"/>   
                            <button type="submit" class="btn btn-primary">Submit</button> 
                </sf:form>
           
                    </div>

        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
