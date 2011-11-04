package org.openimmunizationsoftware.dqa.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openimmunizationsoftware.dqa.db.model.VaccineCvx;
import org.openimmunizationsoftware.dqa.db.model.VaccineMvx;
import org.openimmunizationsoftware.dqa.db.model.VaccineProduct;

public class VaccineProductManager
{
  private static VaccineProductManager singleton = null;
  
  public static VaccineProductManager getVaccineProductManager()
  {
    if (singleton == null)
    {
      singleton = new VaccineProductManager();
    }
    return singleton;
  }
  
  private Map<String, VaccineProduct> vaccineProducts = new HashMap<String, VaccineProduct>();
  
  private VaccineProductManager()
  {
    SessionFactory factory = OrganizationManager.getSessionFactory();
    Session session = factory.openSession();

    Query query = session.createQuery("from VaccineProduct");
    List<VaccineProduct> vaccineProductsList = query.list();
    session.close();
    for (VaccineProduct vp : vaccineProductsList)
    {
      vaccineProducts.put(vp.getCvx().getCvxCode() + "-" + vp.getMvx().getMvxCode(), vp);
    }
  }
  
  public VaccineProduct getVaccineProduct(VaccineCvx cvxCode, VaccineMvx mvxCode)
  {
    return vaccineProducts.get(cvxCode.getCvxCode() + "-" + mvxCode.getMvxCode());
  }
}
