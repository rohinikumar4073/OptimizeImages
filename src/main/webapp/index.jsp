<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.UUID" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Optimize</title>
<link href="css/optimize.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-1.11.0.min.js"></script>
<script src="js/optimize.js"></script>
<script src="js/dropzone.js"></script>
<link href="css/dropzone.css" rel="stylesheet" type="text/css" />
</head>

<body>
<%@include file="./Header.jsp" %>
<div class="page">
<div class="page-content">

<img src="img/left.png" style="float:left"/>
<img src="img/left.png" style="float:right"/>
<div class="draganddroptitle"> Please Upload Images by drag and drop</div>
<form action="upload" method="post" class="dropzone dz-clickable" id="demo-upload">
<%UUID idOne = UUID.randomUUID();%>
<input   id="uniqueid" name="<%=String.valueOf(idOne) %>" style="display: none" value="<%=String.valueOf(idOne) %>" />
<div class="danddtitle" id="dandd"><img src="img/upload.png" width="192"  alt="upload" /></div>
<div class="dz-default dz-message"><span>Drop files here to upload</span></div></form>
<div class="greybutton" id="submitbutton" >Generate </div>
</div>

</div>

<%@include file="./Footer.jsp" %>

<script>

$("#submitbutton").click(function(e){
	if($('#submitbutton').css('cursor')!= "pointer"){
		return;
	}
	
	var temp=$("#uniqueid").val();
	  window.location.href="./SpriteSuccess.jsp?"+"temp="+temp;
	
	});
var myDropzone = new Dropzone(".dropzone");
myDropzone.on("complete", function(file) {
	$('#submitbutton').css('background-image', 'url(./img/orangebutton.png)');
	$('#submitbutton').css('cursor', 'pointer');
	  document.getElementById("dandd").style.display="none";
	 



});

</script>
</body>

</html>
    