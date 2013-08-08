<#import "/spring.ftl" as spring />
<#setting date_format="MM-dd-yyyy HH:mm:ss">
<html>
<head>
<title>${itemType.description!} List</title>
</head>
<body>		
	<div class="items index">
		<div class="row"><div class="span6"><h2>${itemType.description!} List</h2></div><div class="span4 pull-right" id="pagination_div"></div>
		<div class="span2"></br><a href="<@spring.url "/items/add/${itemType.type!}"/>" class="btn btn-success pull-right ajaxModal" data-title="Add New ${itemType.description!}"><i class="icon-upload"></i> Add</a>
			</div>
		</div>
		<table class="table items-table" id="pagination">
			<thead>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Modified</th>
				<th class="actions">Actions</th>
			</tr>
			</thead>
			<tbody>
			<#if itemList??>
			<#list itemList as item>
				<#if item??>
					<tr>  
						<td>${item.id?c!}</td>
						<td>${item.name!}</td>
						<td><#if item.modified??>${item.modified.time?date}</#if></td>
						<td class="actions">
							<a href="<@spring.url "/items/edit/${itemType.type!}/${item.id?c}"/>" data-title="Edit ${itemType.description!}" class="btn btn-primary ajaxModal"><i class="icon-pencil icon-white "></i> Edit</a>
							<form method="post" id="item_${item.id?c}" name="item_${item.id?c}" style="display:none;" action="<@spring.url "/items/${itemType.type!}/${item.id?c}"/>">
							<input type="hidden" value="DELETE" name="_method">
							</form>
							<a href="#" onclick="if(confirm('Are you sure you want to delete ${item.name}?')){document.item_${item.id?c}.submit();} event.returnValue = false; return false;" class="btn btn-danger">
							<i class="icon-remove-circle icon-white"></i> Delete</a>
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