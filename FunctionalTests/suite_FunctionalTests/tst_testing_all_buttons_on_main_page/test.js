function main() {
    startApplication("plagiarism-detector-0.0.1-SNAPSHOT-jfx.jar");
    snooze(4.0);
    closeWindow(":_Stage");
    activateItem(waitForObjectItem(":Integrity Plagiarism Checker by Team-107.menu_menu-bar menu-color", "Statistics"));
    activateItem(waitForObjectItem(":Integrity Plagiarism Checker by Team-107.Statistics_ContextMenu", "Global Statistics"));
    mouseClick(waitForObject(":OK_button_2"), 44, 9, 0, Button.Button1);
    activateItem(waitForObjectItem(":Integrity Plagiarism Checker by Team-107.menu_menu-bar menu-color", "Help"));
    activateItem(waitForObjectItem(":Integrity Plagiarism Checker by Team-107.Help_ContextMenu", "About"));
    mouseClick(waitForObject(":OK_button_2"), 28, 16, 0, Button.Button1);
    activateItem(waitForObjectItem(":Integrity Plagiarism Checker by Team-107.menu_menu-bar menu-color", "File"));
    activateItem(waitForObjectItem(":Integrity Plagiarism Checker by Team-107.File_ContextMenu", "Close"));
}
