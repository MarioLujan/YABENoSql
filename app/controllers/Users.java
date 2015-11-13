/**
 *
 * @author Mario Lujan
 */
package controllers;
 
import play.*;
import play.data.validation.*;
import play.mvc.*;

@Check("admin")
@With(Secure.class)
public class Users extends CRUD {    
}