<#import "/spring.ftl" as spring />
<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner ">
			<div class="mynav">
			<a class="brand" href="#"><img class="logo" src="<@spring.url "/resources/images/ciscologo.png"/>"/></a>
			<ul class="nav" style="margin-top: 16px;">
				<li><a href="<@spring.url "/documents"/>">Home</a></li>
				<li class="dropdown"><a data-toggle="dropdown"
					class="dropdown-toggle" href="#">Upgrade Collateral <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="<@spring.url "/documents"/>">List Collateral Documents</a></li>
						<li><a href="<@spring.url "/documents/add"/>" class="ajaxModal" data-title="Add Collateral Document">Add Collateral Document</a></li>
					</ul>
				</li>
				<li class="dropdown"><a data-toggle="dropdown"
					class="dropdown-toggle" href="#">Rule Data Files<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="<@spring.url "/ruledatafiles"/>">List Rule Data Files</a></li>
						<li><a href="<@spring.url "/ruledatafiles/add"/>" class="ajaxModal" data-title="Add Rule Data File">Add Rule Data File</a></li>
						<li><a href="<@spring.url "/ruledatafiles/uploadruledata"/>" class="ajaxModal" data-title="Upload Rule Data File">Upload Rule Data File</a></li>
					</ul>
				</li>
				<li class="dropdown"><a data-toggle="dropdown"
					class="dropdown-toggle" href="#">Upgrade Documents<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="<@spring.url "/upgradedocs"/>">List Upgrade Documents</a></li>
						<li><a href="<@spring.url "/upgradedocs/add"/>" class="ajaxModal" data-title="Add Upgrade Document">Add Upgrade Document</a></li>
						<li><a href="<@spring.url "/upgradedocs/uploaddocumentdata"/>" class="ajaxModal" data-title="Upload Upgrade Document">Upload Upgrade Document</a></li>
					</ul>
				</li>
				<li class="dropdown"><a data-toggle="dropdown"
					class="dropdown-toggle" href="#">Manage<b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li class="dropdown-submenu">
    						<a class="dropdown-toggle" data-toggle="dropdown" href="#">Category</a>
    						<ul class="dropdown-menu">
         						<li><a href="<@spring.url "/items/category"/>">List Categories</a></li>
								<li><a href="<@spring.url "/items/add/category"/>" class="ajaxModal" data-title="Add New Category">Add New Category</a></li>
    						</ul>
						</li>
						<li class="dropdown-submenu">
    						<a class="dropdown-toggle" data-toggle="dropdown" href="#">Stage</a>
    						<ul class="dropdown-menu">
         						<li><a href="<@spring.url "/items/stage"/>">List Stages</a></li>
								<li><a href="<@spring.url "/items/add/stage"/>" class="ajaxModal" data-title="Add New Stage">Add New Stage</a></li>
    						</ul>
						</li>
						<li class="dropdown-submenu">
    						<a class="dropdown-toggle" data-toggle="dropdown" href="#">Rule Data File Type</a>
    						<ul class="dropdown-menu">
         						<li><a href="<@spring.url "/items/ruledatafile"/>">List Rule Data File Types</a></li>
								<li><a href="<@spring.url "/items/add/ruledatafile"/>" class="ajaxModal" data-title="Add Rule Data File Type">Add Rule Data File Type</a></li>
    						</ul>
						</li>
						<li class="dropdown-submenu">
    						<a class="dropdown-toggle" data-toggle="dropdown" href="#">Upgrade Document Type</a>
    						<ul class="dropdown-menu">
         						<li><a href="<@spring.url "/items/upgradedocument"/>">List Upgrade Document Types</a></li>
								<li><a href="<@spring.url "/items/add/upgradedocument"/>" class="ajaxModal" data-title="Add Upgrade Document Type">Add Upgrade Document Type</a></li>
    						</ul>
						</li>
					</ul>
				</li>
				<li class="dropdown"><a data-toggle="dropdown"
					class="dropdown-toggle" href="#">Users <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="<@spring.url "/users"/>">List Users</a></li>
						<li><a href="<@spring.url "/users/add"/>" class="ajaxModal" data-title="Add New User">Add New User</a></li>
					</ul>
				</li>
				<!--  <li><a href="<@spring.url "/resources/j_spring_security_logout"/>">Logout</a></li> -->
			</ul>
		</div>
	</div>
</div>