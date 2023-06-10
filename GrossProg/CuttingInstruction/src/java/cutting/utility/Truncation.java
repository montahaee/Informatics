package cutting.utility;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class Truncation {

    private Truncation() {

    }
    
    @Contract(pure = true)
    public static @NotNull String form(String comment, boolean nextLine, double number) {
        return String.format(Locale.FRENCH, comment +"%1.2f" +(nextLine? System.lineSeparator() : ""), number);
    }
    @Contract(pure = true)
    public static @NotNull String form(String comment, String format, boolean nextLine, String info) {
        return String.format(Locale.FRENCH, comment +"%" + format +(nextLine? System.lineSeparator() : ""), info);
    }

}
