<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Vending Machine Project</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>

    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <h1 class="text-center">
                        Vending Machine
                        <hr/>
                    </h1>
                </div>
            </div>

            <div class="row">
                <div id="itemButtons" class="col-md-8">
                    <div class="row" id="items">
                        <!-- where all of the item buttons go -->
                        <c:forEach var="currentItem" items="${items}">
                            <div class="col-md-4">
                            <div class="itemIn">
                                <a href="${pageContext.request.contextPath}/itemChoice/${currentItem.itemId}" class="btn btn-primary" style="margin:50px;">
                                    ${currentItem.itemId}
                                    <br/>
                                    ${currentItem.itemName}
                                    <br/>
                                    Cost:$${currentItem.itemCost}
                                    <br/>
                                    Quantity:${currentItem.itemInventory}
                               </a>
                            </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>


                <div class="col-md-4">
                    <div id="moneyScreen">
                        <form>
                            <label for="addMoney" class="form-label">Total $ In</label>
                            <br/>
                            <input type="text" class="form-control" id="balance" value="${balance}" readonly>
                            <!-- text box that holds money amount -->
                        </form>
                            <br/>
                            <br/>
                            <a href="${pageContext.request.contextPath}/addMoney/dollar" class="btn btn-warning">Add Dollar </a>
                            <!-- button to add dollar -->
                            <a href="${pageContext.request.contextPath}/addMoney/quarter" class="btn btn-warning">Add Quarter </a>
                            <!-- button to add quarter -->
                            <br/>
                            <br/>
                            <a href="${pageContext.request.contextPath}/addMoney/dime" class="btn btn-warning">Add Dime </a>
                            <!-- button to add dime -->
                            <a href="${pageContext.request.contextPath}/addMoney/nickel" class="btn btn-warning">Add Nickel </a>
                            <!-- button to add nickel -->
                            <hr/>
                        
                    </div>
                    <div id="Messages">
                        <form>
                            <label for="message" class="form-label">Message</label>
                            <br/>
                            <input value="${textMessage}" type="text" class="form-control" id="message" readonly/>
                            <!-- message text box -->
                            <br/>
                            <br/> 
                            Item: <input value="${itemChoice}" type="text" class="form-control" id="itemDisplay" readonly/>
                            <!-- item id display text box -->
                            <br/>
                            <br/>
                         </form>
                            <a href="vendItem" class="btn btn-warning">Make Purchase</a>
                            <!-- vend item button -->
                            <hr/>
                        
                    </div>
                    <div id="Change">
                        <form>
                            <label for="change" class="form-label">Change</label>
                            <br/>
                            <input value="Quarters: ${change.quarterCounter} Dimes: ${change.dimeCounter} Nickels: ${change.nickelCounter} Pennies: ${change.pennyCounter}" type="text"  class="form-control" id="changeReturned" readonly/>
                            <!-- text box for returned change -->
                            <br/>
                            <br/>
                        </form>
                        <a href="returnChange" class="btn btn-warning"> Return Change</a>
                            <!-- return change button that resets all fields -->
                        
                    </div>
                </div>
            </div>

        </div>


        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>

</html>

