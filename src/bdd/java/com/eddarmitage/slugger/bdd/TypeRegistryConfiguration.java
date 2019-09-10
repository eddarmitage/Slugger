package com.eddarmitage.slugger.bdd;

import io.cucumber.core.api.TypeRegistry;
import io.cucumber.core.api.TypeRegistryConfigurer;
import io.cucumber.cucumberexpressions.ParameterType;

import java.util.Locale;

public class TypeRegistryConfiguration implements TypeRegistryConfigurer {

    @Override
    public Locale locale() {
        return Locale.ENGLISH;
    }

    @Override
    public void configureTypeRegistry(TypeRegistry typeRegistry) {
        typeRegistry.defineParameterType(new ParameterType<>(
                "character",
                "\'.\'",
                Character.class,
                (String s) -> s.charAt(1))
        );

        typeRegistry.defineParameterType(new ParameterType<>(
                "quantity",
                "(an?)|[0-9]+",
                Integer.class,
                (String s) -> s.startsWith("a") ? 1 : Integer.parseInt(s))
        );

    }
}
