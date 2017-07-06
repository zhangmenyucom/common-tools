/**
 * 
 */
package com.taylor.common.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author haoli
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public  class PageBean  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4966751173897935557L;
	private int pageSize = 10;
	private int pageIndex;
	private String sortName; // order by column
	private String sortOrder; //ASC or DESC
	
	/**
	 * @see getPageIndex()
	 * @return
	 */
	public int getCurrentPage(){
		return getPageIndex();
	}
	
}
