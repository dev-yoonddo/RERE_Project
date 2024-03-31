package com.login.demo;

import com.login.demo.TestApplication;
import org.apache.jasper.compiler.JspConfig;
import org.apache.tomcat.util.descriptor.web.JspPropertyGroup;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		JspPropertyGroup jspPropertyGroup = new JspPropertyGroup();
//		jspPropertyGroup.addUrlPattern("*.jsp");
//		jspPropertyGroup.setPageEncoding("UTF-8");
//		jspPropertyGroup.setScriptingInvalid("true");
//		jspPropertyGroup.addIncludePrelude("/WEB-INF/jsp/");
//		jspPropertyGroup.addIncludeCoda("/WEB-INF/jsp/");
		return application.sources(TestApplication.class);
	}

}
