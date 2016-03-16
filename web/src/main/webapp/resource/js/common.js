(function(){
	Object.prototype.list_page = function(pageNo, action){
		var frm = document.fm;
		frm.page.value=pageNo;
		frm.action = action;
		frm.submit();
	}
	Object.prototype.goSearch = function(action){
		var frm = document.fm;
		frm.page.value=1;
		frm.action = action;
		frm.submit();
	}
})()