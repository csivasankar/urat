<#import "/spring.ftl" as spring />
<#setting date_format="MM-dd-yyyy HH:mm:ss">
<html>
<head>
<title>Rule Data Files</title>
</head>
<body>
	<div class="categories index">
		<div class="row">
				<div class="span6"><h3>Rule Data File Management</h3></div><div class="span4 pull-right" id="pagination_div"></div>
				<br/>
				<div class="row">
					<a class="btn btn btn-success pull-right ajaxModal"  data-title="Add Rule Data File" href="<@spring.url "/ruledatafiles/add"/>">
					<i class="icon-upload"></i> Add New File</a>
				</div>	
			</div>
		<br/>
		<table class="table table-striped table-hover" id="pagination">
			<thead>
			<tr>
				<th>File</th>
				<th>Name</th>
				<th>Modified Date</th>
				<th>Info</th>
				<th class="actions">Actions</th>
			</tr>
			</thead>
			<tbody>
				<#if ruleFileList??>
				<#list ruleFileList as ruleFile>
				<#if ruleFile??>
					<tr>
						<td><a href="${ruleFile.url}" target="_blank">${ruleFile.url}</a> </td>
						<td>${ruleFile.name!}</td>
						<td><#if ruleFile.modified??>${ruleFile.modified.time?date}</#if></td>						
						<td>${ruleFile.file.size!} MB</td>
						<td class="actions">
							<a href="${ruleFile.url}" target="_blank"  class="btn btn btn-success"><i class="icon-eye-open"></i> </a>
							<a class="btn btn-primary ajaxModal" title="Edit" data-title="Edit Rule Data File" href="<@spring.url "/ruledatafiles/edit/${ruleFile.id?c}"/>">
							<i class="icon-pencil icon-white"></i> </a>
							<form method="post" id="rulefile_${ruleFile.id?c}" name="rulefile_${ruleFile.id?c}" style="display:none;" action="<@spring.url "/ruledatafiles/${ruleFile.id?c}"/>">
							<input type="hidden" value="DELETE" name="_method">
							</form>
							<a href="#" onclick="if(confirm('Are you sure you want to delete ${ruleFile.name}?')){document.rulefile_${ruleFile.id?c}.submit();} event.returnValue = false; return false;;" class="btn btn-danger">
							<i class="icon-remove-circle icon-white"></i> </a>
							<form method="post" id="file_${ruleFile.id?c}" name="file_publish_${ruleFile.id?c}" style="display:none;" action="<@spring.url "/ruledatafiles/publish/${ruleFile.id?c}?publish="/><#if ruleFile.published>false<#else>true</#if>">
							</form>
							<a href="#" onclick="document.file_publish_${ruleFile.id?c}.submit(); event.returnValue = false; return false;"  class="btn btn-warning">
								<i class="icon-edit"></i> <#if ruleFile.published> Unpublish <#else> Publish </#if> 
							</a>
						</td>
					</tr>
				</#if>
				</#list>
				</#if>
			</tbody>
		</table>
</div>
</body>
</html>