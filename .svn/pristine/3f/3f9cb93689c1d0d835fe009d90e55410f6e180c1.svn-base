<#import "/spring.ftl" as spring /> 
<@spring.bind "document" />
<@spring.bind "collateralDataFileOptions" />
<div class="users form">
	<form action="<@spring.url "/upgradedocs"/>" id="UpgradeDocumentAddForm" method="post" name="UpgradeDocumentUpdateForm">
		<div style="display:none;">
			<input type="hidden" value="PUT" name="_method">
		</div>
		<fieldset>
			<div class="input text required">
				<label for="UpgradeDocumentName">Name * <span class="text-error errorMessage" style="display:none;">may not be empty</span></label>
				<@spring.formInput "document.name", 'maxlength="250" id="DocumentName" onkeyup="checkRequiredFields();" class="span6 requiredField"'/>
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
						 $('form[name=UpgradeDocumentUpdateForm]').submit();
					 }
				  },
				  error : function(){ }
			});
		}
	});
});
</script>