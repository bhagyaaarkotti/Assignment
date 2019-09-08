package LogData.Assignment;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Employee implements BinaryLoggable {
	String name;
	String Organization;
	int salary;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrganization() {
		return Organization;
	}
	public void setOrganization(String organization) {
		Organization = organization;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Organization == null) ? 0 : Organization.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + salary;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (Organization == null) {
			if (other.Organization != null)
				return false;
		} else if (!Organization.equals(other.Organization))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (salary != other.salary)
			return false;
		return true;
	}
	public byte[] toBytes() throws IOException {
		
		String data = this.name+","+this.Organization +"," + this.salary;
	
		return data.getBytes();
	}
	public void fromBytes(byte[] rawBytes) throws IOException {
		String results [] = (new String(rawBytes, StandardCharsets.UTF_8)).split(",");
		this.name = results[0];
		this.Organization = results[1];
		this.salary = Integer.parseInt(results[2]);
	}

}
