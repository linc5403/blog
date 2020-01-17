package club.banyuan.conf;

import club.banyuan.interceptor.AdminInterceptor;
import club.banyuan.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/blogs/create", "/admin/*");
        registry.addInterceptor(new AdminInterceptor()).addPathPatterns("/admin/*");
    }
}
