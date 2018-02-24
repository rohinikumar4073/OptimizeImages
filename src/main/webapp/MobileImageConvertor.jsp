<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ page import="com.optimize.controller.OptimizerController" %>
  <%@ page import="java.util.ArrayList" %>
  <%@ page import="java.util.HashMap" %>
  <%@ page import="com.optimize.Beans.Devices" %>
  <%@ page import="java.util.Set" %>
  <%@ page import="java.util.Iterator" %> 
   <%@ page import="java.util.Map.Entry" %>
       <%@ page import="java.util.UUID" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Optimize</title>
<link href="css/reset.css" rel="stylesheet" type="text/css" />
<link href="css/optimize.css" rel="stylesheet" type="text/css" />
<link href="css/dropzone.css" rel="stylesheet" type="text/css" />
<link href="css/dropzone2.css" rel="stylesheet" type="text/css" />

<script src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="js/Raphel.js"></script>
<script type="text/javascript" src="js/raphael.free_transform.js"></script>
<script src="js/optimize.js"></script>
<script src="js/dropzone.js"></script>
<link href="css/listbox.css" rel="stylesheet"/>
<script src="js/listbox.js"></script>
<link href="css/dropkick.css" rel="stylesheet"/>
<script src="js/jquery.dropkick.js"></script>
<script type="text/javascript" src="js/Raphel.js"></script>
<script type="text/javascript" src="js/raphael.free_transform.js">
</script>
<style>


.dropzone .dz-preview .dz-details img,
.dropzone-previews .dz-preview .dz-details img {
  position: absolute;
  top: 0;
  left: 0;
width: 100%;
height: 100%;
}


</style>
</head>

<body>
<%OptimizerController controller=new OptimizerController();

HashMap<String, ArrayList<Devices>> haspMap =	controller.getOptimizeDataManager().getDeviceList();
%>
<%@include file="./Header.jsp" %>
<div class="page">
<div class="page-content-mobile">
<div class="inputcontent">
<table  class="table1" >
  <tr>
    <td class="inputColumn" width="48%">
    
     <div class="headingOne">
     Input Image Device Details
     </div>
         <div class="headingTwo">
Select Platform     </div>
 <select id="inputplatformselect"  name="inputplatform" class="selectpicker" tabindex="2" >
              <option value="" selected="selected">- Select - </option>
              <%
             Set<String>set= haspMap.keySet();
              for (Iterator iterator = set.iterator(); iterator.hasNext();) {
  				String string = (String) iterator.next();
  				
  			
              %>
              <option value="<%=string %>"><%=string %></option>
              
              <%} %>
              	<option value="Other">Other</option>
            </select>
               <div class="headingTwo" id="selectDevice">
Select Device     </div>
 <select id="inputplatform"  name="inputplatform" class="selectdevice" tabindex="2"  >
            <%Set <Entry<String,ArrayList<Devices>>> entrySet= haspMap.entrySet();
             for (Iterator iterator = entrySet.iterator(); iterator.hasNext();) {
				Entry entry = (Entry) iterator.next();
				ArrayList<Devices> devices=(ArrayList<Devices>) entry.getValue();
				for (Iterator iterator2 = devices.iterator(); iterator2
						.hasNext();) {
					Devices devices2 = (Devices) iterator2.next();
				String string2 =devices2.getDeviceName()+" ( "+devices2.getWidth() +" x "+devices2.getHeight()+" ) ";
			%>
			      <option platform="<%=(String)entry.getKey()%>" value="<%=devices2.getDeviceName() %>"><%=string2 %></option>
			<%
				}} %>
				               <option value="Other" platform="Other">Other</option>
				
            </select>     
            <div class="widAndHeight" style="display:none">
                <div class="headingTwo">
Select Width     </div>
                     <input value="in Pixels"  onfocus="clearText(this)">
     </input>        <div class="headingTwo">
Select  Height    </div>
                     <input value="in Pixels"  onfocus="clearText(this)">
     </input></div>
            </td>
    <td><img src="img/middle.png" width="2" height="343" alt="img" style="margin:auto;display:block"/>
</td>
    <td class="outputColumn" width="48%">
    
    <div class="headingOne">
     Output Image Device Details
     </div>
     <div class="headingTwo">
Select Platform     </div>
 <select id="outputplatformselect" name="outputplatform" class="selectpicker" tabindex="2" >
              <option value="" selected="selected">- Select - </option>
              <%
              for (Iterator iterator = set.iterator(); iterator.hasNext();) {
  				String string = (String) iterator.next();
  				
  			
              %>
              <option value="<%=string %>"><%=string %></option>
              
              <%} %>
               <option value="Other">Other</option>
            </select>
               <div class="headingTwo" id="selectDevice2">
Select Device     </div>
 <select  id="outputplatform" name="outputplatform" class="selectdevice" tabindex="2" multiple="multiple" >
            <%
             for (Iterator iterator = entrySet.iterator(); iterator.hasNext();) {
				Entry entry = (Entry) iterator.next();
				ArrayList<Devices> devices=(ArrayList<Devices>) entry.getValue();
				for (Iterator iterator2 = devices.iterator(); iterator2
						.hasNext();) {
					Devices devices2 = (Devices) iterator2.next();
				String string2 =devices2.getDeviceName()+" ( "+devices2.getWidth() +" x "+devices2.getHeight()+" ) ";
			%>
			      <option  platform="<%=(String)entry.getKey()%>"  value="<%=devices2.getDeviceName() %>"><%=string2 %></option>
			<%
				}} %>
			
            </select>  
            <div class="widAndHeight" style="display:none" >
                <div class="headingTwo">
