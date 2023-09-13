package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ElectronConnector {
    private Process electronProcess;
    private BufferedReader electronOutput;
    private PrintWriter electronInput;

    public ElectronConnector() {
        this.createElectronProcess();
        this.createElectronOutputReader();
        this.createElectronInputWriter();
    }

    private void createElectronProcess() {
        try {
            ProcessBuilder electronProcessBuilder = new ProcessBuilder(
                    "electron",
                    "../electron/main.js"
            );

            this.electronProcess = electronProcessBuilder.start();
        } catch (IOException IOE) {
            IOE.printStackTrace();
        }
    }

    private void createElectronOutputReader() {
        this.electronOutput = new BufferedReader(
                new InputStreamReader(
                        this.electronProcess.getInputStream()
                )
        );
    }

    private void createElectronInputWriter() {
        this.electronInput = new PrintWriter(
                this.electronProcess.getOutputStream()
        );
    }

    public void sendData(String sendTargetData) {
        this.electronInput.println(sendTargetData);
        this.electronInput.flush();
    }

    public String readData() {
        String readData = null;
        try {
            readData = this.electronOutput.readLine();
        } catch (IOException IOE) {
            Printer.print(
                    "표준 출력 스트림을 참조하는 과정에서 알 수 없는 오류가 발생하였습니다.\n" +
                    "stack trace를 참조하십시오."
            );
            IOE.printStackTrace();
        }
        return readData;
    }

}
