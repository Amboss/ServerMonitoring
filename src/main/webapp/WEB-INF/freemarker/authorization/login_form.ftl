[#ftl]
[#if username??]
    <form action="<@spring.url value="/j_spring_security_logout"/>" class="navbar-form pull-right" method="POST">
          <button type="submit" class="btn">Sign out</button>
    </form>
[#else]
      <form action="<@spring.url value="../j_spring_security_logout"/>" class="navbar-form pull-right" method="POST">
           [#include "./common/message/login_message.ftl"]
           <table>
               <tr>
               <#if Session.SPRING_SECURITY_LAST_EXCEPTION?? && Session.SPRING_SECURITY_LAST_EXCEPTION.message?has_content>
                   <@spring.message "login.bad.credentials"/>
               </#if>
                   <td><label class="span2" for="j_username">Username</label></td>
                   <td><@spring.formInput "loginForm.username","placeholder='username'" />
                       <@spring.showErrors "loginForm.username","error" />
                   <input id="j_username" name="j_username" size="20" maxlength="50" type="text"/></td>
               </tr>
               <tr>
                   <td><label class="log_pass" for="j_password">Password</label></td>
                   <td><input id="j_password" name="j_password" size="20" maxlength="50" type="password"/></td>
                      <@spring.showErrors "loginForm.password","error" />
               </tr>
               <tr>
                   <td><input  type="submit" class="btn" value="Login"/><input  type="reset" class="btn" value="Cancel"/></td>
               </tr>
           </table>
       </form>
[/#if]