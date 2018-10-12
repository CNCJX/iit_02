<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="itemAddForm" class="itemForm" method="post">
	    <table cellpadding="5">
	        <tr>
	            <td>商品类目:</td>
	            <td>
					<%--链接按钮 - linkbutton  selectItemCat : 点击之后执行这个函数--%>
	            	<a href="javascript:void(0)" class="easyui-linkbutton selectItemCat">选择类目</a>
	            	<span ></span>
	            	<input type="hidden" name="cid" style="width: 280px;"></input>
	            </td>
	        </tr>
	        <tr>
	            <td>商品标题:</td>
	            <td><input class="easyui-textbox" type="text" name="title" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>商品卖点:</td>
	            <td><input class="easyui-textbox" name="sellPoint" data-options="multiline:true,validType:'length[0,150]'" style="height:60px;width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>商品价格:</td>
	            <td><input class="easyui-numberbox" type="text" name="priceView" data-options="min:1,max:99999999,precision:2,required:true" />
	            	<input type="hidden" name="price"/>
	            </td>
	        </tr>
	        <tr>
	            <td>库存数量:</td>
	            <td><input class="easyui-numberbox" type="text" name="num" data-options="min:1,max:99999999,precision:0,required:true" /></td>
	        </tr>
	        <tr>
	            <td>条形码:</td>
	            <td>
	                <input class="easyui-textbox" type="text" name="barcode" data-options="validType:'length[1,30]'" />
	            </td>
	        </tr>
	        <tr>
	            <td>商品图片:</td>
	            <td>
	            	 <a href="javascript:void(0)" class="easyui-linkbutton picFileUpload">上传图片</a>
	            	 <div class="pics"><ul></ul></div>
	                 <input type="hidden" name="image"/>
	            </td>
	        </tr>
	        <tr>
	            <td>商品描述:</td>
	            <td>
	                <textarea style="width:800px;height:300px;visibility:hidden;" name="desc"></textarea>
	            </td>
	        </tr>
	        <tr class="params hide">
	        	<td>商品规格:</td>
	        	<td>
	        		
	        	</td>
	        </tr>
	    </table>
	    <input type="hidden" name="itemParams"/>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
	</div>
</div>
<script type="text/javascript">
	//编辑器参数
	kingEditorParams = {
		filePostName  : "uploadFile",   
		uploadJson : '/rest/pic/upload',	
		dir : "image" 
	};
	
	var itemAddEditor ;

	//这是JQuery的入口函数， 页面加载完毕之后，就会执行这里面的方法
	$(function(){
		//创建富文本编辑器
		itemAddEditor = KindEditor.create("#itemAddForm [name=desc]", kingEditorParams);
		//初始化类目选择
		initItemCat();
		//初始化图片上传
		initPicUpload();
	});
	
	//提交商品信息到后台
	function submitForm(){
		//校验表单
		if(!$('#itemAddForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		$("#itemAddForm [name=price]").val(eval($("#itemAddForm [name=priceView]").val()) * 100);
		itemAddEditor.sync();
				
		//提交到后台的RESTful
		$.ajax({
		   type: "POST",
		   url: "/rest/item",
		   data: $("#itemAddForm").serialize(),
		   success: function(msg){
			   $.messager.alert('提示','新增商品成功!');
		   },
		   error: function(){
			   $.messager.alert('提示','新增商品失败!');
		   }
		});
	}
	
	function clearForm(){
		$('#itemAddForm').form('reset');
		itemAddEditor.html('');
	}
	
	//类目选择初始化
	function initItemCat(){
	    //根据class名称，找到刚才的那个 选择类目 的 标签， 然后绑定点击事件
		var selectItemCat = $(".selectItemCat");
   		selectItemCat.click(function(){

   		    //生成div， 然后在div里面生成ul  <div><ul></ul></div>
   			$("<div>").css({padding:"5px"}).html("<ul>")

				//生成窗口
   			.window({
   				width:'500',
   			    height:"450",
   			    modal:true,  //背景模糊
   			    closed:true,
   			    iconCls:'icon-save', //有一个保存的图标
   			    title:'选择类目',
   			    onOpen : function(){ //打开这个窗口触发的方法
   			    	var _win = this;

   			    	//在上面生成的ul里面添加树状内容
   			    	$("ul",_win).tree({
   			    		url:'/rest/item/cat',  //请求路径地址 树的内容来自于哪里。
   			    		method:'GET',
   			    		animate:true,
   			    		onClick : function(node){
   			    			if($(this).tree("isLeaf",node.target)){
   			    				// 填写到cid中
   			    				selectItemCat.parent().find("[name=cid]").val(node.id);
   			    				selectItemCat.next().text(node.text);
   			    				$(_win).window('close');
   			    			}
   			    		}
   			    	});
   			    },

				//关闭窗口触发的方法
   			    onClose : function(){
   			    	$(this).window("destroy");
   			    }
   			}).window('open'); //打开窗口
   		});
    }
	
	//图片上传初始化
	function initPicUpload(){
       	$(".picFileUpload").click(function(){
       		var form = $('#itemAddForm');
       		KindEditor.editor(kingEditorParams).loadPlugin('multiimage',function(){
       			var editor = this;
       			editor.plugin.multiImageDialog({
					clickFn : function(urlList) {
						$(".pics li").remove();
						var imgArray = [];
						KindEditor.each(urlList, function(i, data) {
							imgArray.push(data.url);
							$(".pics ul").append("<li><a href='"+data.url+"' target='_blank'><img src='"+data.url+"' width='80' height='50' /></a></li>");
						});
						form.find("[name=image]").val(imgArray.join(","));
						editor.hideDialog();
					}
				});
       		});
       	});
	}
	
</script>
