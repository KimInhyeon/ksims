package com.ksinfo.common.util;

public class PagingModel {
	public static final int PAGE_PER_RECORD = 10;	
	public static final int BLOCK_PER_PAGE = 5; 	
	private int curPage;	
	private int totPage;
	private int prevPage;
	private int nextPage;	
	private int curBlock;	
	private int totBlock;	
	private int pageBegin;	
	private int pageEnd;	
	private int blockBegin;
	private int blockEnd;	
	
	public PagingModel(int count, int curPage) {
		curBlock = 1;			
		this.curPage = curPage;	
		setTotPage(count);		
		setPageRange();
		setTotBlock();
		setBlockRange();
	}
	
	public void setBlockRange() {
		curBlock = (int)Math.ceil((curPage-1) / BLOCK_PER_PAGE)+1;	
		blockBegin = (curBlock-1) * BLOCK_PER_PAGE + 1;			 
		blockEnd = blockBegin + BLOCK_PER_PAGE - 1;					
		if(blockEnd > totPage) {						
			blockEnd = totPage;							
		}
		prevPage = curBlock == 1 ? 1 : (curBlock-1) * BLOCK_PER_PAGE;	
		nextPage = curBlock > totBlock ? (curBlock * BLOCK_PER_PAGE) : (curBlock * BLOCK_PER_PAGE) + 1;
		if(nextPage >= totPage) {
			nextPage = totPage;
		}
	}
	public void setTotBlock() {
		totBlock = (int)Math.ceil(totPage * 1.0 / BLOCK_PER_PAGE);
	}
	public void setPageRange(){
		pageBegin = (curPage - 1) * PAGE_PER_RECORD+1;
		pageEnd = pageBegin+PAGE_PER_RECORD-1;
	}

	//Getter & Setter
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getTotPage() {
		return totPage;
	}
	public void setTotPage(int count) {
		totPage = (int)Math.ceil(count * 1.0 / PAGE_PER_RECORD);
	}
	public int getPrevPage() {
		return prevPage;
	}
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getCurBlock() {
		return curBlock;
	}
	public void setCurBlock(int curBlock) {
		this.curBlock = curBlock;
	}
	public int getTotBlock() {
		return totBlock;
	}
	public void setTotBlock(int totBlock) {
		this.totBlock = totBlock;
	}
	public int getPageBegin() {
		return pageBegin;
	}
	public void setPageBegin(int pageBegin) {
		this.pageBegin = pageBegin;
	}
	public int getPageEnd() {
		return pageEnd;
	}
	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}
	public int getBlockBegin() {
		return blockBegin;
	}
	public void setBlockBegin(int blockBegin) {
		this.blockBegin = blockBegin;
	}
	public int getBlockEnd() {
		return blockEnd;
	}
	public void setBlockEnd(int blockEnd) {
		this.blockEnd = blockEnd;
	}
}
