<%-- 
    Document   : superpower
    Created on : Jan 2, 2019, 10:42:28 PM
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
        <title>Superpowers</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/modals.css" rel="stylesheet">
    </head>
    <body background="${pageContext.request.contextPath}/backgrounds/theFlash.jpg">
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
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/person">Superheroes and Supervillains</a></li>
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/location">Locations</a></li>
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/organization">Organizations</a></li>
                        <li class="active"><a class="nav-link" href="${pageContext.request.contextPath}/superpower">Superpowers</a></li>
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
            <h2 class="text-center">Superpowers</h2>
            <div class="row">
                <div class="col-sm-4">
                    <h2 class="toggleHeader"></h2>
                </div>
            </div>
            
            <sec:authorize access="hasRole('ROLE_ADMIN')">
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bd-example-modal-lg">Add Superpower</button>
            </sec:authorize>
            <br/>
            <br/>
            
        <div id="showPowersDiv">
                <div class="table-responsive">
                    <table class="table table-bordered table-hover table-striped">
                        <thead>
                            <tr>
                                <th>Superpower Name</th>
                            </tr>                            
                        </thead>
                        <tbody>
                            <c:forEach var="superpower" items="${superpowers}">
                                <tr>
                                    <td><a href="getSuperpowerDetails/${superpower.superpowerID}">${superpower.superpowerName}</a></td>
                                    <td class="text-center">
                                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <a href="deletePower/${superpower.superpowerID}">Delete</a> | 
                                        <a href="editPower/${superpower.superpowerID}">Edit</a>
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
            <div id="addPowerDiv">
                
                    <div class="col-sm-12 col-md-offset-2 col-md-8 col-lg-offset-3 col-lg-6">
                        <form action="${pageContext.request.contextPath}/addPower" method="post">
                            <div class="form-group">
                                <label for="powerName">Power Name:</label>
                                <input name="powerName" type="text" class="form-control" required/>                            
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
        </div>
    
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script> 
    </body>
</html>
