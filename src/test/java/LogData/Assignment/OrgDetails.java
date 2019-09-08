package LogData.Assignment;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class OrgDetails<T extends BinaryLoggable> implements BinaryLoggable{
	String name;
	String Address;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Address == null) ? 0 : Address.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		OrgDetails other = (OrgDetails) obj;
		if (Address == null) {
			if (other.Address != null)
				return false;
		} else if (!Address.equals(other.Address))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public byte[] toBytes() throws IOException {
		
		String data = this.name+","+this.Address;
	
		return data.getBytes();
	}
	public void fromBytes(byte[] rawBytes) throws IOException {
		String results [] = (new String(rawBytes, StandardCharsets.UTF_8)).split(",");
		this.name = results[0];
		this.Address = results[1];
		
	}
}
