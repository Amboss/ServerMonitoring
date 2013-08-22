<#-- =====================================================================
     Server delete page

     ===================================================================== -->

<#import "/layout/simple.ftl" as com>

<#import "/util/spring.ftl" as spring />

<#assign pageTitle><@spring.message "server_delete.title" /></#assign>

<@com.page title="${pageTitle}">
    <div class="modal" id="employee_removal" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabel" aria-hidden="true" >

        <form class="modal-body" method="post">
            <center>
                <h3><@spring.message "server_delete.question" /></h3>
                <hr>
                <p><@spring.message "server_delete.name" />: ${server.server_name}</p>
                <p><@spring.message "server_delete.address" />: ${server.address}</p>
                <p><@spring.message "server_delete.url" />: ${server.url}</p>
                <p><@spring.message "server_delete.created" />: ${server.created}</p>
                <hr>
                <input class='btn btn-primary'
                        type='submit'
                        name='delete'
                        value='<@spring.message "server_delete.btn_delete" />' />
                <input class="btn"
                        data-dismiss='modal'
                        type='submit'
                        aria-hidden='true'
                        name='cancel'
                        value='<@spring.message "server_delete.btn_cancel" />' />
            </center>
         </form>
    </div>
</@com.page>