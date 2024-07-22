package org.apache.activemq.artemis.idea.artemisidearunner;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.CommandLineState;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.process.ProcessHandlerFactory;
import com.intellij.execution.process.ProcessTerminatedListener;
import com.intellij.execution.runners.ExecutionEnvironment;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

class ArtemisCommandLineState extends CommandLineState {

   private final ArtemisRunConfiguration artemisRunConfiguration;

   public ArtemisCommandLineState(ArtemisRunConfiguration artemisRunConfiguration, @NotNull ExecutionEnvironment environment) {
      super(environment);
      this.artemisRunConfiguration = artemisRunConfiguration;
   }

   private String  getEscapedName() {
      return artemisRunConfiguration.getName().replace(" ", "-");
   }

   @NotNull
   @Override
   protected ProcessHandler startProcess() throws ExecutionException {
      ArtemisSettings.State state = ArtemisSettings.getInstance().getState();
      File artemisProfilesDir = new File(artemisRunConfiguration.getProject().getBasePath() + File.separator + ".artemis-profiles");
      if (!artemisProfilesDir.exists()) {
         artemisProfilesDir.mkdirs();
      }

      File artemisInstanceDir = new File(artemisProfilesDir, getEscapedName());
      File runFile = new File(artemisProfilesDir, getEscapedName() + ".sh");

      if (!runFile.exists()) {
         try {
            runFile.createNewFile();
            runFile.setExecutable(true);
         } catch (IOException e) {
            throw new ExecutionException(e);
         }
      }
      StringBuilder sb = new StringBuilder();

      sb.append(state.amqInstallationDir + "/bin/artemis ")
            .append("create ")
            .append("--host").append(" ")
            .append(artemisRunConfiguration.getArtemisHostName()).append(" ")
            .append(getEscapedName()).append(" ")
            .append("--user").append(" ")
            .append(artemisRunConfiguration.getArtemisUserName()).append(" ")
            .append("--password ")
            .append(artemisRunConfiguration.getArtemisPassword()).append(" ");

      if (artemisRunConfiguration.getArtemisAllowAnon()) {
         sb.append("--allow-anonymous ");
      } else {
         sb.append("--require-login ");
      }
      if(artemisRunConfiguration.getClustered()) {
         sb.append("--clustered ");
         sb.append("--cluster-user ").append(artemisRunConfiguration.getArtemisUserName()).append(" ");
         sb.append(("--cluster-password ")).append(artemisRunConfiguration.getArtemisPassword()).append(" ");
      }
      if(artemisRunConfiguration.getBackup()) {
         sb.append("--backup ");
      }
      if(artemisRunConfiguration.getPortOffset() > 0) {
         sb.append("--port-offset " + artemisRunConfiguration.getPortOffset()).append(" ");
      }
      if (artemisInstanceDir.exists() && artemisRunConfiguration.getCleanData()) {

         Path dir = Paths.get(artemisInstanceDir.getPath()); //path to the directory
         try {
            Files
                  .walk(dir) // Traverse the file tree in depth-first order
                  .sorted(Comparator.reverseOrder())
                  .forEach(path -> {
                     try {
                        Files.delete(path);  //delete ea
                     } catch (IOException e) {
                        throw new RuntimeException(e);
                     }
                  });
         } catch (IOException e) {
            throw new RuntimeException(e);
         }
      } else {
         sb.append(" --force");
      }
      sb.append(System.lineSeparator());

      sb.append("echo -e '" + artemisRunConfiguration.getBrokerProperties() + "' > " + artemisInstanceDir + "/etc/broker.properties");
      sb.append(System.lineSeparator());

      sb.append(artemisInstanceDir.getAbsolutePath() + "/bin/artemis run");

      try {
         FileWriter writer = new FileWriter(runFile);
         writer.write(sb.toString());
         writer.close();
      } catch (IOException e) {
         throw new ExecutionException(e);
      }

      GeneralCommandLine commandLine = new GeneralCommandLine(runFile.getAbsolutePath());
      commandLine.setWorkDirectory(artemisProfilesDir);
      OSProcessHandler processHandler = ProcessHandlerFactory.getInstance()
            .createColoredProcessHandler(commandLine);
      ProcessTerminatedListener.attach(processHandler);
      return processHandler;
   }
}
