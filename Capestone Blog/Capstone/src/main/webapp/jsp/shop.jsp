<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Coffee Shop</title>

        <link href="${pageContext.request.contextPath}/css/shop.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    </head>
    <body> <div id="backgroundImage" style="background-image: url(img/CoffeeLeaves.jpg);">
            <div class="container-fluid">
                <div class="row">
                    <div id="navbar" class="col-lg-offset-2 col-lg-8">
                        <ul>
                            <li><a href="${pageContext.request.contextPath}/welcome">Welcome</a></li>
                            <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                            <li><a href="${pageContext.request.contextPath}/about">About</a></li>
                            <li><a href="${pageContext.request.contextPath}/shop">Shop</a></li>
                            <li role="presentation">
                                <a href="${pageContext.request.contextPath}/j_spring_security_logout">Log Out</a>
                            </li>
                        </ul><!-- NAVBAR-->
                    </div><hr id="line1"/>
                </div>
                <div id="backgroundWhite">
                    <div class="row">
                        <p style="color: #3C3635; font-family: impactA; text-align: center; font-size: 5em; text-shadow: 1px 1px #3C3635;">Buy It</p>
                        <p style="color: #614126; font-family: impactA; text-align: center; margin-top: -25px; font-size: 3em;">Smell It</p>
                        <p style="color: #7a5230; font-family: impactA; text-align: center; margin-top: -20px; font-size: 2em;">Taste It</p>
                        </row>

                    </div>
                    <div class="row">
                        <div  id="ShoppingCartOuter" style="background-color: white;" class=" col-lg-4 col-lg-push-8">
                            <table id="ShoppingCart" class="table table-hover">
                                <tr>
                                <h3 style="font-family: impactA; text-align: center;">Shopping Cart</h3>
                                <th width="55%">Item</th>
                                <th width="20%">Quantity</th>
                                <th width="30%">Price</th>
                                <tbody id="contentRows"></tbody>
                                </tr>
                            </table>
                            <div id="total" ></div>                                                       
                            <form>
                                <label>
                                    Pickup Name
                                    <input id="pickupName" maxlength="20" size="20" type="text" style="width:9em;"/>
                                    <input id="confirmOrder" type="button" value="Place Order"> 
                                </label>
                            </form>             
                        </div>
                        <div id="ColombianCoffee" class="col-lg-2 col-lg-pull-4">
                            <img src="img/coffee/Colombian.gif" style="width:8em;">
                        </div>
                        <div class=" col-lg-5   col-lg-pull-4 ">
                            <h2 style="alignment-adjust: auto;" >Colombian</h2>
                            <p align="justify" style="font-size: 12px;">This Colombia coffee was produced by some of the 600,000-plus members of the National Federation 
                                of the Coffee Growers of Colombia.The uniqueness of Colombian coffee is well-proportioned taste.
                                That said, this is not a classic Colombia but an interestingly flawed version that also happens 
                                to be a remarkable bargain.</p>
                            <p>Price $6.75</p>
                            <form>
                                Qty <input id="quantity0"  type="quantity" maxlength="2" size="2" style="width:25px;" onkeypress="return isNumber(event)">
                                <input  class="coffeeButtons" id="0" type="button" value="Add To Cart">
                            </form>
                        </div>
                        

                    </div>

                    <!--              /////////////////////////////////////////////////////////////////////////////////////
                                        ///////////////////////////////////////////////////////////////////////////////-->
                    <div class="row">
                        <div class="col-lg-2 EspressoCoffee">
                            <img src="img/coffee/Espresso.gif" style="width:8em; padding-top: 3em;">
                        </div>
                        <div class="col-lg-5">
                            <h2 style="padding-top: 1em;">Espresso</h2>
                            <p align="justify" style="font-size: 12px; padding-top: 1em; overflow: hidden;"> Our delicious coffee and rich espresso heritage was born in the city of Louisville in 2015. Since then, we've not only been proud of our rich,                   
                                full-bodied flavor, but also of our unique and inviting culture. Our Espresso coffee can be prepared using your preferred method.</p>
                            <p>Price $6.50</p>
                            <form>
                                Qty <input id="quantity1" type="quantity" maxlength="2" size="2" style="width:25px">
                                <input class="coffeeButtons" id="1" type="button" value="Add To Cart">
                            </form>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-2 FrenchRoastCoffee">
                            <img src="img/coffee/FrenchRoast.gif" style="width:8em; padding-top: 3em;">
                        </div>
                        <div class="col-lg-5">
                            <h2 style="padding-top: 1em;">French Roast</h2>
                            <p align="justify" style="font-size: 12px; padding-top: 1em;">The beans are roasted to their edge for a singular smoky flavor. Normally you wouldn't expect such a bang from a coffee with low acidity and light 
                                body, but this is no ordinary brew. It's a delectably smoky cup, intense and uncompromising..</p>
                            <p>Price $6.00</p>
                            <form>
                                Qty <input id="quantity2" type="quantity" maxlength="2" size="2" style="width:25px">
                                <input class="coffeeButtons" id="2" type="button" value="Add To Cart">
                            </form>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-2 GourmetCoffee">
                            <img src="img/coffee/Gourmet.gif" style="width:8em; padding-top: 3em;">
                        </div>
                        <div class="col-lg-5">
                            <h2 style="padding-top: 1em;">Gourmet</h2>
                            <p align="justify" style="font-size: 12px; padding-top: 1em;">Our Java Beans Gourmet Coffee uses 100% screened and prepared beans to ensure a bold lively flavor that will give you the pick-me-up you need to 
                                get through the day.</p>
                            <p>Price $6.75</p>
                            <form>
                                Qty <input id="quantity3" type="quantity" maxlength="2" size="2" style="width:25px">
                                <input class="coffeeButtons" id="3" type="button" value="Add To Cart">
                            </form>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-2 HazelnutCoffee">
                            <img src="img/coffee/Hazelnut.gif" style="width:8em; padding-top: 3em;">
                        </div>
                        <div class="col-lg-5">
                            <h2 style="padding-top: 1em;">Hazelnut</h2>
                            <p align="justify" style="font-size: 12px; padding-top: 1em;">Another favorite blend, highly aromatic with a strong nutty flavor. Java Beans Hazelnut is a Java 
                                blend that combines 10% Java coffee and fine arabica coffee from around the world to give you taste that you won't soon forget. A popular flavor among
                                our coffee fans!</p>
                            <p>Price $6.50</p>
                            <form>
                                Qty <input id="quantity4" type="quantity" maxlength="2" size="2" style="width:25px">
                                <input class="coffeeButtons" id="4" type="button" value="Add To Cart">
                            </form>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-2 HouseBlendCoffee">
                            <img src="img/coffee/HouseBlend.gif" style="width:8em; padding-top: 3em;">
                        </div>
                        <div class="col-lg-5">
                            <h2 style="padding-top: 1em;">House Blend</h2>
                            <p align="justify" style="font-size: 12px; padding-top: 1em;">A perennial customer favorite, this inviting American blend is a perfect introduction to 
                                Java Beans coffees. Flavor notes: bright, balanced, and medium-bodied. Pleasant hint of spice with a crisp finish.</p>
                            <p>Price $6.00</p>
                            <form>
                                Qty <input id="quantity5" type="quantity" maxlength="2" size="2" style="width:25px">
                                <input  class="coffeeButtons" id="5" type="button" value="Add To Cart">
                            </form>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-2 OriginalCoffee">
                            <img src="img/coffee/Original.gif" style="width:8em; padding-top: 3em;">
                        </div>
                        <div class="col-lg-5">
                            <h2 style="padding-top: 1em;">Original</h2>
                            <p align="justify" style="font-size: 12px; padding-top: 1em;">Java Beans first opened its doors in 2015. Since that day, our premium blend coffee has been 
                                served only one way - fresh. Our special blend is made with 100% Arabica beans from several of the world's most renowned coffee growing regions and always
                                served within 20 minutes of brewing. Brew your own fresh pot and enjoy the great taste of Java Beans coffee at home.</p>
                            <p>Price $6.00</p>
                            <form>
                                Qty <input id="quantity6" type="quantity" maxlength="2" size="2" style="width:25px">
                                <input  class="coffeeButtons" id="6" type="button" value="Add To Cart">
                            </form>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-2 ViennaCoffee">
                            <img src="img/coffee/Vienna.gif" style="width:8em; padding-top: 3em;">
                        </div>
                        <div class="col-lg-5">
                            <h2 style="padding-top: 1em;">Vienna</h2>
                            <p align="justify" style="font-size: 12px; padding-top: 1em;">For a year, our globally recognized signature blend has delivered a consistently rich 
                                and satisfying full-bodied flavor. Vienna coffee blends are crafted from the highest-quality beans, using industry-leading standards and technology to 
                                ensure that every cup you serve is consistently delicious.</p>
                            <p>Price $6.75</p>
                            <form>
                                Qty <input id="quantity7" type="quantity" maxlength="2" size="2" style="width:25px">
                                <input class="coffeeButtons" id="7" type="button" value="Add To Cart">
                            </form>
                        </div>
                    </div>
                    <script src="${pageContext.request.contextPath}/js/shop.js"></script>
                    </body>
                    </html>
