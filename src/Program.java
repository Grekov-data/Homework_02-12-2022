import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class Program {

    public static void main(String[] args) {
        //Получаем частоты слов
        RussianWordsParser fileParser = new RussianWordsParser("War and piece.txt");
        fileParser.parse();
        Map<String, Integer> frequency = fileParser.frequency();

        //Записываем их в файл
        try(FileWriter fileWriter = new FileWriter("statistics.txt")) {
            for(Map.Entry<String, Integer> entry : frequency.entrySet()) {
                String s = String.format("Слово %s встречается %d раз\n", entry.getKey(), entry.getValue());
                fileWriter.write(s);
            }


            fileWriter.write(fileParser.mostFrequentWord());
            String aveFre = String.format("\nСредняя частота = " + fileParser.averageFrequency());
            fileWriter.write(aveFre);




        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}