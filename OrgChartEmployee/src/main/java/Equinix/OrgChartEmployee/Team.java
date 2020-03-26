package Equinix.OrgChartEmployee;

//Object for Team file
public class Team {
	private String manager;
	private String teamMember;
	
	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}
	
	public String getTeamMember() {
		return teamMember;
	}

	public void setOrganization(String teamMember) {
		this.teamMember = teamMember;
	}		
	
	@Override
	public String toString() {
		return "{" + manager + "::" + teamMember + "}";
	}
}
