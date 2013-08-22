package org.isb.ivr.flow.user;

import java.io.Serializable;

public class InputCB implements Serializable {

	private static final long serialVersionUID = 6481383232511393049L;
	
	private boolean migrated;
	private String securityLevel;
	
	public InputCB() {
		this.setMigrated(false);
		this.setSecurityLevel("L2");
	}

	public boolean isMigrated() {
		return migrated;
	}

	public void setMigrated(boolean migrated) {
		this.migrated = migrated;
	}

	public String getSecurityLevel() {
		return securityLevel;
	}

	public void setSecurityLevel(String securityLevel) {
		this.securityLevel = securityLevel;
	}
}
