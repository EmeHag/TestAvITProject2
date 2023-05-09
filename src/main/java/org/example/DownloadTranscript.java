package org.example;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class DownloadTranscript {

    public static void downloadTranscript(){
        Main.startProgramLogIn();

        $(byXpath("//a[contains(text(),'Intyg »')]")).click();


    }
}
