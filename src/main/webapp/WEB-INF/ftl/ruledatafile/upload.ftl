<#import "/spring.ftl" as spring /> 
<div class="users form">
	<form id="RuleDocumentAddForm" method="post" accept-charset="utf-8" name="RuleDocumentAddForm">
		<fieldset>
			<div>
				<label for="RuleDocumentName">Name <span class="error">*</span></label>
				<select class="span3" id="name" onchange="checkNameField();">
					<option value="" selected="selected">-- Select --</option>
					<#if ruledDataFileTypeList??>
						<#list ruledDataFileTypeList as item>
							<#if item??>
								<option value="${item.name!}">${item.name!}</option>
							</#if>
						</#list>
					</#if>
    			</select>
			</div>
		</fieldset>
		<input type="hidden" value="ruleDataFile" id="page_type" name="page_type"/>
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