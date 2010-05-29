package filter;
import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

public class DownloadFilter implements Filter {
	private FilterConfig config;
	
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}
	public void destroy() {
		this.config = null;
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if ( request instanceof HttpServletRequest && response instanceof HttpServletResponse ) {
			HttpServletRequest httpReq = (HttpServletRequest) request;
			HttpServletResponse httpResp = (HttpServletResponse) response;
			String fileName = httpReq.getRequestURI().substring(
			httpReq.getRequestURI().lastIndexOf("/"), httpReq.getRequestURI().length() );
			if ( fileName.toLowerCase().endsWith("pdf") ) {
				httpResp.setContentType ("application/pdf");
			} else if ( fileName.toLowerCase().endsWith("xls") ) {
				httpResp.setContentType ("application/vnd.ms-excel");
			} else if ( fileName.toLowerCase().endsWith("doc") ) {
				httpResp.setContentType ("application/vnd.ms-word");
			}
			httpResp.setHeader ("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		}
		chain.doFilter(request, response);
	}
	public FilterConfig getConfig() {
		return config;
	}
}