package com.api.teamplayer.base.util;

public final class Constants {
    public final static Integer TRUE_FLAG = 1;
    public final static String[] requiredSwaggerPaths = new String[]{
            "/v2/api-docs",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger.json",
            "/swagger-ui/index.html"};
    public final static String[] requiredGraphqlPaths = new String[]{
            "/vendor/**", "/graphiql/**", "/graphql/**"
    };
}