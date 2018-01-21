package service;

import org.apache.shiro.subject.Subject;

/**
 * @author tmblount
 */
public interface SubjectService
{
    public Subject getLoginSubject();
}
