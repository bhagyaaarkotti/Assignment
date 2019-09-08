package LogData.Assignment;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;


import junit.framework.TestCase;

public class CreateLoggerTest extends TestCase {
	
	
	public void testWriteLogger() throws IOException {
		Employee emp = new Employee();
		emp.name = "TestWLogger";
		emp.Organization = "SYM";
		emp.salary=100;
		OrgDetails ordet = new OrgDetails();
		ordet.name="SYM";
		ordet.Address = "MountainView";
		File file = new File("/tmp/logdata");
		
		CreateLogger<BinaryLoggable> cl = new CreateLogger<BinaryLoggable>(file);
		cl.write(emp);
		cl.write(ordet);
	}
	
	@SuppressWarnings("unchecked")
	public void testReadLogger() throws IOException, InstantiationException, IllegalAccessException {
		
		Employee emp = new Employee();
		emp.name = "TestWLogger";
		emp.Organization = "SYM";
		emp.salary=100;
		OrgDetails<BinaryLoggable> ordet = new OrgDetails<BinaryLoggable>();
		ordet.name="SYM";
		ordet.Address = "MountainView";
		File file = new File("/tmp/logdata");
		
		CreateLogger<BinaryLoggable> cl = new CreateLogger<BinaryLoggable>(file);
		Iterator<?> it = cl.read(emp.getClass());
		Iterator<?> itorg = cl.read(ordet.getClass());
		while (it.hasNext()) {
			Employee empData = (Employee)it.next();
			assertEquals(empData.equals(emp),true);
			
		}
		while (itorg.hasNext()) {
			OrgDetails org = (OrgDetails)itorg.next();
			assertEquals(org.equals(ordet),true);
		}
		
	}

}
