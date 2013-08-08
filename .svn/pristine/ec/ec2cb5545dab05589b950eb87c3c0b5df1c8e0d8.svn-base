<#import "/spring.ftl" as spring /> 
<@spring.bind "document" />
<@spring.bind "categoryOptions" />
<div class="users form">
	<form action="<@spring.url "/documents"/>" id="DocumentUpdateForm" method="post" name="DocumentUpdateForm">
		<div style="display:none;">
			<input type="hidden" value="PUT" name="_method">
		</div>
		<fieldset>
			<div class="input text required">
				<label for="DocumentName">Name * <span class="text-error errorMessage" style="display:none;">may not be empty</span></label>
				<@spring.formInput "document.name", 'maxlength="250" id="DocumentName" class="span6 requiredField"'/>
			</div>
			<div class="row">
			<div class="input text span3">
				<label for="DocumentCategory">Category</label>
				<@spring.formSingleSelect "document.category", categoryOptions, 'class="span3 categorySelect"'/>
			</div>
			<div class="input text span3">
				<label for="DocumentStage">Stage</label>
				<@spring.formSingleSelect "document.stage", stageOptions, 'class="span3 stageSelect"'/>
			</div>
			</div>
			<div class="input text">
				<label for="DocumentDescription">Description</label>
				<@spring.formTextarea "document.description", 'maxlength="2000" id="DocumentDescription" class="span6"'/>
			</div>
			<@spring.formHiddenInput "document.id"/>
			<@spring.formHiddenInput "document.fileType"/>
			<@spring.formHiddenInput "document.documentType"/>
		</fieldset>
		<div class="submit">
			<input id="submitBtn" class="btn" type="button" value="Submit" />
		</div>
	</form>
</div>
<script type="text/javascript">
$(document).ready(function(){
	<#if document.category??>
    $(".categorySelect option[value=${document.category.id?c}]").attr('selected', 'selected');
    </#if>
    <#if document.stage??>
    $(".stageSelect option[value=${document.stage.id?c}]").attr('selected', 'selected');
    </#if>
    $('#submitBtn').click(function(){
    	$(".errorMessage").html("");
    	if($('.requiredField').val().trim().length == 0){
			$(".errorMessage").html("may not be empty").show();
		} else {
			var params = {ajax:true,name:$('#name').val(), id:$('#id').val()};
			$.ajax({
				  url: '<@spring.url "/documents/validate"/>',
				  type : 'POST',
				  data : params,
				  dataType : 'json',
				  success : function(data){
					 if(data.errMsg){
					 	$(".errorMessage").html(data.errMsg).show();
					 }else{
						 $('form[name=DocumentUpdateForm]').submit();
					 }
				  },
				  error : function(){ }
			});
		}
	});
});
</script>