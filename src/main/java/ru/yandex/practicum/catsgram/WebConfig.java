package ru.yandex.practicum.catsgram;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;

@Configuration
public class WebConfig {

    @Bean
    public HttpMessageConverter<Object> createXmlHttpMessageConverter() {
        MarshallingHttpMessageConverter xmlConverter = new MarshallingHttpMessageConverter();
        XStreamMarshaller xStreamMarshaller = new XStreamMarshaller();

        xmlConverter.setMarshaller(xStreamMarshaller);
        xmlConverter.setUnmarshaller(xStreamMarshaller);

        return xmlConverter;
    }
}
