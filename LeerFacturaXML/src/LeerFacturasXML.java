import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import java.nio.charset.StandardCharsets;


public class LeerFacturasXML {

    public static void main(String[] args) {
       Document document=null;
       
       /*
        * La clase SAXbuider crea un documento JDOM utilizando un analizador SAX.
       SAXbuilder utiliza un analizador SAX de terceros (elegido por JAXP de forma
       predeterminada, o puede configurarlo manualmente) para manejar las tareas de
       análisis y utiliza una instancia de un SAXHandler para escuchar los eventos SAX a fin
       de construir un documento con contenido JDOM utilizando una fábrica JDOM. Se
       puede encontrar información sobre SAX en http://www.saxproject.org . El método
       build() convierte el flujo de caracteres con formato UTF-8 en un documento JDOM
       que podemos manejar. 
        */

       
       try {
        FileInputStream fis = new FileInputStream("C:/ArchivosXML/factura.xml");
        //InputStreamReader entrada = new InputStreamReader(fis, StandardCharsets.UTF_8);
        InputStreamReader entrada = new InputStreamReader(fis, StandardCharsets.UTF_8);
        document = new SAXBuilder().build(entrada);
        
        Element factura = document.detachRootElement();
        
        System.out.println("-------------------------------------");
        System.out.println("******* DATOS FACTURA XML ***********");
        System.out.println("-------------------------------------");
        
        Element cabecera = factura.getChild("CABECERA");
        String sNumFactura = cabecera.getAttributeValue("numFactura");
        System.out.println("Número Factura: " + sNumFactura);
        
        Element fecha = cabecera.getChild("FECHA");
        String sFecha = fecha.getText();
        System.out.println("Fecha: " + sFecha);
        
        Element cliente = cabecera.getChild("CLIENTE");
        
        Element nif = cliente.getChild("NIF");
        String sNif = nif.getText();
        System.out.println("NIF: " + sNif);
        
        Element nombre = cliente.getChild("NOMBRE");
        String sNombre = nombre.getText();
        System.out.println("NOMBRE: " + sNombre);
        
        Element lineas = factura.getChild("LINEAS");
        List listaLineas = lineas.getChildren("LINEA");
        Iterator iteratorLineas = listaLineas.iterator();
        while (iteratorLineas.hasNext())
        {
        	System.out.println("                  *");
            System.out.println("-------------------------------------");
            System.out.println("                  *");
            
            Element linea = (Element)iteratorLineas.next();
            
            Element descripcion = linea.getChild("DESCRIPCION");
            String textoDescripcion = descripcion.getText();
            System.out.println("Descripcion --->"+textoDescripcion);
            
            Element precio = linea.getChild("PRECIO");
            String textoPrecio = precio.getText();
            double doublePrecio = Double.parseDouble(textoPrecio);
            System.out.printf("Precio --->"+"%18.2f%n",doublePrecio);
            
            Element unidades = linea.getChild("UNIDADES");
            String textoUnidades = unidades.getText();
            int intUnidades=Integer.parseInt(textoUnidades);
            System.out.printf("Unidades --->"+"%18d%n",intUnidades);
        }
        
       }
       catch (IOException | JDOMException e) {
           System.out.println(e.getMessage());
       }
    }
    
}
