import ru.Aidar.ParsingProffStandards.Parsing.IParseXML;
import ru.Aidar.ParsingProffStandards.Parsing.Impl.ParseXML;
import ru.Aidar.ParsingProffStandards.Utills.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestAppParse {
    public static void main(String[] args) throws IOException {
        String number1 = "06.003";
        String number2 = "06.040";
        String pathToSave1 = Paths.get("resources").toAbsolutePath()
                + File.separator + number1;
        String pathToSave2 = Paths.get("resources").toAbsolutePath()
                + File.separator + number2;
        Path path1 = FileUtils.getLastFilePath(pathToSave1).get();
        IParseXML iParseXML = new ParseXML();
        iParseXML.parse(path1.toString());
        Path path2 = FileUtils.getLastFilePath(pathToSave2).get();
        iParseXML.parse(path2.toString());

    }
}
