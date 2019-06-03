package br.com.druid.sdp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableCaching
@EnableAspectJAutoProxy
@EnableSwagger2
public class SdpLiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(SdpLiveApplication.class, args);
	}

}
