package com.sale.app.config.swagger;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;

class SwaggerEnabledCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String swaggerEnabledConfig = context.getEnvironment().getProperty("swagger.enabled");
        if (StringUtils.isEmpty(swaggerEnabledConfig)) {
            return false;
        }
        return Boolean.parseBoolean(swaggerEnabledConfig);
    }
}
