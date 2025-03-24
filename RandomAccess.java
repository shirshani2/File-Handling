package files;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccess {
    /**
     * Treat the file as an array of (unsigned) 8-bit values and sort them
     * in-place using a bubble-sort algorithm.
     * You may not read the whole file into memory!
     *
     * @param file
     */
    public static void sortBytes(RandomAccessFile file) throws IOException {

        boolean swap; //כדי לדעת האם להחליף או לא את המספרים
        long n = file.length();
         for (long i = 0; i < n - 1; i++) {
             swap = false;
             for (long j = 0; j < n - i - 1; j++) {
                 file.seek(j);
                 int first = file.read(); //שמירת המספר במיקום j

                 file.seek(j+1);
                 int second = file.read(); //שמירת המספר במיקום j+1

                 if (first > second) { //אם j יותר גדול מj+1 להחליף בינהם
                     file.seek(j);
                     file.write(second);

                     file.seek(j+1);
                     file.write(first);

                     swap = true;
                 }
             }
             if(!swap) {
                 break;
             }
         }
    }

    /**
     * Treat the file as an array of unsigned 24-bit values (stored MSB first) and sort
     * them in-place using a bubble-sort algorithm.
     * You may not read the whole file into memory!
     *
     * @param file
     * @throws IOException
     */
    public static void sortTriBytes(RandomAccessFile file) throws IOException {
        boolean swap;
        long n = file.length()/3; //כל 3 ביטים מייצגים מספר ולכן נתייחס כחלקי 3
        for (long i = 0; i < n - 1; i++) {
            swap = false;
            for (long j = 0; j < n - i - 1; j++) {

                file.seek(j*3); // נכפיל ב3 כי כל 3 ביטים מייצגים מספר
                int first = file.read() << 16 | file.read() << 8 | file.read(); // קליטת המספר בהתאם לmsb וlsb

                file.seek((j+1)*3);
                int second = file.read() << 16 | file.read() << 8 | file.read();

                if (first > second) { //אם המספר הראששון קטן מהשני נחליף בהתאם לmsb וlsb
                    file.seek(j*3);
                    file.write(second >> 16);
                    file.write(second >> 8);
                    file.write(second);

                    file.seek((j+1)*3);
                    file.write(first >> 16);
                    file.write(first >> 8);
                    file.write(first);

                    swap = true;
                }
            }
            if(!swap) {
                break;
            }
        }
    }
}
