package io.narsha.security.web;

import io.narsha.security.dto.GroupDTO;
import io.narsha.security.dto.RoleDTO;
import io.narsha.security.dto.UserDTO;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
@Path("/admin")
@Produces(value = MediaType.APPLICATION_JSON)
public class AdminUsersResource {

    @Inject
    RealmResource adminKeycloakClient;

    @GET
    @Path("/users")
    public Collection<UserDTO> users() {
        return adminKeycloakClient.users().list().stream()
                .map(user -> {
                            var builder = UserDTO.builder();
                            applyUserRepresentation(user, builder);
                            return builder.build();
                        }
                ).collect(Collectors.toList());
    }

    @GET
    @Path("/users/{id}")
    public UserDTO user(@PathParam("id") UUID userId) {
        var userResource = adminKeycloakClient.users().get(userId.toString());
        var builder = UserDTO.builder();
        applyUserRepresentation(userResource.toRepresentation(), builder);

        var groups = userResource.groups().stream().map(group -> {
                    var groupBuilder = GroupDTO.builder();
                    applyGroupRepresentation(group, groupBuilder);
                    return groupBuilder.build();
                }
        ).collect(Collectors.toList());

        builder.groups(groups);

        var roles = userResource.roles().getAll().getRealmMappings().stream()
                .map(role -> {
                            var roleBuilder = RoleDTO.builder();
                            applyRoleRepresentation(role, roleBuilder);
                            return roleBuilder.build();
                        }
                ).collect(Collectors.toList());
        builder.roles(roles);

        return builder.build();
    }

    @GET
    @Path("/groups")
    public List<GroupDTO> groups() {
        return adminKeycloakClient.groups().groups().stream()
                .map(group -> {
                    var groupBuilder = GroupDTO.builder();
                    applyGroupRepresentation(group, groupBuilder);
                    return groupBuilder.build();
                }).collect(Collectors.toList());
    }

    @GET
    @Path("/groups/{id}")
    public GroupDTO group(@PathParam("id") UUID id) {
        var groupResource = adminKeycloakClient.groups().group(id.toString());
        var group = groupResource.toRepresentation();

        var builder = GroupDTO.builder();
        applyGroupRepresentation(group, builder);

        var roles = groupResource.roles().getAll().getRealmMappings().stream()
                .map(role -> {
                            var roleBuilder = RoleDTO.builder();
                            applyRoleRepresentation(role, roleBuilder);
                            return roleBuilder.build();
                        }
                ).collect(Collectors.toList());

        builder.roles(roles);

        return builder.build();
    }


    @GET
    @Path("/groups/{id}/users")
    public List<UserDTO> groupUsers(@PathParam("id") UUID id) {
        return adminKeycloakClient.groups().group(id.toString()).members().stream().map(
                user -> {
                    var builder = UserDTO.builder();
                    applyUserRepresentation(user, builder);
                    return builder.build();
                }
        ).collect(Collectors.toList());
    }

    /* move to mapstruct */
    private void applyUserRepresentation(UserRepresentation user, UserDTO.UserDTOBuilder builder) {
        builder.id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .emailVerified(user.isEmailVerified())
                .enabled(user.isEnabled());
    }

    private void applyGroupRepresentation(GroupRepresentation group, GroupDTO.GroupDTOBuilder builder) {
        builder.id(UUID.fromString(group.getId()))
                .name(group.getName());
    }

    private void applyRoleRepresentation(RoleRepresentation role, RoleDTO.RoleDTOBuilder builder) {
        builder.name(role.getName());
    }
}
