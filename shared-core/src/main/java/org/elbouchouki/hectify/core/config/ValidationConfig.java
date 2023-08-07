package org.elbouchouki.hectify.core.config;

import jakarta.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@Configuration(proxyBeanMethods = false)
public class ValidationConfig {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public ParameterNameDiscoverer parameterNameDiscoverer() {
        return new DefaultParameterNameDiscoverer();
    }

    @Bean("customValidatorFactory")
    public ValidatorFactory validatorFactory(ParameterNameDiscoverer parameterNameDiscoverer) {
        LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
        validatorFactoryBean.setParameterNameDiscoverer(parameterNameDiscoverer);
        return validatorFactoryBean;
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(
            @Qualifier("customValidatorFactory") ValidatorFactory validatorFactory
    ) {
        MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
        processor.setValidatorFactory(validatorFactory);
        return processor;
    }
}
