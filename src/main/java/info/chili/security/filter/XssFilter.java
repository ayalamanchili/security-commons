/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.chili.security.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author k26758
 */
@Component
public class XssFilter extends OncePerRequestFilter {

    private Policy policy;
    private AntiSamy as;

    public XssFilter() {
        try {
            policy = Policy.getInstance(getPolicyPath());
            as = new AntiSamy();
        } catch (PolicyException ex) {
            throw new RuntimeException("Cannot load policy:" + ex);
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        MultiReadHttpServletRequest requestWrapper = new MultiReadHttpServletRequest(httpServletRequest);
        String body = IOUtils.toString(requestWrapper.getReader());
        CleanResults cr;
        try {
            cr = as.scan(body, policy);
        } catch (ScanException | PolicyException ex) {
            throw new RuntimeException(ex);
        }
        String cleanBody = cr.getCleanHTML();
        requestWrapper.resetInputStream(cleanBody.getBytes());
        filterChain.doFilter(requestWrapper, httpServletResponse);

    }

    protected String getPolicyPath() {
        return "src/main/resources/antisamy.xml";
    }

}
