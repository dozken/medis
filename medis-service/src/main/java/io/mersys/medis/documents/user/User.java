package io.mersys.medis.documents.user;

import java.util.List;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;

import io.mersys.medis.documents.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Document("user")
@TypeAlias("User")
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity implements UserDetails {
    private List<Authority> authorities;
    private String username;
    private String password;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean isEnabled;
}
