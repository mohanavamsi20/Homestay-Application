<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>LIST ROOMS</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,500,600,700" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Playfair+Display:400,400i,700,700i" rel="stylesheet">

    <link rel="stylesheet" href="css/open-iconic-bootstrap.min.css">
    <link rel="stylesheet" href="css/animate.css">

    <link rel="stylesheet" href="css/owl.carousel.min.css">
    <link rel="stylesheet" href="css/owl.theme.default.min.css">
    <link rel="stylesheet" href="css/magnific-popup.css">

    <link rel="stylesheet" href="css/aos.css">

    <link rel="stylesheet" href="css/ionicons.min.css">

    <link rel="stylesheet" href="css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="css/jquery.timepicker.css">


    <link rel="stylesheet" href="css/flaticon.css">
    <link rel="stylesheet" href="css/icomoon.css">
    <link rel="stylesheet" href="css/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="//geodata.solutions/includes/countrystatecity.js"></script>
    <script>
    $(document).ready(function() {
        $("#zip").keyup(function() {
            var el = $(this);

            if (el.val().length === 5) {
                $.ajax({
                    url: "http://zip.elevenbasetwo.com",
                    cache: false,
                    dataType: "json",
                    type: "GET",
                    data: "zip=" + el.val(),
                    success: function(result, success) {
                        $("#city").val(result.city);
                        $("#state").val(result.state);
                    }
                });
            }
        });
    });
    </script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
    <div class="container">
        <a class="navbar-brand" href="/">Sojourn</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="oi oi-menu"></span> Menu
        </button>
        <form method="POST" id="myform" action="/logout"></form>
        <div class="collapse navbar-collapse" id="ftco-nav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item"><a href="/" class="nav-link">Home</a></li>
                <li class="nav-item"><a href="/bookrooms" class="nav-link">Book a Room</a></li>
	              <li class="nav-item"><a href="/addroom" class="nav-link">List a Room</a></li>
                <% if(session.getAttribute("username")!=null){%>
                    <li class="nav-item active"><a href="/profile" class="nav-link">Profile</a></li>
                    <li class="nav-item">
                        <a class="nav-link" href="#" onclick="document.getElementById('myform').submit()" >Logout</a>
                    </li>
                <%}else{%>
                    <li class="nav-item"><a href="/loginpage" class="nav-link">Login</a></li>
                <%}%>
            </ul>
        </div>
    </div>
