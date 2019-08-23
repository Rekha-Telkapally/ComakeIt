package AdminFunctionalities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Adminbean {
	@Id
	private int adminid;
	private String adminname;
	private String password;
	private int ReportingManager;
	private int LeaveStatus;

	public int getAdminid() {
		return adminid;
	}

	public void setAdminid(int i) {
		this.adminid = i;
	}

	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getReportingManager() {
		return ReportingManager;
	}

	public void setReportingManager(int reportingManager) {
		ReportingManager = reportingManager;
	}

	public int getLeaveStatus() {
		return LeaveStatus;
	}

	public void setLeaveStatus(int leaveStatus) {
		LeaveStatus = leaveStatus;
	}

}
