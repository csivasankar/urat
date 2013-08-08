<#import "/spring.ftl" as spring />
<#setting date_format="MM-dd-yyyy HH:mm:ss">
<html>
<head>
<title>Upgrade Documents</title>
</head>
<body>
	<div class="categories index">
		<div class="row">
				<div class="span6"><h3>Upgrade Document Management</h3></div><div class="span4 pull-right" id="pagination_div"></div>
				<br/>
				<div class="row">
					<a class="btn btn btn-success pull-right ajaxModal"  data-title="Add New Upgrade Document" href="<@spring.url "/upgradedocs/add"/>">
					<i class="icon-upload"></i> Add New Document</a>
				</div>
		</div>
		<br/>
		<table class="table table-striped table-hover" id="pagination">
			<thead>
			<tr>
				<th>Document</th>
				<th>Name</th>
				<th>Modified Date</th>
				<th>Info</th>
				<th class="actions">Actions</th>
			</tr>
			</thead>
			<tbody>
				<#if documentList??>
				<#list documentList as document>
				<#if document??>
					<tr>
						<td><a href="${document.url}" target="_blank">${document.url}</a> </td>
						<td>${document.name!}</td>
						<td><#if document.modified??>${document.modified.time?date}</#if></td>						
						<td>${document.file.size!} MB</td>
						<td class="actions">
							<a href="${document.url}" target="_blank" title="View" class="btn btn btn-success"><i class="icon-eye-open"></i> </a> 
							<a class="btn btn-primary ajaxModal" title="Edit" data-title="Edit Document" href="<@spring.url "/upgradedocs/edit/${document.id?c}"/>">
							<i class="icon-pencil icon-white"></i> </a>
							<form method="post" id="file_${document.id?c}" name="file_${document.id?c}" style="display:none;" action="<@spring.url '/upgradedocs/${document.id?c}'/>">
							<input type="hidden" value="DELETE" name="_method">
							</form>
							<a href="#" title="Delete" onclick="if(confirm('Are you sure you want to delete ${document.name}?')){document.file_${document.id?c}.submit();} event.returnValue = false; return false;" class="btn btn-danger">
							<i class="icon-remove-circle icon-white"></i> </a>
							<form method="post" id="file_${document.id?c}" name="file_publish_${document.id?c}" style="display:none;" action="<@spring.url "/upgradedocs/publish/${document.id?c}?publish="/><#if document.published>false<#else>true</#if>">
							</form>
							<a href="#" onclick="document.file_publish_${document.id?c}.submit(); event.returnValue = false; return false;"  class="btn btn-warning">
								<i class="icon-edit"></i> <#if document.published> Unpublish <#else> Publish </#if> 
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