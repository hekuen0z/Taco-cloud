package edu.taco_cloud.controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Класс простого контроллера, который не обрабатывает запросы,
 * а просто показывает главный экран (домашнюю страницу сайта).
 * <p>
 * Заметки:
 * Также этот контроллер можно перенести в любой другой класс
 * с аннотацией @Configuration (например, в TacoCloudApplication)
 * Может быть полезно для уменьшения количества артефактов в проекте.
 */
@Configuration
public class WebConfigHomePage implements WebMvcConfigurer {

    /**
     * Метод для регистрации контроллеров представления
     *
     * @param registry - позволяет зарегистрировать один или несколько контроллеров представления
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/")
                .setViewName("homePage");
    }
}
