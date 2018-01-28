package service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import service.SubjectService;

/**
 * @author tmblount
 */
@Service("subjectService")
public class SubjectServiceImpl implements SubjectService
{
    @Override
    public Subject getLoginSubject()
    {
        return SecurityUtils.getSubject();
    }
}
