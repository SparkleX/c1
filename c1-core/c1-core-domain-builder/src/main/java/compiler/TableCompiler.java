package compiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.stream.Stream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.next.c1.schema.table.Table;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

@SpringBootApplication
@Component
public class TableCompiler implements InitializingBean {
	
	
	@Value("${output:../../c1-domain/target/generated-sources/java/}")
	File outputPath;
	@Value("${input:../../c1-resources/src/main/resources/domain}")
	File xmlInputPath;
	
	public static void main(String[] args) throws Exception {
		ApplicationContext appContext = SpringApplication.run(TableCompiler.class, args);
		TableCompiler instance = appContext.getBean(TableCompiler.class);
		instance.run();
	}

	JAXBContext jaxbContext;
	Unmarshaller jaxbUnmarshaller;

	
	TableCompiler() throws JAXBException {
		jaxbContext = JAXBContext.newInstance(Table.class);
		jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		jaxbUnmarshaller.setProperty("com.sun.xml.bind.ObjectFactory",new ObjectFactoryImpl());
		

	}

	void genarateCode(Path path) {
		try {
			
			File xmlFile = path.toFile();
			System.out.println(FilenameUtils.getName(xmlFile.getName()));
			Table oTable = (Table) jaxbUnmarshaller.unmarshal(xmlFile);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new DefaultNamespacePrefixMapper());
			//jaxbMarshaller.marshal(oTable, new File("C:/dev/1.xml"));
			
			
			//Table oTable = mapper.readValue(xmlFile, Table.class);
			StringWriter sw = new StringWriter();
			template.process(oTable, sw);
			String outputFileName = FilenameUtils.getBaseName(xmlFile.getName()) + ".java";
			outputFileName = outputPath.getAbsolutePath() + "/gen/table/Bmo" + outputFileName;
			FileUtils.writeStringToFile(new File(outputFileName), sw.toString(), "utf8");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	void run() throws Exception {
		try (Stream<Path> paths = Files.walk(Paths.get(xmlInputPath.getAbsolutePath()))) {
			paths.filter(Files::isRegularFile).forEach(this::genarateCode);
		}
	}

	Template template;
	String readResource(String url) throws FileNotFoundException, IOException
	{
		try (InputStream is = ResourceUtils.getURL(url).openStream()) 
		{
			String content = IOUtils.toString(is, StandardCharsets.UTF_8);
			return content;
		}
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		
		
		String content = readResource("classpath:template/table.ftl");
		Configuration cfg = new Configuration(new Version(2, 3, 20));
		StringTemplateLoader dummyLoader = new StringTemplateLoader();
		dummyLoader.putTemplate("table", content);
		cfg.setTemplateLoader(dummyLoader);
		cfg.setDefaultEncoding("UTF-8");
		cfg.setLocale(Locale.US);
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		template = cfg.getTemplate("table");

	}

}
