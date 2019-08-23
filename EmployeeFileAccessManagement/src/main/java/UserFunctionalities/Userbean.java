package UserFunctionalities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class Userbean {

	@Id
	int userid;
	String username;
	String password;
	String startdate;
	int firsttimelogin;
	int Grantedpermision;
	String address;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsrname() {
		return username;
	}

	public void setUsrname(String usrname) {
		this.username = usrname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public int getFirsttimelogin() {
		return firsttimelogin;
	}

	public void setFirsttimelogin(int firsttimelogin) {
		this.firsttimelogin = firsttimelogin;
	}

	public int getGrantedpermision() {
		return Grantedpermision;
	}

	public void setGrantedpermision(int grantedpermision) {
		Grantedpermision = grantedpermision;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Userbean [userid=" + userid + ", username=" + username + ", password=" + password + ", startdate="
				+ startdate + ", firsttimelogin=" + firsttimelogin + ", Grantedpermision=" + Grantedpermision
				+ ", address=" + address + "]";
	}

}
