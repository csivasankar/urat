(function($){
	$('a.ajaxModal').on('click',function(e){
        e.preventDefault();
        $('#ModalWin').modal();
        //console.log(e.target);
        $('#data_target_modal').html('');
        $('#loading_img').show();
        //console.log('modal');
        $('#modalTitle').text($(this).data("title"));
        var params = {ajax:true};
        $.ajax({
			  url: this.href,
			  data : params,
			  success : function(content){ 
				  //console.log('dialog');
				  $('#loading_img').hide();
				  $('#data_target_modal').html(content);
				  setTimeout(function() {
					  $('#data_target_modal').find("input[type=text]:first").focus();
				  }, 500);
			  },
			  error : function(){
				  $('#loading_img').hide();
				  $('#data_target_modal').addClass('text-error').html('<b>Error while loading the content.</b>');
			  }
		});
        
    });  
	$('#ModalWin').on('show',function(e){
		//console.log('show');
	});
    $('#ModalWin').on('shown',function(e){
    	//console.log('shown');
		$(this).find("input[type=text]:first").focus();
    });
    $('#ModalWin').on('hidden', function (e) {});
    $("#ModalWin").keypress(function(event) {
        if (event.which == 13 || event.keyCode == 13) {
                $('#submitBtn').trigger('click');
                return false;
        }
    });
})(jQuery);
var checkRequiredFields = function() {
	var upload = false;
	$.each($(".requiredField"), function() {
		if ($.trim($(this).val()).length > 0) {
			upload = true;
		} else {
			upload = false;
			return false;
		}
	});
	if (upload) {
		if ($("#page_type").val() === 'document_c' || $("#page_type").val() === 'document_d') {
			validateName();
		} else {
			$(".qq-uploader").css("visibility", "visible");
		}
	} else {
		$(".qq-uploader").css("visibility", "hidden");
		$(".error-msg").html("&nbsp;");
	}
};
var checkNameField = function() {
	var upload = false;
	if ($.trim($('#name').val()).length > 0) {
		upload = true;
	} else {
		upload = false;
	}
	if (upload) {
		$(".qq-uploader").css("visibility", "visible");
	} else {
		$(".qq-uploader").css("visibility", "hidden");
	}
}