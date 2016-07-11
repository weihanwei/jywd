function access(){
	
	var AGENTNO=sys.getHttpParam("AGENTNO");
	
	var tcid=sys.getHttpParam("id");
	
	if(AGENTNO!=null&&tcid!=null){
		
		$.ajax({
	        url: sys.getBaseUrl() + "toBeShopManager/access.do",
	        type: "post",
	        asyne: true,
	        dataType: "json",
	        data: {tcid:tcid,AGENTNO:AGENTNO},
	        success: function (data) {
				   
	        }
	    });
		
	}
	
}