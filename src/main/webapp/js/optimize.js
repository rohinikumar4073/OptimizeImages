function clearText(field){
if(field.defaultValue == field.value){
field.value = "";
}
else if(field.value == ""){
field.value = field.defaultValue;
}
}

function addSVG(width2,height2,x1,y1){
	
	
	  var paper = Raphael(x1, y1, parseInt(width2)+5, parseInt(height2)+5);

	    var rect = paper
	        .rect(0, 0, parseInt(width2), parseInt(height2))
	        .attr('fill', '#E6E6E6')
			.attr('fill-opacity', '0.5')
	        ;

	    // Add freeTransform
	    var ft = paper.freeTransform(rect);

	    // Hide freeTransform handles
	    ft.hideHandles();

	    // Show hidden freeTransform handles
	    ft.showHandles();

	    // Apply transformations programmatically
	    //ft.attrs.rotate = 45;

	    ft.apply();

	    // Remove freeTransform completely
	    ft.unplug();

	    // Add freeTransform with options and callback
	    ft = paper.freeTransform(rect, { keepRatio: false,boundary: { x: 0, y: 0, width: parseInt(width2), height: parseInt(height2) },drag:true,rotate:false,draw:"bbox"  }, function(ft, events) {
	        //console.log(ft.attrs);
	    });
     
	    // Change options on the fly
	    ft.setOpts({ keepRatio: false });
	
	
}