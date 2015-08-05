package com.git.iboke.util;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.git.iboke.util.SortCond.Order;

public class Page<T> {

	public static final int DEF_COUNT = 2;
	/**
	 * 排序参数List key
	 */
	private final static String SORTCOND_KEY = "__sortcond";

	// -- 分页参数 --//
	protected int pageNo = 1;
	protected int pageSize = 50; // 每页的记录个数
	protected boolean autoCount = true;
	
	protected Integer start=null;//设置起始条目.

	// -- 返回结果 --//
	protected List<T> result = Collections.emptyList();
	protected int totalCount = 0;

	protected String order;
	protected String orderBy;

	protected Map<String, Object> sortMap = new LinkedHashMap<String, Object>();

	// -- 构造函数 --//
	public Page() {
	}
	
	public Page(String col,String direction){
		if(!StringUtils.isBlank(col) && Util.fmtObj(col).length()>0){
			if(!StringUtils.isBlank(direction) && Util.fmtObj(direction).length()>0){
				this.addSortCond(new SortCond(col,Enum.valueOf(Order.class, direction.toUpperCase())));
			}else{
				this.addSortCond(new SortCond(col));
			}
		}else{
			this.addSortCond(new SortCond("updated"));
		}
		
	}
	public Page(int pageSize){
		this.totalCount = 0;
		if (pageSize <= 0) {
			this.pageSize = DEF_COUNT;
		} else {
			this.pageSize = pageSize;
		}
		this.pageNo=1;
	}

	public Page(int pageNo, int pageSize, int totalCount) {
		if (totalCount <= 0) {
			this.totalCount = 0;
		} else {
			this.totalCount = totalCount;
		}
		if (pageSize <= 0) {
			this.pageSize = DEF_COUNT;
		} else {
			this.pageSize = pageSize;
		}
		if (pageNo <= 0) {
			this.pageNo = 1;
		} else {
			this.pageNo = pageNo;
		}
		if (totalCount == 0) {
			this.pageNo = 1;
		} else if ((this.pageNo - 1) * this.pageSize >= totalCount) {
			this.pageNo = this.totalCount / this.pageSize;
		}
	}

	// -- 访问查询参数函数 --//
	/**
	 * 获得当前页的页号,序号从1开始,默认为1.
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * 设置当前页的页号,序号从1开始,低于1时自动调整为1.
	 */
	public void setPageNo(final int pageNo) {
		this.pageNo = pageNo;

		if (pageNo < 1) {
			this.pageNo = 1;
		}
	}

	public Page<T> pageNo(final int thePageNo) {
		setPageNo(thePageNo);
		return this;
	}

	/**
	 * 获得每页的记录数量,默认为1.
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置每页的记录数量,低于1时自动调整为1.
	 */
	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;

		if (pageSize < 1) {
			this.pageSize = 1;
		}
	}

	public Page<T> pageSize(final int thePageSize) {
		setPageSize(thePageSize);
		return this;
	}
	
	/**
	 * 设置起始条目值
	 * @param first
	 */
	public void setStart(int start){
		this.start = start;
	}

	/**
	 * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从1开始.
	 */
	public int getFirst() {
		if(start!=null){
			return start;
		}
		return ((pageNo - 1) * pageSize);
	}

	/**
	 * 查询对象时是否自动另外执行count查询获取总记录数, 默认为false.
	 */
	public boolean isAutoCount() {
		return autoCount;
	}

	/**
	 * 查询对象时是否自动另外执行count查询获取总记录数.
	 */
	public void setAutoCount(final boolean autoCount) {
		this.autoCount = autoCount;
	}

	public Page<T> autoCount(final boolean theAutoCount) {
		setAutoCount(theAutoCount);
		return this;
	}

	/**
	 * 取得页内的记录列表.
	 */
	public List<T> getResult() {
		return result;
	}

	/**
	 * 设置页内的记录列表.
	 */
	public void setResult(final List<T> result) {
		this.result = result;
	}

	/**
	 * 取得总记录数, 默认值为-1.
	 */
	public long getTotalCount() {
		return totalCount;
	}

	/**
	 * 设置总记录数.
	 */
	public void setTotalCount(final int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 根据pageSize与totalCount计算总页数, 默认值为-1.
	 */
	public long getTotalPages() {
		if (totalCount < 0)
			return -1;

		long count = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			count++;
		}
		return count;
	}

	/**
	 * 是否还有下一页.
	 */
	public boolean isHasNext() {
		return (pageNo + 1 <= getTotalPages());
	}

	/**
	 * 取得下页的页号, 序号从1开始. 当前页为尾页时仍返回尾页序号.
	 */
	public int getNextPage() {
		if (isHasNext())
			return pageNo + 1;
		else
			return pageNo;
	}

	/**
	 * 是否还有上一页.
	 */
	public boolean isHasPre() {
		return (pageNo - 1 >= 1);
	}

	/**
	 * 取得上页的页号, 序号从1开始. 当前页为首页时返回首页序号.
	 */
	public int getPrePage() {
		if (isHasPre())
			return pageNo - 1;
		else
			return pageNo;
	}

	@SuppressWarnings("unchecked")
	public List<SortCond> getSortCond() {
		List<SortCond> sortCondList = (List<SortCond>) sortMap.get(SORTCOND_KEY);
		if (sortCondList == null) {
			sortCondList = new LinkedList<SortCond>();
			sortMap.put(SORTCOND_KEY, sortCondList);
		}
		return sortCondList;
	}

	@SuppressWarnings("unchecked")
	public void addSortCond(SortCond sortCond) {
		List<SortCond> sortCondList = (List<SortCond>) sortMap
				.get(SORTCOND_KEY);

		if (sortCondList == null) {
			sortCondList = new LinkedList<SortCond>();
			sortMap.put(SORTCOND_KEY, sortCondList);
		}

		sortCondList.add(sortCond);
	}

	public void addSortCond(List<SortCond> sortCondList) {
		for (SortCond sortCond : sortCondList)
			addSortCond(sortCond);
	}

}
