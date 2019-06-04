package edu.angelo.finalprojectdennis;

import com.badlogic.androidgames.framework.FileIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Settings {

    public static boolean soundEnabled = true;
    public static int[] highscores = new int[] {10, 8, 5, 3, 1};

    public static void load(FileIO files) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(files.readFile(".rpggame"))); //"Change both occurrences of ".mrnom" to ".shootem""
            soundEnabled = Boolean.parseBoolean(in.readLine());
            for (int i = 0; i < 5; i += 1) {
                highscores[i] = Integer.parseInt(in.readLine());
            }
        } catch (IOException e) {
            // :( It's ok we have defaults
        } catch (NumberFormatException e) {
            // :/ It's ok, defaults save our day
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
            }
        }
    }

    public static void save(FileIO files) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(files.writeFile(".rpggame"))); //"Change both occurrences of ".mrnom" to ".shootem""

            //"look in the save method for the two lines that call the write method on the out object, and change them so that each String being written ends with a newline character;"
            //Each line will end with a new line character as we call out.newLine();
            out.write(Boolean.toString(soundEnabled));
            out.newLine();
            for (int i = 0; i < 5; i += 1) {
                out.write(Integer.toString(highscores[i]));
                out.newLine();
            }
        } catch (IOException e) {
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
            }
        }
    }

    public static void addScore(int score) {
        for (int i = 0; i < 5; i += 1) {
            if (highscores[i] < score) {
                for (int j = 4; j > i; j -= 1) {
                    highscores[j] = highscores[j - 1];
                }
                highscores[i] = score;
                return;
            }
        }
    }
}
