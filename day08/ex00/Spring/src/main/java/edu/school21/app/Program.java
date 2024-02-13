package edu.school21.app;

import edu.school21.preprocessor.PreProcessor;
import edu.school21.preprocessor.PreProcessorToUpperImpl;
import edu.school21.printer.Printer;
import edu.school21.printer.PrinterWithPrefixImpl;
import edu.school21.renderer.Renderer;
import edu.school21.renderer.RendererErrImpl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {
    public static void main(String[] args) {
        PreProcessor preProcessor = new PreProcessorToUpperImpl();
        Renderer renderer = new RendererErrImpl(preProcessor);
        PrinterWithPrefixImpl printer = new PrinterWithPrefixImpl(renderer);
        printer.setPrefix("Prefix ");
        printer.print("Hello!");

        System.out.println("\n-------SPRING PRINTER-----------\n");

        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Printer printer2 = context.getBean("printerWithPrefix", Printer.class);
        printer2.print ("Hello!") ;

        Printer datePrinter = context.getBean("printerLowerWithDateTime", Printer.class);
        datePrinter.print("Hello!");
        datePrinter = context.getBean("printerErrWithDateTime", Printer.class);
        datePrinter.print("Hello!");
        ((AbstractApplicationContext) context).close();
    }
}