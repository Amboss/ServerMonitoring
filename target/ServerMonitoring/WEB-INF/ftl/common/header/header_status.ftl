[#ftl]
[#-- this is header status --]
[#--leng changing  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!--]
<form action="<@spring.url value="/j_spring_security_logout"/>" class="navbar-form pull-right" method="POST">
    <button type="submit" class="btn">Logout</button>
</form>
[@security.authorize ifAnyGranted="ROLE_ADMIN"]
    Hello Admin: ${username}
[/@security.authorize]
[@security.authorize ifAnyGranted="ROLE_USER"]
    Hello: ${username}
[/@security.authorize]