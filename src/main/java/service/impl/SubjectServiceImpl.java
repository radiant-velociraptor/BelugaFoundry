package service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import service.SubjectService;

/**
 * @author tmblount
 */
public class SubjectServiceImpl implements SubjectService
{
    @Override
    public Subject getLoginSubject()
    {
        return SecurityUtils.getSubject();
    }
}
