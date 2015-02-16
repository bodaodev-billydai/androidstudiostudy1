package wenxin.billy.bean;

import org.eclipse.jetty.util.annotation.ManagedAttribute;
import org.eclipse.jetty.util.annotation.ManagedObject;
import org.eclipse.jetty.util.annotation.ManagedOperation;
import org.eclipse.jetty.util.annotation.Name;

@ManagedObject("Test MBean Annotations")
public class SampleBean {
	String fname = "Full Name";

	@ManagedAttribute(value = "The full name of something", name = "fname")
	public String getFullName() {
		return fname;
	}

	public void setFullName(String name) {
		fname = name;
	}

	@ManagedOperation("Doodle something")
	public void doodle(
			@Name(value = "doodle", description = "A description of the argument") String doodle) {
		System.err.println("doodle " + doodle);
	}
}