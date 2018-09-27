package com.globant.bootcamp;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

public class DesktopTest {

	private Desktop desktop;

	private Desktop messyDesktop;

	private File recentlyOpenedFile;

	private File unopenedFile;

	@Before
	public void setUp() {
		createDesktops();
		openAFile();
		openLotsOfFilesIn(messyDesktop);
		unopenedFile = File.builder("~/todo").build();
	}

	private void createDesktops() {
		desktop = new Desktop();
		messyDesktop = new Desktop();
	}

	private void openAFile() {
		recentlyOpenedFile = File.builder("~/secrets.txt").build();
		desktop.openFile(recentlyOpenedFile);
		messyDesktop.openFile(recentlyOpenedFile);
	}

	private static void openLotsOfFilesIn(Desktop desktop) {
		for (int i = 0; i < 25; i++) {
			File logFile = File.builder("~/logs/file" + i).build();
			desktop.openFile(logFile);
		}
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