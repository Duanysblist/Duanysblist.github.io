<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Welcome Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/modals.css" rel="stylesheet">
    <style>
      /* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
      #map {
        height: 50%;
        width: 100%;
        margin: 0 auto 0 auto;
        
      }
      /* Optional: Makes the sample page fill the window. */
      html, body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
    </style>

        
    </head>
    <body background="${pageContext.request.contextPath}/backgrounds/newSuperman.jpg">
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
                        <li class="active"><a class="nav-link" href="${pageContext.request.contextPath}/">Home</a></li>
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
              <div id="map"></div>
              <br/>
            <br/>
        <div class="container" style="background-color: aliceblue">
            <br/>
            <br/>
            <div class="jumbotron">
                <h2>So you think you saw a super...</h2>
                <p>Well you didn't. Brought to you by your friends at the Department of Defense.</p>
                <p>On this site, you can add your "sightings" by clicking on the "Report Sighting" button in the top right hand corner of any page and we'll take a long hard look at it while it's stored in our archives. You can also see the details for certain locations, people, and organizations simply by clicking on their names. </p>
            </div>
        </div>
              
                
                <br/>
            <br/>
                <div class="container" style="background-color: aliceblue">
            <h2>Most Recent Sightings</h2>
            <div class="table-responsive">
                <table class="table table-bordered table-hover table-striped">
                    <thead>
                        <tr>
                            <th>Date</th>
                            <th>Person</th>
                            <th>Location</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="sightings" items="${sightings}">
                            <tr>
                                <td><a href="getSightingDetails/${sightings.sightingsID}">${sightings.sightingsDate}</a></td>
                                <td><a href="${pageContext.request.contextPath}/getPersonDetails/${sightings.person.personID}">${sightings.person.personName}</a></td>
                                <td><a href="${pageContext.request.contextPath}/getLocationDetails/${sightings.location.locationID}">${sightings.location.locationName}</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            
            


        </div>
                
                <br/>
                <br/>
                      <!-- Placed at the end of the document so the pages load faster -->
    <script>
      function initMap() {
          
          var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 4
          });
          
          var bounds = new google.maps.LatLngBounds();

            <c:forEach var="sightings" items="${sightings}" varStatus="status">
                    var myLatLng = new google.maps.LatLng(<c:out value="${sightings.location.locationLatitude}"/>, <c:out value="${sightings.location.locationLongitude}"/>);
    
            
          
            var marker = new google.maps.Marker({
            position: myLatLng,
            map: map,
            title: 'Hello World!',
            label:{text:"${sightings.location.locationName}"}
            });
       bounds.extend(myLatLng);
       </c:forEach>
           map.fitBounds(bounds);
           
      }
    </script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBEDIIEe7NftcI4Zq17HX3WVhkJ2FkdNuE &callback=initMap"
    async defer></script>

        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

