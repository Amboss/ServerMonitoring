<#-- =====================================================================
     Employee delete page

     ===================================================================== -->

<#import "/layout/simple.ftl" as com>

<#import "/util/spring.ftl" as spring />

<@com.page title="Employee delete">

        <div class="modal" id="employee_removal" tabindex="-1" role="dialog"
                 aria-labelledby="myModalLabel" aria-hidden="true" >

            <form class="modal-body" method="post">
                <center>
                    <h3>Delete this employee?</h3>
                    <hr>
                    <p>Name: ${employee.employee_name}</p>
                    <p>Login: ${employee.login}</p>
                    <p>Created: ${employee.created}</p>
                    <hr>

                    <input class='btn btn-primary'
                            type='submit'
                            name='delete'
                            value='Delete employee' />
                    <input class="btn"
                            data-dismiss='modal'
                            type='submit'
                            aria-hidden='true'
                            name='cancel'
                            value='Cancel' />
                </center>
             </form>
        </div>
</@com.page>