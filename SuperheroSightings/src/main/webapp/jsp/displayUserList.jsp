<%-- 
    Document   : displayUserList
    Created on : Jan 22, 2019, 2:04:51 PM
    Author     : Dan's Laptop
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Superhero Sighting Users</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body background="${pageContext.request.contextPath}/backgrounds/Venom.jpg">
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
                        <li><a class="nav-link" href="${pageContext.request.contextPath}/superpower">Superpowers</a></li>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <li class="active" role="presentation">
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
                <div class="container" style="background-color: aliceblue">
            <h2 class="text-center">Users</h2>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bd-example-modal-lg">Add User</button>
            </sec:authorize>
            <br/>
            <hr/>
            <div class="table-responsive">
            <table class="table table-bordered table-hover table-striped">
                <thead>
                    <tr>
                        <th>User Name</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${users}">
                    <tr>
                        <td><c:out value="${user.userName}"/></td>
                        <td><a href="deleteUser?username=${user.userName}">Delete</a></td>
                    </tr>
                    </c:forEach>
                </tbody>
                    </table>
                
                

            
        </div>
                
            <div class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                
                <form method="POST" action="addUser">
                    <div class="form-group">
                        <label for="username">Username:</label>
                         <input type="text" 
                                 name="username" class="form-control"/>                            
                    </div>
                    <div class="form-group">
                        <label for="password">Password: </label>
                        <input type="password" 
                                 name="password" class="form-control"/>                          
                    </div>
                    <div class="form-group">
                        <label for="isAdmin">Admin User? </label>
                        <input type="checkbox" 
                            name="isAdmin" value="yes" class="form-control"/>                          
                    </div>     

                <input type="submit" value="Add User"/><br/>
                </form>
                    </div>
                </div>
            </div>
            </div>
                
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>
