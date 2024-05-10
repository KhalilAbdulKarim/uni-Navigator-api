//package com.uninavigatorspring;
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.databind.JsonSerializer;
//import com.fasterxml.jackson.databind.SerializerProvider;
//import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
//import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
//import org.hibernate.proxy.HibernateProxy;
//import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
//
//import java.io.IOException;
//
//@Configuration
//public class JacksonConfig {
//
//    @Bean
//    public Jackson2ObjectMapperBuilderCustomizer jacksonCustomizer() {
//        return builder -> {
//            builder.filters(new SimpleFilterProvider().setFailOnUnknownId(false));
//            builder.featuresToDisable(com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS);
//            builder.serializerByType(HibernateProxy.class, new JsonSerializer<HibernateProxy>() {
//                @Override
//                public void serialize(HibernateProxy value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
//                    if (value.getHibernateLazyInitializer().isUninitialized()) {
//                        gen.writeNull();
//                    } else {
//                        Object deproxied = value.getHibernateLazyInitializer().getImplementation();
//                        serializers.defaultSerializeValue(deproxied, gen);
//                    }
//                }
//            });
//        };
//    }
//
//
//}
