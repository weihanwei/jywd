// JavaScript Document

function encrypt(str,key){
	var tempStr = '';
	var keyIndex = 0;
		for (var x = 0; x < str.length; x++) {
			tempStr += String.fromCharCode(str.charCodeAt(x) ^ key.charCodeAt(keyIndex));
			if (++keyIndex == key.length) {
				keyIndex = 0;
			}
		}
	var debytes = $.base64.encode(tempStr);
	return debytes;
}

function decrypt(str,key){
	var newStr =  $.base64.decode(str);
	var tempStr = '',keyIndex=0;
	for (var x = 0; x < newStr.length; x++) {
			tempStr += String.fromCharCode(newStr.charCodeAt(x) ^ key.charCodeAt(keyIndex));
			if (++keyIndex == key.length) {
				keyIndex = 0;
			}
		}
	return tempStr;
}