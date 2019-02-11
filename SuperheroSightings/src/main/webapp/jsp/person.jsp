<%-- 
    Document   : person
    Created on : Dec 31, 2018, 3:30:50 PM
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
        <title>People</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/modals.css" rel="stylesheet">
    </head>
    <body background="${pageContext.request.contextPath}/backgrounds/captainAmerica.jpg">
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
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/sighting">Sightings</a></li>
                        <li class="active"><a class="nav-link" href="${pageContext.request.contextPath}/person">Superheroes and Supervillains</a></li>
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
            <h2 class="text-center">Superheroes and Supervillains</h2>
            <div class="row">
                <div class="col-sm-4">
                    <h2 class="toggleHeader"></h2>
                </div>
            </div>
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bd-example-modal-lg">Add Person</button>
            <br/>
            <br/>
            
            <div id="showPeopleDiv">
                <div class="table-responsive">
                    <table class="table table-bordered table-hover table-striped">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Description</th>
                                <th>Are they a Hero?</th>
                                <th></th>
                            </tr>                            
                        </thead>
                        <tbody>
                            <c:forEach var="person" items="${people}">
                                <tr>
                                    <td><a href="${pageContext.request.contextPath}/getPersonDetails/${person.personID}">${person.personName}</a></td>
                                    <td>${person.personDescription}</td>
                                    <td>${person.isHero ? 'Yes' : 'No'}</td>
                                    <td class="text-center">
                                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <a href="${pageContext.request.contextPath}/deletePerson/${person.personID}">Delete</a> | 
                                        <a href="${pageContext.request.contextPath}/editPerson/${person.personID}">Edit</a>
                                        </sec:authorize>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            <sec:authorize access="hasRole('ROLE_ADMIN')">
              <div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                <div id="addPersonDiv">

                    <div class="col-sm-12 col-md-offset-2 col-md-8 col-lg-offset-3 col-lg-6">
                        <form action="${pageContext.request.contextPath}/addPerson" method="post">
                            <div class="form-group">
                                <label for="personName">Person Name:</label>
                                <input name="personName" type="text" class="form-control" required/>                            
                            </div>                        
                            <div class="form-group">
                                <label for="personDescription">Person Description:</label>
                                <textarea name="personDescription" class="form-control" rows="10" required></textarea>
                            </div>
                            <div class="form-group">
                                <label for="isHero">Are they a hero?</label><br/>
                                <input type="radio" name="isHero" value="TRUE" class="form-control"/>Yes<br/>
                                <input type="radio" name="isHero" value="FALSE" class="form-control"/>No
                            </div>
                            <div class="form-group">
                                <label for="superpowerID">Superpowers: (hold shift to select more than one)</label>
                                <select class="form-control" name="superpowerIDs" multiple="true">
                                    <c:forEach var="superpower" items="${superpowers}">
                                        <option value="${superpower.superpowerID}">${superpower.superpowerName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="organizationID">Organizations: (hold shift to select more than one)</label>
                                <select class="form-control" name="organizationIDs" multiple="true">
                                    <c:forEach var="organization" items="${organizations}">
                                        <option value="${organization.organizationID}">${organization.orgName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary">Submit</button> 
                        </form>
                    </div>
                </div>
            </div>
                            </div>
                            </div>
                            </div>
                            </sec:authorize>

        
        
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
