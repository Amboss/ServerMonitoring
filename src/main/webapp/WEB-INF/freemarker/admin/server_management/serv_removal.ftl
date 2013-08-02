<#-- =====================================================================
     Server delete page

     ===================================================================== -->

<#import "/layout/simple.ftl" as com>

<#import "/util/spring.ftl" as spring />

<@com.page title="Server delete">
        <div class="modal" id="employee_removal" tabindex="-1" role="dialog"
                 aria-labelledby="myModalLabel" aria-hidden="true" >

            <form class="modal-body" method="post">
                <h3>Are you shore you want to delete this server?</h3>
                <p>Name: ${server.server_name}</p>
                <p>Address: ${server.address}</p>
                <p>URL: ${server.url}</p>
                <p>Created: ${server.created}</p>

                <input class='btn btn-primary'
                        type='submit'
                        name='delete'
                        value='Delete server' />
                <input class="btn"
                        data-dismiss='modal'
                        type='submit'
                        aria-hidden='true'
                        name='cancel'
                        value='Cancel' />
             </form>
        </div>
</@com.page>