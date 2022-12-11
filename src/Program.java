import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class Program {

    public static void main(String[] args) {
        //�������� ������� ����
        RussianWordsParser fileParser = new RussianWordsParser("War and piece.txt");
        fileParser.parse();
        Map<String, Integer> frequency = fileParser.frequency();

        //���������� �� � ����
        try(FileWriter fileWriter = new FileWriter("statistics.txt")) {
            for(Map.Entry<String, Integer> entry : frequency.entrySet()) {
                String s = String.format("����� %s ����������� %d ���\n", entry.getKey(), entry.getValue());
                fileWriter.write(s);
            }


            fileWriter.write(fileParser.mostFrequentWord());
            String aveFre = String.format("\n������� ������� = " + fileParser.averageFrequency());
            fileWriter.write(aveFre);




        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}