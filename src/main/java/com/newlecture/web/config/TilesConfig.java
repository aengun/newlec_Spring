package com.newlecture.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.tiles3.SimpleSpringPreparerFactory;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration // 설정 자동 인식
public class TilesConfig {
   
   @Bean // spring이 관리하는 커다란 방(객체 관리) : ioc 컨테이너
   public TilesConfigurer tilesConfigurer() {
      TilesConfigurer tilesConfigurer = new TilesConfigurer();
      tilesConfigurer.setDefinitions(new String[] {"/WEB-INF/tiles.xml"});
      tilesConfigurer.setCheckRefresh(true);
      //ViewPreparer에서 Autowired가 가능하게 하는 설정
      tilesConfigurer.setPreparerFactoryClass(SimpleSpringPreparerFactory.class);
      return tilesConfigurer;
   }
   
   @Bean
   public TilesViewResolver tilesViewResolver() {
      TilesViewResolver viewResolver = new TilesViewResolver(); 
      // 컨트롤러가 반환하는 값을 찾음 >> 패턴 맞는애를 찾아옴 >> view를 만듦 >> 스프링에게 던져줌
      viewResolver.setViewClass(TilesView.class);
      viewResolver.setOrder(1);
      
      return viewResolver;
   }
   
}