</nav>
<!-- END nav -->
	<div class="hero-wrap" style="background-image: url('images/bg_1.jpg');">
	    <div class="overlay"></div>
	    <div class="container">
	    <div class="row no-gutters slider-text d-flex align-itemd-end justify-content-center">
	        <div class="col-md-9 ftco-animate text-center d-flex align-items-end justify-content-center">
		        <div class="text">
		            <p class="breadcrumbs mb-2"><span class="mr-2"><a href="/">Home</a></span> <span>List Rooms</span></p>
		            <h1 class="mb-4 bread">LIST ROOMS</h1>
		        </div>
	        </div>
	    </div>
	    </div>
	</div>
	<section class="ftco-section contact-section bg-light">
        <div class="row block-6">
          <div class="col-md-6 order-md-last d-flex" style="left:380px">
            <form method="POST" action="/posthouse" class="bg-white p-5 contact-form" modelAttribute="listHouse">
               <c:if test="${not empty errorMsg}">
                 <div class="alert alert-danger" role="alert">${errorMsg}</div>
               </c:if>
              <div class="form-group">
              	<label for="ownerusername">Your Username</label>
                <input type="text" name="ownerusername" id="ownerusername" class="form-control" value="${username}" readonly>
              </div>
              <div class="form-group">
              	<label for="housetype">Select HouseType *</label>
             	<select class="form-control" name="housetype" id="housetype" required>
             	  <option value="">Select House</option>
				  <option value="Single House">Single family House</option>
				  <option value="Apartment">Apartment</option>
				  <option value="Villa">Villa</option>
				</select>
              </div>
              <div class="form-group">
              	<label for="housetype">Enter House Name *</label>
                <input type="text" name="housename" class="form-control" placeholder="Enter Catchy House Name" required>
                
              </div>
              <div class="form-group">
              <label for="facilities">Select House Facilities</label>
	             <div class="d-flex flex-row bd-highlight mb-10">
	              	<div class="p-2 bd-highlight">
					  <input type="checkbox" id="Garden" name="Garden">
					  <label for="Garden">Garden</label>
					</div>
					<div class="p-2 bd-highlight">
					  <input type="checkbox" id="Parking" name="Parking">
					  <label for="Parking">Parking</label>
					</div>
					<div class="p-2 bd-highlight">
					  <input type="checkbox" id="Laundry" name="Laundry">
					  <label for="Laundry">Laundry</label>
					</div>
				</div>
				<div class="d-flex flex-row bd-highlight mb-10">
	              	<div class="p-2 bd-highlight">
					  <input type="checkbox" id="Internet Access" name="Internet Access">
					  <label for="Internet Access">Internet Access</label>
					</div>
					<div class="p-2 bd-highlight">
					  <input type="checkbox" id="Wheelchair Accessible" name="Wheelchair Accessible">
					  <label for="Wheelchair Accessible">Wheelchair Accessible</label>
					</div>
					<div class="p-2 bd-highlight">
					  <input type="checkbox" id="Air Conditioning" name="Air Conditioning">
					  <label for="Air Conditioning">Air Conditioning</label>
					</div>
				</div>
				<div class="d-flex flex-row bd-highlight mb-10">
	              	<div class="p-2 bd-highlight">
					  <input type="checkbox" id="Bikes for use" name="Bikes for use">
					  <label for="Bikes for use">Bikes for use</label>
					</div>
					<div class="p-2 bd-highlight">
					  <input type="checkbox" id="Swimming Pool" name="Swimming Pool">
					  <label for="Swimming Pool">Swimming Pool</label>
					</div>
					<div class="p-2 bd-highlight">
					  <input type="checkbox" id="Gym at home" name="Gym at home">
					  <label for="Gym at home">Gym at home</label>
					</div>
				</div>
              </div>
              <label for="description">Description *</label>
              <div class="form-group">
              	<textarea id="description" name="description" rows="4" cols="50" required></textarea>
              </div>
              <!-- - -->
              <div class="form-group">
				  <div class="row">
				    <div class="col-sm-4">
				      <label for="country">Country *</label>
				      <select name="country" class="countries form-control" id="countryId" required>
				      <option value="">Select Country</option>
					  </select>
				    </div>
				    <div class="col-sm-4">
				      <label for="state">State *</label>
				      <select name="state" class="states form-control" id="stateId" required>
				      <option value="">Select State</option>
					  </select>
				    </div>
				    <div class="col-sm-4">
				      <label for="city">City/Town *</label>       
				      <select name="city" class="cities form-control" id="cityId" required>
				      <option value="">Select City</option>
				      </select>
				    </div>
				  </div>
			  </div>
			  <!-- - -->
              <div class="form-group">
              	<label for="address">Address *</label>
                <input type="text" name="address" class="form-control" placeholder="Enter Address" required>
              </div>
              <div class="form-group">
              	<label for="Pincode">Pincode *</label>
                <input type="text" name="Pincode" class="form-control" placeholder="Enter Pincode" required>
              </div>
              
              <div class="form-group">
              <label for="localfacilities">Select Local Facilities Near House</label>
	             <div class="d-flex flex-row bd-highlight mb-10">
	              	<div class="p-2 bd-highlight">
					  <input type="checkbox" id="Airport" name="Airport">
					  <label for="Airport">Airport</label>
					</div>
					<div class="p-2 bd-highlight">
					  <input type="checkbox" id="Railway Station" name="Railway Station">
					  <label for="Railway Station">Railway Station</label>
					</div>
					<div class="p-2 bd-highlight">
					  <input type="checkbox" id="Bus stop" name="Bus stop">
					  <label for="Bus stop">Bus stop</label>
					</div>
				</div>
				<div class="d-flex flex-row bd-highlight mb-10">
	              	<div class="p-2 bd-highlight">
					  <input type="checkbox" id="Shopping mall" name="Shopping mall">
					  <label for="Shopping mall">Shopping mall</label>
					</div>
					<div class="p-2 bd-highlight">
					  <input type="checkbox" id="Restaurants" name="Restaurants">
					  <label for="Restaurants">Restaurants</label>
					</div>
					<div class="p-2 bd-highlight">
					  <input type="checkbox" id="Hospital" name="Hospital">
					  <label for="Hospital">Hospital</label>
					</div>
				</div>
			  </div>
			  <div class="form-group">
              	<label for="duration">Room Duration *</label>
              	<p>Note: Room Duration can be extend upto 100 days</p>
                <input type="number" name="duration" class="form-control" placeholder="Enter Maximum room duration in days" min="1" step="1" max="100" required>
              </div>
              <div class="form-group">
              	<label for="rooms">Number of Room *</label>
                <input type="number" name="rooms" class="form-control" placeholder="Enter Number of rooms in your house" required>
              </div>
              <label for="priceperroom">Enter price per room *</label>
              <div class="form-group">
              	<label for="priceperroom">Select Currency *</label>
                <input type="number" name="priceperroom" class="form-control" placeholder="Enter Price per room at one night" required>
              </div>
              <label for="roomdescription">Room Description *</label>
              <p>Describe your room facilities</p>
              <div class="form-group">
              	<textarea id="roomdescription" name="roomdescription" rows="4" cols="50" required></textarea>
              </div>
              
              <label for="roomimg">Upload Room Images *</label>
              <p>Note:You can upload multiple images at one upload</p>
              <div class="form-group">
  				 <input type="file" id="roomimg" name="roomimg" accept="image/*" multiple required>
              </div>
              <label for="faceimg">Upload Your Face Images *</label>
              <div class="form-group">
  				 <input type="file" id="faceimg" name="faceimg" accept="image/*" required>
              </div>
              <div class="form-group">
                <input id="enter" type="submit" value="UPLOAD HOUSE" class="btn btn-primary py-3 px-5">
              </div>
            </form>
          </div>
        </div>
   	</section>
