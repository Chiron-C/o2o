/**
 * 1,获取店铺的分类信息，所属区域
 * 2，提交页面的内容并保存
 * 
 */
$(function(){
	var initUrl="/o2o/shopadmin/getshopinitinfo";/*返回区域还有店铺类别的信息*/
	var registerShopUrl='/o2o/shopadmin/registershop';/*提交的地址*/
	getShopInitInfo();
	console.log($.getJSON(initUrl));
	//console.log(123);
	function getShopInitInfo(){
		$.getJSON(initUrl,function(data){
			if(data.success){				
				var tempHtml='';
				var tempAreaHtml='';
				data.shopCategoryList.map(function(item,index){
					tempHtml+='<option id="'+item.shopCategoryId+'">'+item.shopCategoryName+'</option>';
				});
				data.areaList.map(function(item,index){
					tempAreaHtml+='<option id="'+item.areaId+'">'+item.areaName+'</option>';
				});
				$('#shop-category').html(tempHtml);
				$('#area').html(tempAreaHtml);
			}
		});
	}
	$('#submit').click(function(){
		var shop={};
		shop.shopName=$('#shop-name').val();
		shop.shopAddr=$('#shop-addr').val();
		shop.phone=$('#shop-phone').val();
		shop.shopDesc=$('#shop-desc').val();
		shop.shopCategory={
			shopCategoryId:$('#shop-category').find('option').not(function(){
				return !this.selected;
			}).data('id')
		};
		shop.area={
			areaId:$('#area').find('option').not(function(){
				return !this.selected;
			}).data('id')	
		};
		var shopImg=$('#shop-img')[0].files[0];
		var formData=new FormData();
		formData.append('shopImg',shopImg);
		formData.append('shopStr',JSON.stringify(shop));
		$.ajax({
			url:registerShopUrl,
			type:'POST',
			timeout:200,
			async:true,
			cache:false,//不从缓存读数据
			date:formData,/*要发送给服务器的数据*/
			contentType:false,
			processData:false,
			cache:false,
			suncess:function(data){
				if(data.success){
					$.toast('提交成功');/*弹出一个信息*/
				}else{
					$.toast('提交失败!'+data.errMsg);
				}
			}
			
		});
	});
});