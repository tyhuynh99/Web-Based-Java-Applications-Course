<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Pet Care System</title>

        <!-- Bootstrap core CSS -->
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom fonts for this template -->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
        <link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>

        <!-- Custom styles for this template -->
        <link href="css/agency.min.css" rel="stylesheet">

    </head>

    <body id="page-top">

        <!-- Navigation -->
        <nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
            <div class="container">
                <a class="navbar-brand js-scroll-trigger" href="index.jsp">Pet Care System</a>
                <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    Menu
                    <i class="fas fa-bars"></i>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav text-uppercase ml-auto">
                        <li class="nav-item">
                            <a class="nav-link js-scroll-trigger" href="service_book.jsp">Services</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link js-scroll-trigger" href="ShoppingController">Accessories</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link js-scroll-trigger" href="index.jsp">About</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link js-scroll-trigger" href="index.jsp">Team</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link js-scroll-trigger" href="index.jsp">Contact</a>
                        </li>
                    </ul>
                    <c:if test="${not empty sessionScope.ACCOUNT }" var="checkAccount">
                        
                        <c:if test="${ sessionScope.CART != null }">
                                <form class="form-inline" action="ShoppingController" method="POST">
                                    <input class="form-control form-control-sm mr-3 w-75" 
                                           type="text" 
                                           placeholder="Search accessory ..."
                                           aria-label="Search" 
                                           name="search" value="${param.search}"/>
                                    <button style="border: none; background-color: transparent; height: auto; width: auto">
                                        <i class="fas fa-search" style="color: white" aria-hidden="true"></i>
                                    </button>
                                </form>
                            <div style="color: #FEC810  ; font-size: 16px;">
                                <a href="MainController?action=View Your Shopping Cart">
                                    <label>
                                        <i class="fas fa-shopping-cart"></i> 
                                        ${sessionScope.CART.getCountAccCart()+sessionScope.CART.getCountSerCart()}
                                    </label>
                                </a>
                            </div>
                        </c:if>
                    </c:if>
                    <c:if test="${!checkAccount}" >
                        <div style="margin-left: 100px;">
                            <a href="register.jsp?insert_role=user" style="margin-right: 30px;">Register account</a>
                            <a href="login.jsp" >Login</a>
                        </div>
                    </c:if>
                </div>
            </div>
        </nav>
