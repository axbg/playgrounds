import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, TemplateException {
        System.out.println("Injecting the data in the templates");
        String finalHtml = injectValuesInFtlTemplates();

        System.out.println("Parsing the injected templates");
        Document document = Jsoup.parse(finalHtml, "UTF-8");
        document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);

        System.out.println("Storing the resulting PDF");
        try (OutputStream outputStream = new FileOutputStream("./src/main/resources/result.pdf")) {
            ITextRenderer renderer = new ITextRenderer();
            SharedContext sharedContext = renderer.getSharedContext();
            sharedContext.setPrint(true);
            sharedContext.setInteractive(false);
            renderer.setDocumentFromString(document.html());
            renderer.layout();
            renderer.createPDF(outputStream);
        }
    }

    private static String injectValuesInFtlTemplates() throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File("./src/main/resources/tmpl"));

        // Inject values in a smaller template
        Template template = cfg.getTemplate("fillable2.ftl");
        Map<String, Object> data = new HashMap<>();
        data.put("worked", "combined!");
        StringWriter writer = new StringWriter();
        template.process(data, writer);
        writer.flush();
        String generatedHtml = writer.toString();

        // Add the first injected template in the main one
        template = cfg.getTemplate("fillable.ftl");
        data = new HashMap<>();
        data.put("name", "John");
        data.put("date", new Date().toString());
        data.put("selected", true);
        data.put("additionalHtml", generatedHtml);

        writer = new StringWriter();
        template.process(data, writer);
        writer.flush();

        return writer.toString();
    }
}
