package org.apache.activemq.artemis.idea.artemisidearunner;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;

public class PopupDialogAction extends AnAction {

   public PopupDialogAction() {
      super();
   }
   @Override
   public void update(@NotNull AnActionEvent event) {
      // Set the availability based on whether a project is open
      Project project = event.getProject();
      event.getPresentation().setEnabledAndVisible(project != null);
   }

   @Override
   public void actionPerformed(@NotNull AnActionEvent event) {
      // Using the event, create and show a dialog
      Project currentProject = event.getProject();
      StringBuilder message =
            new StringBuilder(event.getPresentation().getText() + " Selected!");
      // If an element is selected in the editor, add info about it.
      Navigatable selectedElement = event.getData(CommonDataKeys.NAVIGATABLE);
      if (selectedElement != null) {
         message.append("\nSelected Element: ").append(selectedElement);
      }
      String title = event.getPresentation().getDescription();
      Messages.showMessageDialog(
            currentProject,
            message.toString(),
            title,
            Messages.getInformationIcon());
   }

   // Override getActionUpdateThread() when you target 2022.3 or later!

}
