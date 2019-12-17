<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page import="entity.ProductDetail"%>
<%@page import="entity.Product"%>
<%
	session.removeAttribute("selectedProduct");
	session.removeAttribute("selectedProductDetail");
%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>Add Profile</title>

<!-- CSS -->
<link rel="stylesheet" type="text/css" media="screen"
	href="css/screen.css" />
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" type="text/css" media="screen" href="css/ie.css" />


<!-- JS -->
<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.pack.js"></script>
<script type="text/javascript" src="js/init.js"></script>
<script type="text/javascript" src="js/alert.js"></script>
<script src="jquery-2.1.3.min.js"></script>
<script src="js/script.js"></script>
</head>
<body id="edit-profile">
	<!-- Container -->
	<div id="container">
		<div id="container-inner">

			<h1>Add Product</h1>
			<form action="<c:url value='addProduct'/>" method="post">
				<fieldset>
					<div>
						<label for="name_last">Name: </label> <input type="text"
							name="name" id="name_last" required />
					</div>

					<div>
						<label for="name_first">Image for Product: </label> <input
							type="file" name="imageProduct" size="500" />
					</div>
					<div>
						<label for="name_first">Image for Product Detail: </label> <input
							type="file" name="imageProductDetail_1" size="500" />
					</div>
					<div>
						<label for="name_first">Image for Product Detail: </label> <input
							type="file" name="imageProductDetail_2" size="500" />
					</div>
					<div>
						<label for="name_first">Image for Product Detail: </label> <input
							type="file" name="imageProductDetail_3" size="500" />
					</div>
					<div>
						<label for="name_first">Image for Product Detail: </label> <input
							type="file" name="imageProductDetail_4" size="500" />
					</div>
					<div>
						<label for="name_first">Image for Product Detail: </label> <input
							type="file" name="imageProductDetail_5" size="500" />
					</div>

					<div>
						<label for="name_first">Price: </label> <input type="text"
							name="price" id="name_first" required />
					</div>

					<div>
						<label for="about_you">Description: </label>
						<textarea name="description" id="about_you" rows="10" cols="5"
							required></textarea>
					</div>

					<fieldset id="section-dob" class="group">
						<legend>
							<span>Last Update: </span>
						</legend>
						<!-- Month -->
						<div>
							<label for="dob_month">Month</label> <select name="month"
								id="dob_month"requiredField">>
								<option value="">- Month -</option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>

							</select>
						</div>

						<!-- Day -->
						<div>
							<label for="dob_day">Day</label> <select name="day" id="dob_day"
								required>
								<option value="">- Day -</option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
								<option value="7">7</option>
								<option value="8">8</option>
								<option value="9">9</option>
								<option value="10">10</option>
								<option value="11">11</option>
								<option value="12">12</option>
								<option value="13">13</option>
								<option value="14">14</option>
								<option value="15">15</option>
								<option value="16">16</option>
								<option value="17">17</option>
								<option value="18">18</option>
								<option value="19">19</option>
								<option value="20">20</option>
								<option value="21">21</option>
								<option value="22">22</option>
								<option value="23">23</option>
								<option value="24">24</option>
								<option value="25">25</option>
								<option value="26">26</option>
								<option value="27">27</option>
								<option value="28">28</option>
								<option value="29">29</option>
								<option value="30">30</option>
								<option value="31">31</option>
							</select>
						</div>

						<!-- Year -->
						<div>
							<label for="dob_year">Year</label> <select name="year"
								id="dob_year" required>
								<option value="">- Year -</option>
								<option value="2004">2023</option>
								<option value="2004">2022</option>
								<option value="2004">2021</option>
								<option value="2004">2020</option>
								<option value="2004">2019</option>
								<option value="2004">2018</option>
								<option value="2004">2017</option>
								<option value="2004">2016</option>
								<option value="2004">2015</option>
								<option value="2004">2014</option>
								<option value="2004">2013</option>
								<option value="2004">2012</option>
								<option value="2004">2011</option>
								<option value="2004">2010</option>
								<option value="2004">2009</option>
								<option value="2004">2008</option>
								<option value="2004">2007</option>
								<option value="2004">2006</option>
								<option value="2004">2005</option>
								<option value="2004">2004</option>
								<option value="2003">2003</option>
								<option value="2002">2002</option>
								<option value="2001">2001</option>
								<option value="2000">2000</option>
								<option value="1999">1999</option>
								<option value="1998">1998</option>
								<option value="1997">1997</option>
								<option value="1996">1996</option>
								<option value="1995">1995</option>
								<option value="1994">1994</option>
								<option value="1993">1993</option>
								<option value="1992">1992</option>
								<option value="1991">1991</option>
								<option value="1990">1990</option>
								<option value="1989">1989</option>
								<option value="1988">1988</option>
								<option value="1987">1987</option>
								<option value="1986">1986</option>
								<option value="1985">1985</option>
								<option value="1984">1984</option>
								<option value="1983">1983</option>
								<option value="1982">1982</option>
								<option value="1981">1981</option>

							</select>
						</div>
					</fieldset>

					<div>
						<label for="category">Category:</label> <select name="category_id"
							id="" required>
							<option value="1">Mac</option>
							<option value="2">iPhone</option>
							<option value="3">iPad</option>
							<option value="4">Accessories</option>
						</select>
					</div>
					<div>
						<label for="about_you">Description Details: </label>
						<textarea name="description_detail" id="about_you" rows="10"
							cols="5" required></textarea>
					</div>

					<div>
						<label for="about_you">Technique Details: </label>
						<textarea name="techniqueDetail" id="" rows="10" cols="5" required></textarea>
					</div>

					<div>
						<label for="name_first">Accessories: </label> <input type="text"
							name="accessories" id="" required />
					</div>

					<div>
						<label for="name_first">Guaranty: </label> <input type="text"
							name="guaranty" id="" required />
					</div>



					<!-- Controls -->
					<input id="submit" name="submitSave" type="submit" value="Save"/>
					

				</fieldset>
			</form>

		</div>
	</div>

	<!-- /Container -->

</body>
</html>