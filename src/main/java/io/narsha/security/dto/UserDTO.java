package io.narsha.security.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDTO {

    private String id;
    private String username;
    private String email;
    private Boolean enabled;
    private Boolean emailVerified;
    private String firstName;
    private String lastName;
    private List<GroupDTO> groups;
    private List<RoleDTO> roles;

}
