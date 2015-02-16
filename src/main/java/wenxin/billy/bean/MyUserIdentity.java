package wenxin.billy.bean;

import java.security.Principal;

import javax.security.auth.Subject;

import org.eclipse.jetty.server.UserIdentity;
import org.eclipse.jetty.util.annotation.ManagedObject;

@ManagedObject("User MBean Annotations")
public class MyUserIdentity implements UserIdentity {
	Subject mySubject;
	Principal myPrincipal;
	Scope myScope;
	public Subject getSubject() {
		return  mySubject;
	}

	public Principal getUserPrincipal() {
		return myPrincipal;
	}

	public boolean isUserInRole(String role, Scope scope) {
		return false;
	}

}
