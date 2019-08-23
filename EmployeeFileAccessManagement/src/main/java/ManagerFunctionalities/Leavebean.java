package ManagerFunctionalities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "LeaveDetails")
@Table(name = "LeaveDetails")
public class Leavebean {
	@Id
	private int ID;
	private String Reason;
	private String Startdate;
	private int Days;
	private String status;
	private int managerid;

	public int getId() {
		return ID;
	}

	public void setId(int id) {
		this.ID = id;
	}

	public String getReason() {
		return Reason;
	}

	public void setReason(String reason) {
		this.Reason = reason;
	}

	public String getStartdate() {
		return Startdate;
	}

	public void setStartdate(String startdate) {
		this.Startdate = startdate;
	}

	public int getDays() {
		return Days;
	}

	public void setDays(int days) {
		this.Days = days;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getManagerid() {
		return managerid;
	}

	public void setManagerid(int managerid) {
		this.managerid = managerid;
	}

}
