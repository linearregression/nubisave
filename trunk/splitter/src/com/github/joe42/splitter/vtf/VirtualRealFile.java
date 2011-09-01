package com.github.joe42.splitter.vtf;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import com.github.joe42.splitter.util.StringUtil;
import com.github.joe42.splitter.util.file.FileUtil;

import fuse.compat.FuseStat;

public class VirtualRealFile extends VirtualFile {
	File realFile = null;
	public VirtualRealFile(String vtPath, String realPath) {
		super(vtPath);
		realFile = new File(realPath);
	}
	public FuseStat getAttr(){
		FileEntry entry = getFileEntry();
		entry.size = (int) realFile.length();
		return entry.getFuseStat(); 
	}
	public void truncate(){
		FileUtil.writeFile(realFile, "");
	}
	public String getText(){
		return FileUtil.readFile(realFile);
	}

	public String toString() {
		return FileUtil.readFile(realFile);
	}

	public void read(ByteBuffer buf, long offset) {
		String text = FileUtil.readFile(realFile);
		CharBuffer cbuf = buf.asCharBuffer();
		cbuf.put(text.substring((int) offset, buf.limit()));
	}

	public void write(ByteBuffer buf, long offset) {
		//FileUtil.writeFile(realFile, StringUtil.getUTF8FromByteBuffer(buf));
		FileUtil.writeFile(realFile, buf, offset); 
		// FIXME: file cannot be
														// larger than 4096
														// bytes
	}
	public void setText(String text){
		FileUtil.writeFile(realFile, text);
	}

}
