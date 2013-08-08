<#import "/spring.ftl" as spring />
<@spring.bind "item" />
<div class="items form">
	<form action="<@spring.url "/items/${type!}"/>" id="ItemUpdateForm" method="POST" enctype="application/x-www-form-urlencoded" accept-charset="utf-8" name="ItemUpdateForm">
		<div style="display:none;">
			<input type="hidden" value="PUT" name="_method">
		</div>
		<fieldset>
			<div class="input text">
				<label for="ItemName">Name</label>
				<@spring.formInput "item.name", 'maxlength="150" class="requiredField"'/>
				<span class="text-error errorMessage" style="display:none;">may not be empty</span>
			</div>
			<@spring.formHiddenInput "item.id"/>
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
				  url: '<@spring.url "/items/validateItem"/>',
				  type : 'POST',
				  data : params,
				  dataType : 'json',
				  success : function(data){
					 if(data.errMsg){
					 	$(".errorMessage").html(data.errMsg).show();
					 }else{
						 $('form[name=ItemUpdateForm]').submit();
					 }
				  },
				  error : function(){ }
			});
		}
    });
});
</script>