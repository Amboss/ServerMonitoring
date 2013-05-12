package serverMonitoring.controller.common;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: serge
 */

public abstract class AbstractController {

    protected Logger log = Logger.getLogger(this.getClass().getName());

    @Value( "#{applicationProperties['format.date']}" )
    protected String dateFormat;

    @InitBinder
    public void allowEmptyDateBinding( WebDataBinder binder ) {
        // Allow for null values in date fields.
        binder.registerCustomEditor( Date.class, new CustomDateEditor( new SimpleDateFormat( dateFormat), true ));
        // tell spring to set empty values as null instead of empty string.
        binder.registerCustomEditor( String.class, new StringTrimmerEditor( true ));
    }
}
