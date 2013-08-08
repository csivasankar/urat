<#import "/spring.ftl" as spring /> 
<div class="users form">
	<form id="UpgradeDocumentAddForm" method="post" accept-charset="utf-8" name="UpgradeDocumentAddForm">
		<fieldset>
			<div class="input text required">
				<label for="UpgradeDocumentName">Name * <span class="text-error errorMessage" style="display:none;">may not be empty</span></label>
				<@spring.formInput "document.name", 'maxlength="250" id="DocumentName" onkeyup="checkRequiredFields();" class="span6 requiredField"'/>
			</div>
		</fieldset>
		<input type="hidden" value="document_d" id="page_type" name="page_type"/>
	</form>
	<div class="upload" style="position: relative; overflow: hidden; direction: ltr;"></div>
</div>
<script type="text/javascript">
$(function() {
	var uploader = [];
    $('.upload').each(function(i){
		 var id = $(this).attr('rel');
		 uploader[i] = new qq.FileUploader({
				    element:this,
				    multiple: false,
				    allowedExtensions: ['pdf','avi','mp4'],
				    action: '<@spring.url "/upload/document"/>',
				    onComplete: function(id, fileName, responseJSON){
						window.location.href = '<@spring.url "/upgradedocs"/>';
				    }
				});
	});
});
function validateName() {
	var params = {ajax:true,name:$('#name').val()};
	$.ajax({
		  url: '<@spring.url "/upgradedocs/validate"/>',
		  type : 'POST',
		  data : params,
		  dataType : 'json',
		  success : function(data){
			 if(data.errMsg){
			 	$(".errorMessage").html(data.errMsg).show();
			 	$(".qq-uploader").css("visibility", "hidden");
			 }else{
				 $(".errorMessage").html("").show();
				 $(".qq-uploader").css("visibility", "visible");
			 }
		  },
		  error : function(){ }
	});
} 
</script>