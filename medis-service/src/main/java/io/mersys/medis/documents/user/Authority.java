package io.mersys.medis.documents.user;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN"),
    ANONYMOUS("ANONYMOUS");

	private String value;

    @Override
    public String getAuthority() {
        return this.name();
    }
	   
	public String getValue() {
	       return this.value;
	}
	   
	Authority(String value) {
	       this.value = value;
	}
}
