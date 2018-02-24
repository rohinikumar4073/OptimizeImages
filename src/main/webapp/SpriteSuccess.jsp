<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.optimize.DownloadImages" %>
        <%@ page import="com.optimize.Beans.ImageArray" %>
           <%@ page import="com.optimize.controller.OptimizerController" %>
        <%@ page import="java.util.Iterator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="java.util.ArrayList"%><html xmlns="http://www.w3.org/1999/xhtml">
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
<%OptimizerController controller=new OptimizerController();
	DownloadImages downloadImages = controller.getDownloadImages();

	downloadImages.service(request, response,controller.getOptimizeDataManager());
%>
<div class="cordinatecontainer">
<div class="coordinatecontainertitle">
All your images are converted successfully into sprites. <br>
<a href="./download?temp=<%=downloadImages.getImageId() %>">Please Click here to download</a>
</div>
<table width="702">
  <tr>
    <td class="tableheader" width="54px">S.No</td>
        <td class="tableheader" width="328px">Image Names</td>
            <td class="tableheader">Coordinates</td>
  </tr>
   <%
   	ArrayList<ImageArray> outputImages = downloadImages
   			.getImageOutput().getOutputimageArrayList();
   int temp=0;
   	for (Iterator iterator = outputImages.iterator(); iterator
   			.hasNext();) {
   		ImageArray inputImage = (ImageArray) iterator.next();
   		temp++;
 if(temp%2==1){
   		%>
       
 
  <tr>
    <td class="tableodd"  ><%=temp %></td>
        <td class="tableodd"><%=inputImage.getImageName()%></td>
            <td class="tableodd">top:<%=inputImage.getTop()%> ;left :<%=inputImage.getLeft()%> ; right <%=inputImage.getRight()%>; bottom :  <%=inputImage.getBottom()%></td>
  </tr>
  <%}else if(temp%2==0){ %>
  <tr>
    <td class="tableeven"><%=temp %></td>
        <td class="tableeven"><%=inputImage.getImageName()%></td>
            <td class="tableeven">top:<%=inputImage.getTop()%> ;left :<%=inputImage.getLeft()%> ; right <%=inputImage.getRight()%>; bottom :  <%=inputImage.getBottom()%></td>
  </tr>
     <%
  }
 }
     %>
</table>
<div class="copycordinatesbutton" id="copycordinates" >Copy Cordinates </div>
</div>

</div>
</div>
<%@include file="./Footer.jsp" %>

<script>



</script>
</body>

</html>
    