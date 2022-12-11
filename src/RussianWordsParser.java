import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

//���� ����� ����� ������������ ������� ����� � ������
public class RussianWordsParser implements Parser{
    private final String fileName;
    private final Map<String, Integer> wordsFrequency;




    public RussianWordsParser(String fileName) {
        //����� �������� �������� ������������� ����� fileName
        this.fileName = fileName;
        this.wordsFrequency = new TreeMap<>();

    }

    @Override
    public void parse() {
        if(!wordsFrequency.isEmpty()) {
            //���� �� �� ����� ��� ������� ������� ����, �� ������ �� ������
            return;
        }
        try(FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String s = null;
            while((s = bufferedReader.readLine()) != null) {
                String[] russianWords = s.split("[^�-��-�]+");
                for(String word : russianWords) {
                    if(!word.equals("")) {   //���� ����� �� �������� ������ �������
                        //����� ����� ���� ���� � ����� wordsFrequency, ���� �� ����.
                        //toLowerCase ����� ��� �������������� � ������� ��������
                        //���������� ��� ��� ����, ����� �������� ����� "������" � "������" ��������� �����������
                        String lowerCaseWord = word.toLowerCase();

                        if(wordsFrequency.get(lowerCaseWord) == null) {
                            //����� ����������� � ������ ���. �� ������ ������ ��� ������� ����� �������
                            wordsFrequency.put(lowerCaseWord, 1);
                        }
                        else {
                            //������ ������ ������� �����. ����������� �� �� �������.
                            int oldFrequency = wordsFrequency.get(lowerCaseWord);
                            wordsFrequency.replace(lowerCaseWord, oldFrequency + 1);
                        }
                    }
                }
            }
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Map<String, Integer> frequency() {
        return wordsFrequency;
    }

    public String mostFrequentWord() {
        parse();
        int num = 0;
        String answerMFW = null;
        for (String word : wordsFrequency.keySet()) {
            if (num < wordsFrequency.get(word)) {
                num = wordsFrequency.get(word);
                answerMFW = word;
            }
        }
        String mostFrequentWord = "\n���� ����� ����������� ����� \""+answerMFW+"\" - "+num+" ���";
        return mostFrequentWord;
    }

    public double averageFrequency() {
        int sum = 0;
        int num = 0;
        int k = 0;
        double aveFre = 0;
        parse();
        for (String word : wordsFrequency.keySet()) {
            num = wordsFrequency.get(word);
            sum = sum + num;
            k++;
        }
        aveFre = sum/k;
        return aveFre;
    }
}
