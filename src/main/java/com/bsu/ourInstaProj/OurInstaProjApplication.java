package com.bsu.ourInstaProj;

import com.bsu.ourInstaProj.entity.request.PhotoRequest;
import com.bsu.ourInstaProj.entity.response.PhotoVO;
import com.bsu.ourInstaProj.service.PhotoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
public class OurInstaProjApplication {


	public static void main(String[] args) {
		SpringApplication.run(OurInstaProjApplication.class, args);
	}

	@Bean
	public Function<PhotoRequest, PhotoVO> photoCut() {
		return photoRequest -> new PhotoVO(photoRequest);
	}

}
