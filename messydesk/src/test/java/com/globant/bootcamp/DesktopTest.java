package com.globant.bootcamp;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;
import static org.hamcrest.CoreMatchers.equalTo;

public class DesktopTest {

    private Desktop desktop;
    private Desktop messyDesktop;
    private File    recentlyOpenedFile;
    private File    unopenedFile;

    @Before
    public void setUp() {
        desktop = new Desktop();
        messyDesktop = new Desktop();
        
        recentlyOpenedFile = File.builder("~/secrets.txt").build();
        desktop.openFile(recentlyOpenedFile);
        messyDesktop.openFile(recentlyOpenedFile);
        
        for (int i = 0 ; i < 25 ; i++) {
            File logFile = File.builder("~/logs/file" + i).build();
            messyDesktop.openFile(logFile);
        }
        unopenedFile = File.builder("~/todo").build();
    }

    @Test
    public void WhenDesktopInitializesThenRecentFilesIsEmpty() {
        Desktop emptyDesk = new Desktop();
        assertTrue(!emptyDesk.hasRecentFiles());
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
        Collection<Object> fileHistory = desktop.getFileHistory();
        int fileCount = Collections.frequency(fileHistory, recentlyOpenedFile);
        assertThat(fileCount, equalTo(1));
    }

    @Test
    public void ifRecentFileListIsFullThenOldestOpenedFileIsRemovedFromList() {
        assumeTrue(messyDesktop.reachedCapacity());
        File oldestFile = messyDesktop.getOldestOpenedFile();
        messyDesktop.openFile(unopenedFile);
        assertTrue(!messyDesktop.hasRecentlyOpen(oldestFile));
    }

}
