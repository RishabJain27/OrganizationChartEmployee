package Equinix.OrgChartEmployee;

//Object for Organization file
public class Organization {
		private String title;
		private String organization;
		private String id;

		
		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}
		
		public String getOrganization() {
			return organization;
		}

		public void setOrganization(String organization) {
			this.organization = organization;
		}		
		
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		@Override
		public String toString() {
			return "{" + title + "::" + organization + "::" + id + "}";
		}

}
