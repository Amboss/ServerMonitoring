

<#import "/util/spring.ftl" as spring />

<script src="<@spring.url "/js/jquery.validate.js"/>" type="text/javascript"></script>
	<script type="text/javascript">
  		$(document).ready(function(){
  			jQuery.extend(jQuery.validator.messages, {
    			required: "<@spring.message "validation.required" />",
    			maxlength: jQuery.validator.format("<@spring.message "validation.maxlength" />"),
    			minlength: jQuery.validator.format("<@spring.message "validation.minlength" />"),
			});
  		
    		$("#departmentForm").validate();
  		});
</script>