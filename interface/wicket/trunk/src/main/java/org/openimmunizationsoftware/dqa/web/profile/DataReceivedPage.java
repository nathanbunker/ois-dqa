package org.openimmunizationsoftware.dqa.web.profile;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.hibernate.Query;
import org.hibernate.Session;
import org.openimmunizationsoftware.dqa.db.model.BatchActions;
import org.openimmunizationsoftware.dqa.db.model.BatchReport;
import org.openimmunizationsoftware.dqa.db.model.BatchType;
import org.openimmunizationsoftware.dqa.db.model.IssueAction;
import org.openimmunizationsoftware.dqa.db.model.MessageBatch;
import org.openimmunizationsoftware.dqa.db.model.SubmitterProfile;
import org.openimmunizationsoftware.dqa.web.DqaBasePage;
import org.openimmunizationsoftware.dqa.web.DqaSession;
import org.openimmunizationsoftware.dqa.web.NavigationPanel;

public class DataReceivedPage extends DqaBasePage 
{

  public DataReceivedPage(final PageParameters parameters) {
    super(parameters, NavigationPanel.PROFILE);
    
    DqaSession webSession = (DqaSession) getSession();
    SubmitterProfile submitterProfile = webSession.getSubmitterProfile();
    
    Session dataSession = webSession.getDataSession();
    Query query = dataSession.createQuery("from MessageBatch where profile = ? and batchType = ?");
    query.setParameter(0, submitterProfile);
    query.setParameter(1, BatchType.SUBMISSION);
    List<MessageBatch> messageBatchList = query.list();
    for (MessageBatch messageBatch : messageBatchList)
    {
      query = dataSession.createQuery("from BatchReport where messageBatch = ?");
      query.setParameter(0, messageBatch);
      List<BatchReport> reportList = query.list();
      if (reportList.size() > 0)
      {
        messageBatch.setBatchReport(reportList.get(0));
      }
      
      query = dataSession.createQuery("from BatchActions where messageBatch = ?");
      query.setParameter(0, messageBatch);
      List<BatchActions> batchActionsList = query.list();
      for (BatchActions batchActions : batchActionsList)
      {
        messageBatch.getBatchActions(batchActions.getIssueAction()).setActionCount(batchActions.getActionCount());
      }

    }
    
    ListView<MessageBatch> messageBatchItems = new ListView<MessageBatch>("messageBatchItems", messageBatchList) {
      
      @Override
      protected void populateItem(ListItem<MessageBatch> item)
      {
        final MessageBatch messageBatch = item.getModelObject();
        
        int messageCount = messageBatch.getBatchReport().getMessageCount();
        BatchActions batchActionAccept = messageBatch.getBatchActions(IssueAction.ERROR);
        BatchActions batchActionError = messageBatch.getBatchActions(IssueAction.ERROR);
        BatchActions batchActionWarn = messageBatch.getBatchActions(IssueAction.ERROR);
        BatchActions batchActionSkipped = messageBatch.getBatchActions(IssueAction.ERROR);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        item.add(new Label("date", sdf.format(messageBatch.getStartDate())));
        item.add(new Label("score", "" + messageBatch.getBatchReport().getOverallScore()));
        item.add(new Label("size", "" + messageCount));
        item.add(new Label("acceptPer", makePercent(batchActionAccept.getActionCount(), messageCount)));
        item.add(new Label("warnPer", makePercent(batchActionWarn.getActionCount(), messageCount)));
        item.add(new Label("skippedPer", makePercent(batchActionSkipped.getActionCount(), messageCount)));
        item.add(new Label("erroredPer", makePercent(batchActionError.getActionCount(), messageCount)));
        
      }
      private String makePercent(int num, int denom)
      {
        if (denom == 0)
        {
          return "-";
        }
        return ((int) (100.0 * num / denom + 0.5)) + "%";
        
      }
    };
    add(messageBatchItems);
    
  }
}
