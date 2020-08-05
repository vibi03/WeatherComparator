package utilities;

public enum ProtocolEnum {

	SECURED("http") {
		@Override
		public String retrieveProtocol() {
			return getProtocol() + "://";

		}
	},
	NON_SECURED("https") {
		@Override
		public String retrieveProtocol() {
			return getProtocol() + "://";
		}
	};
	
	private String protocol;
	
	ProtocolEnum(String protocol) {

		this.protocol = protocol;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	
	abstract public String retrieveProtocol();
}
