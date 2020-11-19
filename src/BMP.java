import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class BMP {
    String signature;
    int fileSize, dataOffset, width, height;
    final ArrayList<Short> data;

    public BMP(String fileName) throws IOException {
        data = new ArrayList<>();
        read(fileName);
    }

    public void read(String filename) throws IOException {
        FileInputStream inputStream = new FileInputStream(filename);
        int symbol;
        int n = 0;
        while ((symbol = inputStream.read()) != -1) {
            if (n++ < 54)
                System.out.print(symbol + " ");
            data.add((short) symbol);
        }
        inputStream.close();

        this.signature = "" + (char) (int) (data.get(0)) + (char) (int) (data.get(1));
        this.fileSize = data.get(5) * 256 * 256 * 256 + data.get(4) * 256 * 256 + data.get(3) * 256 + data.get(2);
        this.dataOffset = data.get(13) * 256 * 256 * 256 + data.get(12) * 256 * 256 + data.get(11) * 256 + data.get(10);
        this.width = data.get(21) * 256 * 256 * 256 + data.get(20) * 256 * 256 + data.get(19) * 256 + data.get(18);
        this.height = data.get(25) * 256 * 256 * 256 + data.get(24) * 256 * 256 + data.get(23) * 256 + data.get(22);
    }

    public void showInfo() {
        System.out.println(String.format("%13s", "Signature: ") + signature);
        System.out.println(String.format("%13s", "file size: ") + fileSize);
        System.out.println(String.format("%13s", "data offset: ") + dataOffset);
        System.out.println(String.format("%13s", "width: ") + width);
        System.out.println(String.format("%13s", "height: ") + height);
    }

    public void changeBrightness(int change) {
        short color;
        for (int i = this.dataOffset; i < this.fileSize; i++) {
            color = data.get(i);
            if (change > 0)
                color = color + change > 255 ? 255 : (short) (color + change);
            else if (change < 0)
                color = color + change < 0 ? 0 : (short) (color + change);

            data.set(i, color);
        }
    }

    public void negative() {
        short color;
        for (int i = this.dataOffset; i < this.fileSize; i++) {
            color = (short) (255 - data.get(i));
            data.set(i, color);
        }
    }

    public void crypt() {
        String[] alphabet = {"", " ", "E", "T", "I", "A", "O", "S", "N", "R", "H", "L", "D", "C", "F", "U", "M", "G", "Y", "B", "P", ".", "W", "V", ",", "K", "-", "’", "0", "X", "Q", "1", "4", "5", "6", "7", "8", "9", "J", "Z"};
        String text = "THE BIGGEST CITIES OF THE UNITED KINGDOM ARE ALSO GREAT INDUSTRIAL AND CULTURAL CENTRES. THE BIGGEST CITY OF THE COUNTRY IS LONDON. THE POPULATION OF GREATER LONDON IS NOW OVER 8 MILLION PEOPLE. LOTS OF MEN AND WOMEN CROWD THE CITY AT DAY-TIME. THEY ARE ENGAGED IN THE VAST INTERNATIONAL BUSINESS OF LONDON WHICH HAS MADE IT LIKE NO OTHER PLACE IN THE WORLD. MANY OF THE PRESENT-DAY COMMERCIAL, FINANCIAL, AND CIVIC INSTITUTIONS OF THE CITY HAVE THEIR ROOTS IN THE 16TH CENTURY, AND SOME GO EVEN DEEPER. LONDON IS ALSO A CULTURAL CENTRE OF THE ENGLAND. THE BRITISH MUSEUM, LOCATED HERE IS THE LARGEST IN THE WORLD. IN THE EVENING YOU MAY CHOOSE BETWEEN MORE THAN 50 THEATRES OF THE CITY. AMONG MOST FAMOUS CITY ATTRACTIONS ARE ALSO BUCKINGHAM PALACE, THE HOUSES OF PARLIAMENT, ST. PAUL’S CATHEDRAL, WESTMINSTER ABBEY, THE TOWER OF LONDON AND MANY OTHERS. OTHER BIG AND FAMOUS CITIES OF THE UNITED KINGDOM ARE BIRMINGHAM, GLASGOW, LIVERPOOL, MANCHESTER, EDINBURGH, BELFAST AND OTHERS. THEY ARE FAMOUS FOR ITS COMPANIES, UNIQUE CHARACTER AND HISTORY. BIRMINGHAM IS LONG FAMOUS AS AN INTERNATIONAL BUSINESS CENTRE. IT HAS DEVELOPED INTO A MODERN AND EXCITING CITY, WHICH BUILDINGS AND SHOPS ARE SECOND TO NONE. BIRMINGHAM IS AT HEART OF BRITAIN’S MOTORWAY SYSTEM. MASSIVE POST-WAR DEVELOPMENT BROUGHT EXCITING NEW BUILDINGS, BUT THE BEST OF THE OLD ONES HAVE BEEN PRESERVED. THE CITY’S MUSEUM AND ART GALLERY HAS SOME OF THE FINEST EXAMPLES OF EUROPEAN PAINTING. BIRMINGNAM’S ULTRA-MODERN LIBRARY IS ONE OF THE LARGEST AND BEST STOCKED IN EUROPE AND INCLUDES THE SHAKESPEARE MEMORIAL LIBRARY WITH 40,000 BOOKS IN 90 LANGUAGES. THE CITY POSSESSES SEVERAL INTERESTING CHURCHES AND TWO CATHEDRALS. EDINBURGH, THE CAPITAL OF SCOTLAND, IS ALSO A GREAT CITY OF MORE THAN HALF A MILLION INHABITANTS. THE CITY IS BUILT OF STONE, NOT BRICK. THE HOUSES LOOK HARD AND SOLID. SOME PEOPLE WOULD CALL THEM GRIM, ESPECIALLY ON A WET DAY, BUT WHEN THE SUN SHINES BEAUTIFULLY IN THE CITY THE CITY LOOKS FINE. ITS MANY BOOKSHOPS, TAVERNS, AND CLUBS SOME OF WORLD-FAMOUS PEOPLE VISITED AT DIFFERENT TIMES. AMONG THEM WERE DR. JOHNSON AND ROBERT BURNS. ONE OF THE FAMOUS AVENUES IN THE WORLD IS THE PRINCE’S STREET IN EDINBURGH. IT IS THE FINEST STREET AND A SHOPPING AREA OF THE CITY. GLASGOW IS THE THIRD LARGEST CITY OF GREAT BRITAIN. YOU MAY FEEL ITS INDUSTRIAL ENERGY EVERYWHERE IN THE CITY. THE CITY EXTENDS ALONG BOTH BANKS OF THE RIVER CLYDE. WITH EACH PHASE OF ITS DEVELOPMENT IT HAS STRETCHED, UNTIL ITS OUTSKIRTS NOW LIE SEVERAL MILES FROM THE CITY CENTRE. IT IS, BY FAR, THE LARGEST AND MOST POPULOUS CITY IN THE WHOLE OF SCOTLAND. GLASGOW IS KNOWN THE WORLD OVER FOR ITS SHIP-BUILDING. GLASGOW-BUILT LOCOMOTIVES RUN IN EVERY PART OF THE WORLD. TODAY GLASGOW IS OF SUCH A SIZE THAT IT EXTENDS FAR OVER BOTH BANKS OF THE RIVER CLYDE AND BRIDGES ARE AS ESSENTIAL FOR THE CONDUCT OF ACTIVITIES AS ARE THE PEOPLE THEMSELVES. WITHIN A DISTANCE OF A MILE THERE ARE 7 BRIDGES. THEY CARRY ROAD AND RAIL TRAFFIC IN AND OUT OF THE CITY. NO OTHER CITY OF SCOTLAND HAS OR NEEDS AS MANY RIVER CROSSINGS AS GLASGOW. CARDIFF, THE CAPITAL OF WALES, LIES NEAR THE MOUTH OF THE RIVER TAFF. IN THE DAYS OF OUR GREAT-GRANDPARENTS CARDIFF WAS A TINY VILLAGE. TODAY THERE ARE ABOUT A QUARTER OF A MILLION PEOPLE LIVING THERE. CARDIFF IS NOW THE LARGEST TOWN IN WALES AND IS NOTED FOR ITS COAL. THERE IS ALSO A DELIGHTFUL PARK IN THE CITY WHICH EVERYONE TRIES TO SEE. THIS IS CATHAY’S PARK. FEW TOWNS IN THE WORLD HAVE SUCH FINE PUBLIC BUILDINGS AS CARDIFF. THE LAW COURTS, CITY HALL AND UNIVERSITY BUILDINGS IN CATHAY’S PARK ARE WORTHY OF ANY CITY IN THE WORLD. THE UNITED KINGDOM OF GREAT BRITAIN AND NORTHERN IRELAND HAVE MANY OTHER CITIES AND TOWNS, THAT ATTRACT THOUSANDS OF PEOPLE FROM ALL OVER THE WORLD EITHER ON BUSINESS OR PRIVATE VISIT.";
// TODO: чтение данных из файла
// TODO: сохранение зашифрованного BMP-файла

        short color;
        int n = 0, change, code = 0;
        for (int i = this.dataOffset; i < this.fileSize; i++) {
            change = text.charAt(n);

            for (int j = 1; j < alphabet.length; j++) {
                if ((int) alphabet[j].charAt(0) == change) {
                    code = j;
                    break;
                }
            }

            color = data.get(i);
            if (change > 0)
                color = color + code > 255 ? 255 : (short) (color + code);


            data.set(i, color);
            n++;
            if (n >= text.length())
                break;
        }
    }
}