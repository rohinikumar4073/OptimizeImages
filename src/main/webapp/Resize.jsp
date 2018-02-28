<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ page import="com.optimize.controller.OptimizerController" %>
            <%@ page import="com.optimize.mobileimagecovertor.Resize" %>
      
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Resize Images</title>
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
<%OptimizerController controller=new OptimizerController();
Resize resizeClass = controller.getResize();
resizeClass.service(request, response,controller.getOptimizeDataManager());


%>
<div class="cordinatecontainer">
<div class="coordinatecontainertitle">
All your images are resized successfully into input mobile device sizes . <br>
<a href="./downloadImagesZip?temp=<%=resizeClass.getImageId() %>">Please Click here to download</a>
</div>
</div>
</div>
<%@include file="./Footer.jsp" %>

</body>
</html>
