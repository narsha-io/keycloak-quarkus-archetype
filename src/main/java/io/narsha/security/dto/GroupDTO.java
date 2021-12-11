package io.narsha.security.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class GroupDTO {

    private UUID id;
    private String name;
    private List<RoleDTO> roles;
}
