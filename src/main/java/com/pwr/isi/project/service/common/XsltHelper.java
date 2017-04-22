package com.pwr.isi.project.service.common;

import lombok.extern.slf4j.Slf4j;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;

@Slf4j
public class XsltHelper {

    private static final String PathToFolderWithXslt = "xslt/";

    public static String ApplyXsltTransformToXml(String inPutXml, String xsltFilename) {
        try {
            URL resource = XsltHelper.class.getClassLoader().getResource(PathToFolderWithXslt + xsltFilename);
            if (resource == null) {
                log.error("Xslt file not found");
                return null;
            }
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer(
                    new javax.xml.transform.stream.StreamSource(resource.getFile()));
            StringReader reader = new StringReader(inPutXml);
            StringWriter writer = new StringWriter();
            transformer.transform(
                    new StreamSource(reader),
                    new StreamResult(writer));
            return writer.toString();
        } catch (TransformerConfigurationException e) {
            log.error("An error occurred in the XSL file");
        } catch (TransformerException e) {
            SourceLocator locator = e.getLocator();
            int col = locator.getColumnNumber();
            int line = locator.getLineNumber();
            String publicId = locator.getPublicId();
            String systemId = locator.getSystemId();
            log.error("An error occurred while applying the XSL file");
            log.error("Column" + col);
            log.error("Line" + line);
            log.error("Public id" + publicId);
            log.error("SystemId" + systemId);
        }
        return null;
    }
}
