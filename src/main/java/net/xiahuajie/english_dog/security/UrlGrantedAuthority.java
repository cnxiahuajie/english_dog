package net.xiahuajie.english_dog.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class UrlGrantedAuthority implements GrantedAuthority {

    private static final long serialVersionUID = -1057850490085902931L;

    private String permissionUrl;

    @Override
    public String getAuthority() {
        // TODO Auto-generated method stub
        return null;
    }

}
