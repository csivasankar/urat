<#import "/spring.ftl" as spring /> 
<@spring.bind "ruleDataFile" />
<div class="users form">
	<form action="<@spring.url "/ruledatafiles"/>" id="UpgradeDocumentAddForm" method="post" name="RuleDataFileUpdateForm">
		<div style="display:none;">
			<input type="hidden" value="PUT" name="_method">
		</div>
		<fieldset>
			<div class="input text required">
				<label for="RuleDocumentName">Name <span class="error">*</span></label>
				<@spring.formInput "ruleDataFile.name", 'maxlength="250" id="RuleDocumentName" class="span6 requiredField"'/>
			</div>
			<@spring.formHiddenInput "ruleDataFile.id"/>
		</fieldset>
	<div class="submit">
		<input id="submitBtn" class="btn" type="button" value="Submit" />
	</div>
	</form>
</div>
<script type="text/javascript">
$(document).ready(function(){
    $('#submitBtn').click(function(){
    	$('form[name=RuleDataFileUpdateForm]').submit();
	});
});
</script>