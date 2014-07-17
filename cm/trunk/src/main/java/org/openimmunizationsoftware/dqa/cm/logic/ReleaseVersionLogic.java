package org.openimmunizationsoftware.dqa.cm.logic;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.openimmunizationsoftware.dqa.cm.model.ReleaseStatus;
import org.openimmunizationsoftware.dqa.cm.model.ReleaseVersion;

public class ReleaseVersionLogic
{

  public static ReleaseVersion getCurrentReleaseVersion(Session dataSession)
  {
    Query query = dataSession.createQuery("from ReleaseVersion where releaseStatusString = ? order by majorVersionNum desc, minorVersionNum desc");
    query.setParameter(0, ReleaseStatus.CURRENT.getId());
    List<ReleaseVersion> releaseVersionList = query.list();
    if (releaseVersionList.size() > 0)
    {
      return releaseVersionList.get(0);
    }

    return getProposedReleaseVersion(dataSession);
  }

  public static ReleaseVersion getProposedReleaseVersion(Session dataSession)
  {
    ReleaseVersion releaseVersion = null;
    Query query = dataSession.createQuery("from ReleaseVersion where releaseStatusString = ? order by majorVersionNum desc, minorVersionNum desc");
    query.setParameter(0, ReleaseStatus.PROPOSED.getId());
    List<ReleaseVersion> releaseVersionList = query.list();
    if (releaseVersionList.size() > 0)
    {
      releaseVersion = releaseVersionList.get(0);
    }
    return releaseVersion;
  }

  public static ReleaseVersion getReleaseVersion(int releaseVersionId, Session dataSession)
  {
    return (ReleaseVersion) dataSession.get(ReleaseVersion.class, releaseVersionId);
  }

  public static List<ReleaseVersion> getReleaseVersions(ReleaseStatus releaseStatus, Session dataSession)
  {
    Query query = dataSession.createQuery("from ReleaseVersion where releaseStatusString = ? order by majorVersionNum desc, minorVersionNum desc");
    query.setParameter(0, releaseStatus.getId());
    List<ReleaseVersion> releaseVersionList = query.list();
    return releaseVersionList;
  }

  public static List<ReleaseVersion> getReleaseVersions(Session dataSession)
  {
    Query query = dataSession.createQuery("from ReleaseVersion order by majorVersionNum desc, minorVersionNum desc");
    List<ReleaseVersion> releaseVersionList = query.list();
    return releaseVersionList;
  }

  public static void updateReleaseVersion(ReleaseVersion releaseVersion, Session dataSession)
  {
    Transaction transaction = dataSession.beginTransaction();
    dataSession.update(releaseVersion);
    transaction.commit();
  }

  public static boolean checkIfProposedVersionExists(int majorVersionNum, int minorVersionNum, Session dataSession)
  {
    Query query = dataSession.createQuery("from ReleaseVersion where majorVersionNum = ? and minorVersionNum = ?");
    query.setParameter(0, majorVersionNum);
    query.setParameter(1, minorVersionNum);
    return query.list().size() > 0;
  }
}
