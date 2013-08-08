<#import "/spring.ftl" as spring /> 
<@spring.bind "ruleDataFile" />
<div class="users form">
	<form id="RuleDocumentAddForm" method="post" accept-charset="utf-8" onkeyup="checkRequiredFields();" name="RuleDocumentAddForm">
		<fieldset>
			<div class="input text required">
				<label for="RuleDocumentName">Name <span class="error">*</span></label>
				<@spring.formInput "ruleDataFile.name", 'maxlength="250" id="RuleDocumentName" class="span6 requiredField"'/>
			</div>
			<input type="hidden" value="ruleDataFile" id="page_type" name="page_type"/>
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
				    allowedExtensions: ['txt','json'],
				    action: '<@spring.url "/upload/document"/>',
				    onComplete: function(id, fileName, responseJSON){
						window.location.href = '<@spring.url "/ruledatafiles"/>';
				    }
				});
	});
});
</script>