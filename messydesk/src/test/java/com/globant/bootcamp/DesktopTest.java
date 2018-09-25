package com.globant.bootcamp;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.equalTo;

public class DesktopTest {

    private Desktop desktop;
    private Desktop fullRecentFileListDesktop;
    private File    recentlyOpenedFile;
    private File    unopenedFile;

    @Before
    public void setUp() {
        desktop = new Desktop();
        fullRecentFileListDesktop = new Desktop();
        recentlyOpenedFile = new File();
        unopenedFile = new File();
    }

    @Test
    public void WhenDesktopInitializesThenRecentFilesIsEmpty() {
        assertTrue(!desktop.hasRecentFiles());
    }

    @Test
    public void WhenFileIsOpenedThenFileIsInTheRecentFileList() {
        desktop.openFile(unopenedFile);
        assertTrue(desktop.hasRecentlyOpen(unopenedFile));
    }

    @Test
    public void ifOpenedFileIsRecentThenItGetsOnTopOfRecentFileList() {
        desktop.openFile(recentlyOpenedFile);
        assertThat(desktop.getLastOpenedFile(), equalTo(recentlyOpenedFile));
    }

    @Test
    public void ifOpenedFileIsRecentThenItDoesNotDuplicateInList() {
        desktop.openFile(recentlyOpenedFile);
        List<File> fileHistory = desktop.getFileHistory();
        int fileCount = Collections.frequency(fileHistory, recentlyOpenedFile);
        assertThat(fileCount, equalTo(1));
    }

    @Test
    public void ifRecentFileListIsFullThenOldestOpenedFileIsRemovedFromList() {
        File oldestFile = fullRecentFileListDesktop.getOldestOpenedFile();
        fullRecentFileListDesktop.openFile(unopenedFile);
        assertTrue(!fullRecentFileListDesktop.hasRecentlyOpen(oldestFile));
    }

}