<footer class="ftco-footer ftco-bg-dark ftco-section">
      <div class="container">
        <div class="row mb-5">
          <div class="col-md">
            <div class="ftco-footer-widget mb-4">
              <h2 class="ftco-heading-2">Sojourn</h2>
              <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
              <ul class="ftco-footer-social list-unstyled float-md-left float-lft mt-5">
                <li class="ftco-animate"><a href="#"><span class="icon-twitter"></span></a></li>
                <li class="ftco-animate"><a href="#"><span class="icon-facebook"></span></a></li>
                <li class="ftco-animate"><a href="#"><span class="icon-instagram"></span></a></li>
              </ul>
            </div>
          </div>
          <div class="col-md">
            <div class="ftco-footer-widget mb-4 ml-md-5">
              <h2 class="ftco-heading-2">Useful Links</h2>
              <ul class="list-unstyled">
                <li><a href="#" class="py-2 d-block">Blog</a></li>
                <li><a href="#" class="py-2 d-block">Rooms</a></li>
                <li><a href="#" class="py-2 d-block">Amenities</a></li>
                <li><a href="#" class="py-2 d-block">Gift Card</a></li>
              </ul>
            </div>
          </div>
          <div class="col-md">
             <div class="ftco-footer-widget mb-4">
              <h2 class="ftco-heading-2">Privacy</h2>
              <ul class="list-unstyled">
                <li><a href="#" class="py-2 d-block">Career</a></li>
                <li><a href="#" class="py-2 d-block">About Us</a></li>
                <li><a href="#" class="py-2 d-block">Contact Us</a></li>
                <li><a href="#" class="py-2 d-block">Services</a></li>
              </ul>
            </div>
          </div>
          <div class="col-md">
            <div class="ftco-footer-widget mb-4">
            	<h2 class="ftco-heading-2">Have a Questions?</h2>
            	<div class="block-23 mb-3">
	              <ul>
	                <li><span class="icon icon-map-marker"></span><span class="text">203 Fake St. Mountain View, San Francisco, California, USA</span></li>
	                <li><a href="#"><span class="icon icon-phone"></span><span class="text">+2 392 3929 210</span></a></li>
	                <li><a href="#"><span class="icon icon-envelope"></span><span class="text">info@yourdomain.com</span></a></li>
	              </ul>
	            </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12 text-center">

            <p>Copyright &copy;<script>document.write(new Date().getFullYear());</script> SDP-3 4331</a></p>
          </div>
        </div>
      </div>
    </footer>
    
  

  <!-- loader -->
  <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>

  <script src="js/jquery.min.js"></script>
  <script src="js/jquery-migrate-3.0.1.min.js"></script>
  <script src="js/popper.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/jquery.easing.1.3.js"></script>
  <script src="js/jquery.waypoints.min.js"></script>
  <script src="js/jquery.stellar.min.js"></script>
  <script src="js/owl.carousel.min.js"></script>
  <script src="js/jquery.magnific-popup.min.js"></script>
  <script src="js/aos.js"></script>
  <script src="js/jquery.animateNumber.min.js"></script>
  <script src="js/jquery.mb.YTPlayer.min.js"></script>
  <script src="js/bootstrap-datepicker.js"></script>
  <!-- // <script src="js/jquery.timepicker.min.js"></script> -->
  <script src="js/scrollax.min.js"></script>
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
  <script src="js/google-map.js"></script>
  <script src="js/main.js"></script>
</body>
</body>
</html>