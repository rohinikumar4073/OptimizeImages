
<div id="page">
<div id="banner" class="banner">
<div class="banner-container">
<img src="img/logo.png"/>

</div>


</div>
<div class="seperator">

</div>
<div class="tab-container">
<div class="tabs">
 <span id="tab1" class="tab" style="padding-left:0px;"> Sprite Generator</span>
 <span id="tab3" class="tab"> Mobile Image Convertor</span>
</div>
</div>

<script>
var pathname = window.location.pathname;
if(pathname=="/Optimize/MobileImageConvertor.jsp"){
	$("#tab3").attr('class', 'selected');
}else if(pathname=="/Optimize/index.jsp"){
	$("#tab1").attr('class', 'selected');

	
}
$(document).ready(function(e) {
    $(".tab").click(function(e){
		$(this).attr('class', 'selected')
		$(this).siblings().attr('class', 'tab');
		});
	
	$("#demo-upload").click(function(e){
		  $(".danddtitle").hide();
		
		});
	$("#tab1").click(function(e){
       window.location.href="./index.jsp";
		});
	$("#tab2").click(function(e){
	       window.location.href="./UnderConstruction.jsp";
			});
	$("#tab3").click(function(e){
	       window.location.href="./MobileImageConvertor.jsp";
			});
	$("#tab4").click(function(e){
	       window.location.href="./UnderConstruction.jsp";
			});
	$("#tab5").click(function(e){
	       window.location.href="./UnderConstruction.jsp";
			});
	
});
</script>
    