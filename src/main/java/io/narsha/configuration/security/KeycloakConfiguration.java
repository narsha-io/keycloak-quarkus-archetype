package io.narsha.configuration.security;

import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

@Dependent
public class KeycloakConfiguration {

    @Produces
    public RealmResource realmResource() {
        var keycloak = KeycloakBuilder.builder()
                .serverUrl("http://localhost:8181/auth")
                //.realm("quarkus-example-realm")
                .realm("master")
                .username("admin")
                .password("admin")
                .clientId("admin-cli")
                .build();

        return keycloak.realm("quarkus-example-realm");
    }
}
