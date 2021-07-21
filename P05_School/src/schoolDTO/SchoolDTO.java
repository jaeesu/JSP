package schoolDTO;

/*
 
create table school(
name varchar2(20) not null,
value varchar2(20),
code number
);
 */

public class SchoolDTO {
	private String name;
	private String value;
	private int code;
	
	public SchoolDTO() {
		
	}
	
	public SchoolDTO(String name, String value, int code) {
		this.name = name;
		this.value = value;
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
	
}
