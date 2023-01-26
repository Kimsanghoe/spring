package kim.typeconverter;

import kim.typeconverter.converter.IntegerToStringConverter;
import kim.typeconverter.converter.IpPortToStringConverter;
import kim.typeconverter.converter.StringToIntegerConverter;
import kim.typeconverter.converter.StringToIpPortConverter;
import kim.typeconverter.formatter.MyNumberFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToIpPortConverter());
//        registry.addConverter(new StringToIntegerConverter());
//        registry.addConverter(new IntegerToStringConverter());
        registry.addConverter(new IpPortToStringConverter());


        registry.addFormatter(new MyNumberFormatter());
    }
}
