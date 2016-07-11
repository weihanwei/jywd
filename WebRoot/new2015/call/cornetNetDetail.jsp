<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html manifest="../manifest/call/cornetNet.appcache">
<head>
   
</head>
<body>

</body>
<script src="../js/lib/seajs/seajs.js"></script>
<script type="text/javascript">
    seajs.use(['jquery','JSBridge','main'], function(a) {
        seajs.use("../js/"+SCRIPT_PATH+"/model/call/cornetNetDetail");
    });
</script>

</html>

