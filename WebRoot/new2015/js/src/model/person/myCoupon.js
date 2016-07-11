define(function(require) {
	jy.innertHead('我的电子优惠券');

	jy.innertFood(4);
});

//面板间的却换
function navShow(nav , show){
    $(nav).on("touchstart , click" , function(){
        //获取返回的当前下标值
        var navId = $(this).index();
        //所有导航移除样式on
        $(nav).removeClass("on");
        //当前导航添加样式on
        $(this).addClass("on");
        //隐藏所有的内容层
        $(show).addClass("hide");
        //显示当前的内容层
        $(show).eq(navId).removeClass("hide");
    });
}

//收缩隐藏层
function slideDom(obj, animobj){
    $(obj).on("touchstart , click", function(){
        var showDom = $(this).next();
        if(showDom.is(":hidden")){
            $(obj).addClass("BorderB");
            $(this).removeClass("BorderB");
            $(animobj).slideUp(500);
            showDom.slideDown(500);
        }

    });
}

//调用navShow
navShow(".myPriNav ul li", ".myShow");

//调用slideDom
slideDom(".myRecRedp" , ".myRecDetails");
slideDom(".combo dt" , ".combo dd");
