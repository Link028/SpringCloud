package com.link.common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.link.common.CommonConstants;
import com.link.util.ServletUtils;

/**
 * web层通用数据处理
 */
public class BaseResource
{ 
		    /**
		     * 封装分页对象
		* @param <T>
		*/
		public static <T> PageResponse<T> getPageObject()
		{
			PageResponse<T> page = new PageResponse<T>();
			
		 page.setPageNum(ServletUtils.getParameterToInt(CommonConstants.PAGE_NUM));
		 page.setPageSize(ServletUtils.getParameterToInt(CommonConstants.PAGE_SIZE));
		 page.setSortItem(ServletUtils.getParameter(CommonConstants.SORT_COLUMN));
		 page.setSortType(ServletUtils.getParameter(CommonConstants.IS_ASC));
		 
		 return page;
		}
		
		public static <T> PageResponse<T> buildPageRequest()
		{
		 return getPageObject();
		}
	
	
//	protected  <T> PageResponse<T> setSuccessResult(PageResponse<T> result) {
//		return setSuccessResult(result, "操作成功！");
//	}
//
//	protected <T> PageResponse<T> setResult( String result, String msg , T data ) 
//	{
//		PageResponse<T> resp = new PageResponse<T>();
//		
//		resp.setData(data);
//		
//		 
//		resp.setSuccess(true);
//		resp.setMsg(msg);
// 
//		return result ;
//	}
//    
//	protected <T> JsonResult<T> setExceptionResult(JsonResult<T> result, Throwable ex, String msg) 
//	{
//		String smsg = msg + ":" + ex.getMessage();
//
//		if(smsg.length() > 500 )
//		{
//			smsg = smsg.substring(0,500 ) ;
//		}
//		 
//		result.setSuccess(false);
//		result.setMsg(smsg);
//
//		_LOG.error(smsg, ex);
//		
//		return result ;
//	}
//
//
//	public <T> Map<String, Object> toPageData(DataGrid<T> list, PageParam param)
//	{ 
//		Map<String, Object> modelMap = new HashMap<String, Object>();
//		
//		modelMap.put("draw", param.getDraw()); 
//		
//		modelMap.put("recordsTotal", list.getTotal() );
//		modelMap.put("recordsFiltered", list.getTotal()  );
//		
//		modelMap.put("data", list.getRows() );
//
//		return modelMap; 
//	}
//	
    //--------------------------------------------------------
    
	/**
	 * 获取页面传递的某一个参数值,
	 */
	public String getParam(String key) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request.getParameter(key);
	}

	/**
	 * 获取页面传递的某一个数组值
	 * 
	 * @return Class<T>
	 * @throws Exception
	 */
	public String[] getParamValues(String key) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request.getParameterValues(key);
	}
    
    /**
     * 获取request
     */
    public HttpServletRequest getRequest()
    {
        return ServletUtils.getRequest();
    }

    /**
     * 获取response
     */
    public HttpServletResponse getResponse()
    {
        return ServletUtils.getResponse();
    }

    /**
     * 获取session
     */
    public HttpSession getSession()
    {
        return getRequest().getSession();
    }

    public long getCurrentUserId()
    {
        String currentId = getRequest().getHeader(CommonConstants.CURRENT_ID);
        if (StringUtils.isNotBlank(currentId))
        {
            return Long.valueOf(currentId);
        }
        return 0l;
    }

    public String getLoginName()
    {
        return getRequest().getHeader(CommonConstants.CURRENT_UN);
    }

}
