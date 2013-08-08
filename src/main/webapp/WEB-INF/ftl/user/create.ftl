<#import "/spring.ftl" as spring /> 
<@spring.bind "user" />
<div class="users form">
	<form id="UserAddForm" method="post" accept-charset="utf-8" action="<@spring.url "/users"/>" name="UserAddForm">
		<span class="text-error errorMessage" style="display:none;">Username may not be empty</span>
		<fieldset>
			<div class="input text required">
				<label for="UserUsername">Username</label>
				<@spring.formInput "user.username", 'maxlength="255" id="UserUsername" class="requiredField"'/>
			</div>
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
    	if($('#username').val().trim().length == 0){
			$(".errorMessage").html("Username may not be empty").show();
		} else {
			var params = {ajax:true,username:$('#username').val()};
			$.ajax({
				  url: '<@spring.url "/users/validateUser"/>',
				  type : 'POST',
				  data : params,
				  dataType : 'json',
				  success : function(data){
					 if(data.errMsg){
					 	$(".errorMessage").html(data.errMsg).show();
					 }else{
						 $('form[name=UserAddForm]').submit();
					 }
				  },
				  error : function(){ }
			});
		}
    });
});
</script>
