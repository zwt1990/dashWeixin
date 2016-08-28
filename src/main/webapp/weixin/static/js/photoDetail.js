/**
 * jiangyukun on 16/6/19.
 */
+function (window, undefined) {
    'use strict';

    var _ = window._;
    var $ = window.$;

    var $swiperWrapper = $('.swiper-wrapper');
    var dataFormContailer=$('#dataForm');
    var _sliderTemplate = _.template($('#sliderTemplate').html());
    var _fromDataTemplate = _.template($('#fromDataTemplate').html());
    


    function fetchDataFromServer() {
    	   $.ajax({
    	   	     type: 'POST',
    	        url: '../wxphoto/getTypePhotoExt' ,
    	       data: {
    	    	   pConfigId:getQueryString("id")
    	       } ,
    	       dataType: 'json',
    	       success: function(data){
    	    	   buildData(data);
    	   	    } 
    	   	});
    	
       
    }

    function buildData(data){
    	 var sliderList = [];
    	 var slilerData=data.sliderExts;
         for (var i = 0; i < slilerData.length; i++) {
             var slider = {
                 sliderImageUrl: "../sys/static"+slilerData[i].extValue
             };
             sliderList.push(slider);
         }
         showSlider(sliderList);
         showFormData(data.formExts);
         if(data.descImgExts.length>0){
        	 $("#descImg").attr("src","../sys/static"+data.descImgExts[0].extValue)
         }
        
    }
    
    function showSlider(sliderList) {
        for (var i = 0; i < sliderList.length; i++) {
            var slider = sliderList[i];
            var sliderHtml = _sliderTemplate({
                sliderImageUrl: slider.sliderImageUrl
            });
            $swiperWrapper.append(sliderHtml);
        }
        new Swiper('.swiper-container', {
            pagination: '.swiper-pagination',
            paginationClickable: true
        });
    }

    function showFormData(formDataList){
    	 for (var i = 0; i < formDataList.length; i++) {
             var formData = formDataList[i];
             var formDataHtml = _fromDataTemplate({
            	 extKey: formData.extKey,
            	 extValue: formData.extValue
             });
             dataFormContailer.append(formDataHtml);
         }
    }
    fetchDataFromServer();
    
}(window);