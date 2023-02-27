package backend.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class InputClass {
    BufferedReader reader;
    public InputClass(BufferedReader reader)
    {
        this.reader = reader;
    }

    public ArrayList<String> read() throws IOException {
        String temp = reader.readLine();
        String[] templist = temp.split(" ");
        return new ArrayList<String>(Arrays.asList(templist));
    }
}
