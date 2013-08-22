<#-- =====================================================================
     Employee delete page

     ===================================================================== -->

<#import "/layout/simple.ftl" as com>

<#import "/util/spring.ftl" as spring />

<#assign pageTitle><@spring.message "empl_update.title" /></#assign>

<@com.page title="${pageTitle}">
    <div class="modal" id="employee_removal" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabel" aria-hidden="true" >
        <form class="modal-body" method="post">
            <center>
                <h3><@spring.message "empl_delete.form.title" /></h3>
                <hr>
                <p><@spring.message "empl_delete.name" />: ${employee.employee_name}</p>
                <p><@spring.message "empl_delete.login" />: ${employee.login}</p>
                <p><@spring.message "empl_delete.created" />: ${employee.created}</p>
                <hr>

                <input class='btn btn-primary'
                        type='submit'
                        name='delete'
                        value='<@spring.message "empl_delete.btn_delete" />' />
                <input class="btn"
                        data-dismiss='modal'
                        type='submit'
                        aria-hidden='true'
                        name='cancel'
                        value='<@spring.message "empl_delete.btn_cancel" />' />
            </center>
         </form>
    </div>
</@com.page>