Select Width     </div>
                     <input value="in Pixels"  onfocus="clearText(this)">
     </input>        <div class="headingTwo">
Select  Height    </div>
                     <input value="in Pixels"  onfocus="clearText(this)">
     </input></div>
    </td>
  </tr>
</table>




</div>
<div id="upload">
<div style="padding-bottom:15px">Select Image(s) Input Mode</div>
<input checked="checked" type="radio" name="bulk" id="r1"/>
<label for="r1"><span></span>Bulk Upload</label>
<input type="radio" name="bulk" id="r2" />
<label for="r2"><span></span>Single With Strechable Areas</label>
</div>
<div class="dragndrop">
  <div class="draganddroptitle" style="font-size:18px;font-weight:bold"> Drag and Drop Images</div>
  <div class="singdraganddroptitle" style="font-size:18px;font-weight:bold;display: hide;"> Upload single Image and indicate strechable areas</div>
    <form action="upload" method="post" class="dropzone dz-clickable" id="demo-upload" style="min-height: 180px;">
          
                 <input id="uniqueid" name="7a6777fc-3a21-4007-a7af-c9fe212617a8" style="display: none" value="7a6777fc-3a21-4007-a7af-c9fe212617a8">
                   <div class="danddtitle" id="dandd" style="
    margin-top: 25px;
"><img src="img/upload.png" width="150" alt="upload"></div>
                   <div class="dz-default dz-message"><span>Drop files here to upload</span></div>
        </form>
        
<form action="upload"
      class="toBeUploaded"
      id="my-awesome-dropzone" style="display: none;">

      <div id="holder" style="height: 100%;">
</div>
      </form>
<div class="greybutton" id="submitbutton" >Generate </div>
</div>
</div>

</div>
</div>
<%@include file="./Footer.jsp" %>
<script>

$("#submitbutton").click(function(e){
	var temp=$("#uniqueid").val();
	  window.location.href="./SpriteSuccess.jsp?"+"temp="+temp;
	
	});
$('#inputplatformselect').dropkick({
	  change: function (value, label) {
 
	   $("#inputplatform").siblings(".lbjs").children(".lbjs-list").children('.lbjs-item').each(function( ) {
		   if(label=='Other'){
			    $("#selectDevice").attr('style','display:none');
				$("#inputplatform").siblings(".lbjs").attr('style','display:none');
			   
				$("#inputplatform").siblings(".widAndHeight").attr('style','display:block');

			   }else if($(this).attr('platform')==label){
				   $("#selectDevice").attr('style','display:block');
					$("#inputplatform").siblings(".lbjs").attr('style','display:block');
					$("#inputplatform").siblings(".widAndHeight").attr('style','display:none');
					   
			   $(this).attr('style','display:block');
			   }else { 

				   $(this).attr('style','display:none');

				   };
		   });

}

	
});
$('#outputplatformselect').dropkick({
	  change: function (value, label) {

	   $("#outputplatform").siblings(".lbjs").children(".lbjs-list").children('.lbjs-item').each(function( ) {

		   if(label=='Other'){
			   $("#selectDevice2").attr('style','display:none');
			   
				$("#outputplatform").siblings(".lbjs").attr('style','display:none');
			   
				$("#outputplatform").siblings(".widAndHeight").attr('style','display:block');

			   }else   if($(this).attr('platform')==label){
				   $("#selectDevice2").attr('style','display:block');
				   
					$("#outputplatform").siblings(".lbjs").attr('style','display:block');
					$("#outputplatform").siblings(".widAndHeight").attr('style','display:none');
					
			   $(this).attr('style','display:block');
			   }else{

				   $(this).attr('style','display:none');

				   };
		   });

}

	
});
$("#r2").click(function(){


$(".draganddroptitle").hide();
$(".singdraganddroptitle").show();
$(".toBeUploaded").show();
$(".dropzone").hide();


	
});$("#r1").click(function(){


	$(".draganddroptitle").show();
	$(".singdraganddroptitle").hide();

	$(".dropzone").show();
	$(".toBeUploaded").hide();


		
	});
$('.selectdevice').listbox();

var myDropzone = new Dropzone(".dropzone");

myDropzone.on("complete", function(file) {
	$('#submitbutton').css('background-image', 'url(./img/orangebutton.png)');
	$('#submitbutton').css('cursor', 'pointer');
	  document.getElementById("dandd").style.display="none";
	 



});
$(".toBeUploaded").dropzone({
    maxFiles: 1,
	
    maxfilesexceeded: function(file) {
        this.removeAllFiles();
        this.addFile(file);
    },
    "complete": function(file) {
    	
    
    	var width= $(".toBeUploaded").children(".dz-preview").children(".dz-details").children("img").css("width");
  	  var height= $(".toBeUploaded").children(".dz-preview").children(".dz-details").children("img").css("height");
  	var offset=$(".toBeUploaded").children(".dz-preview").children(".dz-details").children("img").offset();

  	$(".toBeUploaded").children(".dz-preview").children(".dz-details").css("width",width);
  	$(".toBeUploaded").children(".dz-preview").children(".dz-details").css("height",height);
addSVG(width,height,offset.left,offset.top);
$('#submitbutton').css('background-image', 'url(./img/orangebutton.png)');
$('#submitbutton').css('cursor', 'pointer');
    }
    
});

</script>

</body>

</html>
    