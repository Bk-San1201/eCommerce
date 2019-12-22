<head>
<meta charset="UTF-8">
<title>Sign Up Form</title>
<link rel="stylesheet"
	href="css/normalize.css">
<link rel='stylesheet'
	href='css/font-register.css'>
<link rel="stylesheet" href="css/register.css">

</head>

<%
	String check = (String) session.getAttribute("username_exist");
%>

<body>
	<!-- partial:index.partial.html -->
	<div id="container">
		<form action="<c:url value='register'/>" id="contact_form" method="post">
			<div class="row">
				<h4>Account</h4>
				<div class="input-group input-group-icon">
					<input type="text" name="fullName" placeholder="Full Name" required/>
					<div class="input-icon">
						<i class="fa fa-user"></i>
					</div>
				</div>
				<div class="input-group input-group-icon">
					<input type="text" name="username" placeholder="User Name" required/>
					<div class="input-icon">
						<i class="fa fa-user"></i>
					</div>
				</div>
				<div class="input-group input-group-icon">
					<input type="text" name="address" placeholder="Address" required/>
					<div class="input-icon">
						<i class="fa fa-user"></i>
					</div>
				</div>
				<div class="input-group input-group-icon">
					<input type="text" name="cityregion" placeholder="City Region" required/>
					<div class="input-icon">
						<i class="fa fa-user"></i>
					</div>
				</div>
				<div class="input-group input-group-icon">
					<input type="email" name="email" placeholder="Email Adress" required/>
					<div class="input-icon">
						<i class="fa fa-envelope"></i>
					</div>
				</div>
				<div class="input-group input-group-icon">
					<input type="text" name="phone" placeholder="Phone Number" required/>
					<div class="input-icon">
						<i class="fa fa-envelope"></i>
					</div>
				</div>
				<div class="input-group input-group-icon">
					<input type="password" name="password" placeholder="Password" required/>
					<div class="input-icon">
						<i class="fa fa-key"></i>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-half">
					<h4>Date of Birth</h4>
					<div class="input-group">
						<div class="col-third">
							<input type="text" placeholder="DD" />
						</div>
						<div class="col-third">
							<input type="text" placeholder="MM" />
						</div>
						<div class="col-third">
							<input type="text" placeholder="YYYY" />
						</div>
					</div>
				</div>
				<div class="col-half">
					<h4>Gender</h4>
					<div class="input-group">
						<input type="radio" name="gender" value="male" id="gender-male" />
						<label for="gender-male">Male</label> <input type="radio"
							name="gender" value="female" id="gender-female" /> <label
							for="gender-female">Female</label>
					</div>
				</div>
			</div>
			<div class="row">
				<h4>Payment Details</h4>
				<div class="input-group">
					<input type="radio" name="payment-method" value="card"
						id="payment-method-card" checked="true" /> <label
						for="payment-method-card"><span><i
							class="fa fa-cc-visa"></i>Credit Card</span></label> <input type="radio"
						name="payment-method" value="paypal" id="payment-method-paypal" />
					<label for="payment-method-paypal"> <span><i
							class="fa fa-cc-paypal"></i>Paypal</span></label>
				</div>
				<div class="input-group input-group-icon">
					<input type="text" name="ccNumber" placeholder="Card Number" required/>
					<div class="input-icon">
						<i class="fa fa-credit-card"></i>
					</div>
				</div>
				<div class="col-half">
					<div class="input-group input-group-icon">
						<input type="text" placeholder="Card CVC" />
						<div class="input-icon">
							<i class="fa fa-user"></i>
						</div>
					</div>
				</div>
				<div class="col-half">
					<div class="input-group">
						<select>
							<option>01 Jan</option>
							<option>02 Jan</option>
						</select> <select>
							<option>2015</option>
							<option>2016</option>
						</select>
					</div>
				</div>
			</div>
			<div class="row">
				<h4>Terms and Conditions</h4>
				<div class="input-group">
					<input type="checkbox" id="terms" required/> <label for="terms">I
						accept the terms and conditions for signing up to this service,
						and hereby confirm I have read the privacy policy.</label>
				</div>
				<%
					if (check != null) { %>
						<font color=red>Sorry, username already exists!</font>
				<%	}
				%>
				<button class="button">Register</button>
			</div>
		</form>
	</div>
	<!-- partial -->

</body>