<#import "/spring.ftl" as spring /> 
<@spring.bind "document" />
<@spring.bind "categoryOptions" />
<div class="users form">
	<form id="DocumentAddForm" method="post" accept-charset="utf-8" name="DocumentAddForm">
		<fieldset>
			<div class="input text required">
				<label for="DocumentName">Name * <span class="text-error errorMessage" style="display:none;">may not be empty</span></label>
				<@spring.formInput "document.name", 'maxlength="250" id="DocumentName" onkeyup="checkRequiredFields();" class="span6 requiredField"'/>
			</div>
			<div class="row">
			<div class="input text span3">
				<label for="DocumentCategory">Category</label>
				<@spring.formSingleSelect "document.category", categoryOptions, 'class="span3"'/>
			</div>
			<div class="input text span3">
				<label for="DocumentStage">Stage</label>
				<@spring.formSingleSelect "document.stage", stageOptions, 'class="span3"'/>
			</div>
			</div>
			<div class="input text">
				<label for="DocumentDescription">Description</label>
				<@spring.formTextarea "document.description", 'maxlength="2000" id="DocumentDescription" class="span6"'/>
			</div>
			<input type="hidden" value="document_c" id="page_type" name="page_type"/>
		</fieldset>
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
						window.location.href = '<@spring.url "/"/>';
				    }
				});
	});
});
function validateName() {
	var params = {ajax:true,name:$('#name').val()};
	$.ajax({
		  url: '<@spring.url "/documents/validate"/>',
		  type : 'POST',
		  data : params,
		  dataType : 'json',
		  success : function(data){
			 if(data.errMsg){
			 	$(".errorMessage").html(data.errMsg).show();
			 	$(".qq-uploader").css("visibility", "hidden");
			 }
		  },
		  error : function(){ }
	});
}

function checkDropdown() {
	if ($("#category").val().length > 0 || $("#stage").val().length > 0) {
		$(".errorMessage").html("").show();
		$(".qq-uploader").css("visibility", "visible");
	} else {
		$(".errorMessage").html("Category or Stage is required").show();
	 	$(".qq-uploader").css("visibility", "hidden");
	}
}
</script>