
<head>
<style>
label {
	font-family: QuickSandBook;
	font-size:.5cm;
}
input { 
	font-family: "QuicksandBook", Arial, sans-serif; 
	font-style:bold;
	font-size:.42cm;
	/* width: 100%; */
}
</style>
<!-- JS Files -->
<script src="js/jquery.min.js"></script>
<script src="js/custom.js"></script>
<script src="js/slides/slides.min.jquery.js"></script>
<script src="js/cycle-slider/cycle.js"></script>
<script src="js/nivo-slider/jquery.nivo.slider.js"></script>
<script src="js/tabify/jquery.tabify.js"></script>
<script src="js/prettyPhoto/jquery.prettyPhoto.js"></script>
<script src="js/twitter/jquery.tweet.js"></script>
<script src="js/scrolltop/scrolltopcontrol.js"></script>
<script src="js/portfolio/filterable.js"></script>
<script src="js/modernizr/modernizr-2.0.3.js"></script>
<script src="js/easing/jquery.easing.1.3.js"></script>
<script src="js/kwicks/jquery.kwicks-1.5.1.pack.js"></script>
<script src="js/swfobject/swfobject.js"></script>
<!-- FancyBox -->
<link rel="stylesheet" type="text/css" href="js/fancybox/jquery.fancybox.css" media="all">
<script src="js/fancybox/jquery.fancybox-1.2.1.js"></script>
</head>

<div id="container">
  <h1>User Profile</h1>
  <div style="width:25%; float:left; position:relative; padding: 0px 0px;/*  border:1px solid black */">
    <img src= "avatar/Luffys-flag-icon.png" style="max-width:100%;height:auto; /* border:1px solid black; */">
  </div>
  <div style="width:70%; float:right; position:relative; padding: 0px 0px;/* border:1px solid black; */">
  <!-- <div class="one-half-last"> -->
    <ul id="tabify_menu" class="menu_tab" style="margin: 0;">
      <li class="active"><a href="#fane1">Profile</a></li>
      <li><a href="#fane2">Order lists</a></li>
      <li><a href="#fane3">Change password</a></li>
    </ul>
    <div id="fane1" class="tab_content">
		<form action="#" id="contact_form" method="post">
			<fieldset>
				<label>Name <span class="required">*</span></label> <input
					type="text" name="name" id="myName" value="Monkey D. Luffy"
					class="text requiredField" readonly style="width: 50%">
			</fieldset>
			<fieldset>
				<label>Email <span class="required">*</span></label> <input
					type="text" name="email" id="myEmail"
					value="kingpirate@onepiece.com" class="text requiredField email"
					style="width: 50%">
			</fieldset>
			<fieldset>
				<label>Address <span class="required">*</span></label> <input
					type="text" name="address" id="myAddress" value="Foosha village"
					class="text requiredField subject" style="width: 50%">
			</fieldset>
			<fieldset>
				<label>Your Message <span class="required">*</span></label>
				<textarea name="message" id="myMessage" rows="20" cols="30"
					class="text requiredField" style="width: 100%"></textarea>
			</fieldset>
			<fieldset>
				<input name="Mysubmitted" id="Mysubmitted" value="Send Message"
					class="button white" type="submit" style="font-size: 13px;">
			</fieldset>
		</form>
	</div>
	<div id="fane2" class="tab_content">
		<!-- Code here -->
		<table border="0" style="font-size:13px">
			<th>Order Id</th>
			<th>Date</th>
			<th>Status</th>
			<tr>
				<td>31561681651</td>
				<td>18/12/2019</td>
				<td>Done</td>
			</tr>
		</table>
	</div>
	<div id="fane3" class="tab_content">
		<!-- Code here  -->
	</div>
    <!--END form ID contact_form -->
  </div>
  <div style="clear:both; height: 40px"></div>
</div>
<!-- close container -->
