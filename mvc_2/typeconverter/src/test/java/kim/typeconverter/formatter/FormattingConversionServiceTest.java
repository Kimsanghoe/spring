package kim.typeconverter.formatter;

import kim.typeconverter.converter.IpPortToStringConverter;
import kim.typeconverter.converter.StringToIntegerConverter;
import kim.typeconverter.converter.StringToIpPortConverter;
import kim.typeconverter.type.IpPort;
import org.junit.jupiter.api.Test;
import org.springframework.format.support.DefaultFormattingConversionService;

import static org.assertj.core.api.Assertions.assertThat;

class FormattingConversionServiceTest {

    @Test
    void formattingConversionService(){
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();


        //컨버터 등록
        conversionService.addConverter(new StringToIntegerConverter());
        conversionService.addConverter(new IpPortToStringConverter());
        conversionService.addConverter(new StringToIpPortConverter());

        //포메터 등록
        conversionService.addFormatter(new MyNumberFormatter());

        IpPort ip = conversionService.convert("127.0.0.1:8080", IpPort.class);
        assertThat(ip).isEqualTo(new IpPort("127.0.0.1", 8080));

        assertThat(conversionService.convert(1000, String.class)).isEqualTo("1,000");
        assertThat(conversionService.convert("1,000", Long.class)).isEqualTo(1000L);

    }

}
