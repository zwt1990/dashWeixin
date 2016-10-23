/**
 * jiangyukun on 16/6/19.
 */
+function (window, undefined) {
    'use strict';

    var _ = window._;
    var $ = window.$;

    var $swiperWrapper = $('.swiper-wrapper');
    var $allCategoryContainer = $('.all-category-container');
    var $allProductContainer = $('.all-product-container');

    var _sliderTemplate = _.template($('#sliderTemplate').html());
    var _categoryItemTemplateText = _.template($('#categoryItemTemplate').html());
    var _twoProductTemplate = _.template($('#twoProductTemplate').html());
    var _productItemTemplate = _.template($('#productItemTemplate').html());




    function fetchDataFromServer() {
    	   $.ajax({
    	   	     type: 'POST',
    	        url: '../wxphoto/getImgconfigs' ,
    	       data: {
    	       		configId:getQueryString("id")
    	       } ,
    	       dataType: 'json',
    	       success: function(data){
    	    	   buildData(data)
    	   	    } 
    	   	});
    	
       
    }

    function buildData(data){
    	 var sliderList = [];
         for (var i = 0; i < data.cslImgs.length; i++) {
             var slider = {
                 link: data.cslImgs[i].url,
                 sliderImageUrl: "../sys/static"+data.cslImgs[i].path
             };
             sliderList.push(slider);
         }
         showSlider(sliderList);

         var categoryList = [];
         for (var i = 0; i < data.menuImgs.length; i++) {
             var categoryItem = {
                 imageUrl: "../sys/static"+data.menuImgs[i].path,
                 text: data.menuImgs[i].name,
                 link: data.menuImgs[i].url,
             };
             categoryList.push(categoryItem);
         }

         showCategoryList(categoryList);

         var productList = [];
         for (var i = 0; i < data.detailImgs.length; i++) {
             var productItem = {
                 imageUrl: "../sys/static"+data.detailImgs[i].path,
                 productName: data.detailImgs[i].name,
                 link: data.detailImgs[i].url,
                 price: 100.50,
                 id:data.detailImgs[i].id,
                 description: 'helo'
             };
             productList.push(productItem);
         }
         showProductList(productList);
    }
    
    function showSlider(sliderList) {
        for (var i = 0; i < sliderList.length; i++) {
            var slider = sliderList[i];
            var sliderHtml = _sliderTemplate({
                link: slider.link,
                sliderImageUrl: slider.sliderImageUrl
            });
            $swiperWrapper.append(sliderHtml);
        }
        new Swiper('.swiper-container', {
            pagination: '.swiper-pagination',
            paginationClickable: true
        });
    }

    function showCategoryList(categoryList) {
        for (var i = 0; i < categoryList.length; i++) {
            var categoryItem = categoryList[i];
            var categroyItemHtml = _categoryItemTemplateText({
                imageUrl: categoryItem.imageUrl,
                text: categoryItem.text,
                link: categoryItem.link
            });
            $allCategoryContainer.append(categroyItemHtml);
        }
    }

    function showProductList(productList) {
        for (var i = 0; i < productList.length; i += 2) {
            var productItem1 = productList[i];
            var productItem2 = productList[i + 1];

            var content1Html = getProductItemHtml(productItem1);
            var content2Html = getProductItemHtml(productItem2);

            var productItemHtml = _twoProductTemplate({
                content1: content1Html,
                content2: content2Html

            });

            $allProductContainer.append(productItemHtml);
        }
    }

    function getProductItemHtml(productItem) {
        if (!productItem) {
            return;
        }
        return _productItemTemplate({
            imageUrl: productItem.imageUrl,
            name: productItem.productName,
            link:'photoDetail.html?id='+ productItem.id,
            price: productItem.price,
        });
    }

    fetchDataFromServer();
}(window);