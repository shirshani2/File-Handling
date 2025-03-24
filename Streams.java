package files;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.util.List;
import java.util.ArrayList;

public class Streams {
    /**
     * Read from an InputStream until a quote character (") is found, then read
     * until another quote character is found and return the bytes in between the two quotes.
     * If no quote character was found return null, if only one, return the bytes from the quote to the end of the stream.
     *
     * @param in
     * @return A list containing the bytes between the first occurrence of a quote character and the second.
     */
    public static List<Byte> getQuoted(InputStream in) throws IOException {
        ArrayList<Byte> list = new ArrayList<>();
        int data;
        while ((data = in.read()) != -1) {
            if ((char)data == '"') { //רק כאשר מגיעים לציטוט מתחילים להוסיף לרשימה
                while ((data = in.read()) != -1 && (char)data != '"') { //מכניסים לרשימה עד לסיום הציטוט
                    list.add((byte)data);
                }
                return list;
            }
        }

        return null;
    }


    /**
     * Read from the input until a specific string is read, return the string read up to (not including) the endMark.
     *
     * @param in      the Reader to read from
     * @param endMark the string indicating to stop reading.
     * @return The string read up to (not including) the endMark (if the endMark is not found, return up to the end of the stream).
     */
    public static String readUntil(Reader in, String endMark) throws IOException {
        String answer = "";
        String check = "";
        int data;
        int endLength = endMark.length(); // בדיקת אורך הendmark
        while ((data = in.read()) != -1) {
            if (answer.length() >= endLength) { //לבדוק את סוף המילה רק שהיא כבר מספיק ארוכה
                check = answer.substring(answer.length() - endLength);
            }
            if (!check.equals(endMark)) { //בדיקה האם סוף המילה היא הendmark
                answer += (char)data;
            }
        }
        if (check.equals(endMark)) { //אם היה endmark להוריד אותה
            return answer.substring(0, answer.length() - endLength);
        }

        return answer;
    }

    /**
     * Copy bytes from input to output, ignoring all occurrences of badByte.
     *
     * @param in
     * @param out
     * @param badByte
     */
    public static void filterOut(InputStream in, OutputStream out, byte badByte) throws IOException {
        int data;
        while ((data = in.read()) != -1) {
            if ((byte)data != badByte) {
                out.write((byte)data);
                }
            }
    }

    /**
     * Read a 40-bit (unsigned) integer from the stream and return it. The number is represented as five bytes,
     * with the most-significant byte first.
     * If the stream ends before 5 bytes are read, return -1.
     *
     * @param in
     * @return the number read from the stream
     */
    public static long readNumber(InputStream in) throws IOException {
        ArrayList<Byte> lengthCheack = new ArrayList<>();
        long answer = 0;
        int data;
            for (int i = 0; i < 5; i++) {
                if ((data = in.read()) != -1) {
                    answer = (answer << 8) | (data);
                    lengthCheack.add((byte)data);
                }

            }

        if (lengthCheack.size() < 5) {
            return -1;
        }

        return answer;
    }
}
