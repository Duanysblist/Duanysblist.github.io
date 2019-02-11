<%-- 
    Document   : organization
    Created on : Dec 31, 2018, 3:34:13 PM
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
        <title>Organizations</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/modals.css" rel="stylesheet">
    </head>
    <body background="${pageContext.request.contextPath}/backgrounds/joker.jpg">
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
                        <li class="active"><a class="nav-link" href="${pageContext.request.contextPath}/organization">Organizations</a></li>
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
            <h2 class="text-center">Organizations</h2>
            <div class="row">
                <div class="col-sm-4">
                    <h2 class="toggleHeader"></h2>
                </div>
            </div>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bd-example-modal-lg">Add Organization</button>
            </sec:authorize>
            <br/>
            <br/>
            <div id="viewOrgsTable">                                    
                <div class="table-responsive">
                    <table class="table table-bordered table-hover table-striped">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Description</th>
                                <th>Contact Info</th>
                                <th>Hero Organization?</th>
                                <th></th>
                            </tr>                            
                        </thead>
                        <tbody>
                            <c:forEach var="organization" items="${organizations}">
                                <tr>
                                    <td><a href="getOrganizationDetails/${organization.organizationID}">${organization.orgName}</a></td>
                                    <td>${organization.orgDescription}</td>
                                    <td>${organization.orgContactInfo}</td>
                                    <td>${organization.isHeroOrg ? 'Yes' : 'No'}</td>
                                    <td>
                                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <a href="deleteOrganization/${organization.organizationID}">Delete</a> 
                                        <a href="editOrganization/${organization.organizationID}">Edit</a>
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
            <div id="addOrgForm">
            <div class="col-sm-12 col-md-offset-2 col-md-8 col-lg-offset-3 col-lg-6">
                <form action="${pageContext.request.contextPath}/addOrganization" method="post">
                    <div class="form-group">
                        <label for="orgName">Organization Name:</label>
                        <input name="orgName" type="text" maxlength="45" class="form-control" required/>                            
                    </div>                        
                    <div class="form-group">
                        <label for="orgDescription">Description:</label>
                        <textarea name="orgDescription" maxlength="300" class="form-control" rows="10" required></textarea>
                    </div>                                                
                    <div class="form-group">
                        <label for="orgContactInfo">Contact Information:</label>
                        <input name="orgContactInfo" type="text" maxlength="45" class="form-control" required/>                            
                    </div>  
                    <div class="form-group">
                        <label for="isHeroOrg">A Hero Organization:</label>
                        <input type="checkbox" name="isHeroOrg" value="TRUE" checked="checked" class="form-control">Yes<br/>
                        <input type="checkbox" name="isHeroOrg" value="FALSE" class="form-control">No
                    </div>                          
                    <button type="submit" class="btn btn-primary">Submit</button> 
                </form>
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
