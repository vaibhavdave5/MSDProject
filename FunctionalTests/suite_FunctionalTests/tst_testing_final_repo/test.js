function main() {
    startApplication("plagiarism-detector-0.0.1-SNAPSHOT-jfx.jar");
    mouseClick(waitForObject(":Integrity Plagiarism Checker by Team-107.Upload Excel Validator_button danger"), 80, 19, 0, Button.Button1);
    chooseFile(waitForObject(":SquishJavaFX"), "C:\\Study\\MSD\\TeamRepo\\team-107\\plagiarism-detector\\target\\test-classes\\studentData.xlsx");
    mouseClick(waitForObject(":Integrity Plagiarism Checker by Team-107.folder_image-view"), 29, 56, 0, Button.Button1);
    chooseDirectory(waitForObject(":SquishJavaFX"), "C:\\Study\\MSD\\TeamRepo\\team-107\\plagiarism-detector\\src\\test\\resources\\final-test-repos");
    mouseClick(waitForObject(":final-test-repos_check-box"), 10, 18, 0, Button.Button1);
    mouseClick(waitForObject(":Integrity Plagiarism Checker by Team-107.hw_text-input text-field"), 61, 28, 0, Button.Button1);
    type(waitForObject(":Integrity Plagiarism Checker by Team-107.hw_text-input text-field"), "HW3");
    mouseClick(waitForObject(":Integrity Plagiarism Checker by Team-107.Select a strategy..._menu-button primary"), 157, 10, 0, Button.Button1);
    activateItem(waitForObjectItem(":Integrity Plagiarism Checker by Team-107.Select a strategy..._ContextMenu", "Textual Similarity"));
    mouseClick(waitForObject(":Integrity Plagiarism Checker by Team-107.Run_button primary"), 82, 20, 0, Button.Button1);
    mouseClick(waitForObject(":OK_button"), 14, 19, 0, Button.Button1);
    mouseClick(waitForObject(":dirContent.final-test-repos_cell indexed-cell tree-cell check-box-tree-cell"), 9, 10, 0, Button.Button1);
    mouseClick(waitForObject(":Integrity Plagiarism Checker by Team-107.Run_button primary"), 35, 24, 0, Button.Button1);
    mouseClick(waitForObject(":OK_button_2"), 13, 13, 0, Button.Button1);
    mouseClick(waitForObject(":Integrity Plagiarism Checker by Team-107.Textual Similarity_menu-button primary"), 152, 17, 0, Button.Button1);
    activateItem(waitForObjectItem(":Integrity Plagiarism Checker by Team-107.Textual Similarity_ContextMenu", "Code Similarity"));
    mouseClick(waitForObject(":Integrity Plagiarism Checker by Team-107.Run_button primary"), 46, 2, 0, Button.Button1);
    mouseClick(waitForObject(":OK_button"), 19, 19, 0, Button.Button1);
    snooze(4.1);
    closeWindow(":Integrity Plagiarism Checker by Team-107_Stage");
}
