package io.github.si1kn;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class APIDListener {


    public static void main(String[] args) {
        String command = "acpi_listen";

        // Use ProcessBuilder to execute the command
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", "-c", command);

        try {
            // Start the process
            Process process = processBuilder.start();
            // Capture and process the command output
            captureOutput(process.getInputStream());

            // Wait for the process to complete
            int exitCode = process.waitFor();
            System.out.println("Exited with code: " + exitCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void captureOutput(InputStream inputStream) {
        // Create a BufferedReader to read the output
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Process each line of the output
                determin(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void determin(String s) {
        if(s.equals("button/lid LID close")) {
            runScript("sh /scripts/lidClose.sh");
        }

        if(s.equals("button/lid LID open")) {
            runScript("sh /scripts/lidOpen.sh");
        }
    }

    static void runScript(String s) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", "-c", s);
    }
}
