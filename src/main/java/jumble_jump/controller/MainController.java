package jumble_jump.controller;

import jumble_jump.domain.AllFontPrinter;
import jumble_jump.domain.CalligrapherFinder;
import jumble_jump.domain.Fonts;
import jumble_jump.util.reader.FontCsvReader;
import jumble_jump.util.reader.RawCsvReader;
import jumble_jump.util.validator.FontIdValidator;
import jumble_jump.util.validator.PrintoutValidator;
import jumble_jump.view.Input;
import jumble_jump.view.Output;

import java.awt.*;
import java.io.IOException;
import java.util.Map;

public class MainController {

    public void main() throws IOException {
        Fonts fonts = createFonts();
        CalligrapherFinder calligrapherFinder = new CalligrapherFinder(fonts);

        AllFontPrinter allFontPrinter = new AllFontPrinter();
        String print = allFontPrinter.print(calligrapherFinder, fonts);
        System.out.println(print);

        getPrintout(calligrapherFinder,fonts);
    }

    private void getPrintout(CalligrapherFinder calligrapherFinder,Fonts fonts){
        while(true){
            try{
                String printout = Input.inputPrintout();
                if(calligrapherFinder.isEnd(printout)){
                    break;
                }
                PrintoutValidator.validate(printout);
                int fontId = inputFontId(fonts);

                String result =  calligrapherFinder.getCalligrapy(fontId,printout);
                printPrintout(result);
            }catch (IllegalArgumentException e){
                Output.printError(e.getMessage());
            }
        }
    }

    private void printPrintout(String printout){
        Output.printMessage(printout);
    }


    private int inputFontId(Fonts fonts){
        while(true){
            try{
                int result = Input.inputFontId();
                FontIdValidator.validateExistId(result,fonts);
                return result;
            }catch (IllegalArgumentException e){
                Output.printError(e.getMessage());
            }

        }
    }


    private Fonts createFonts() throws IOException {
        return new Fonts(registerFonts());
    }

    private Map<Integer,String> registerFonts() throws IOException {
        FontCsvReader csvReader = new FontCsvReader(new RawCsvReader());
        String itemCsvPath = "/data/fonts.csv";

        Map<Integer, String> stringStringMap = csvReader.readAsMap(itemCsvPath);
        return stringStringMap;
    }
}
