import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    //        public static void main(String[] args) throws Exception{
//        String command = "echo 'hello world'";
//        Process process = Runtime.getRuntime().exec(command);
//        int exitCode = process.waitFor();
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//        String line;
//        while ((line = reader.readLine()) != null) {
//            System.out.println(line);
//        }
//
//        System.out.println("\nExited with error code : " + exitCode);
//    }
    public static void main(String[] args) {
        JSch jsch = new JSch();
        Session session = jsch.getSession(user, host, port);
        session.setPassword(password);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();

        ChannelExec channel = (ChannelExec) session.openChannel("exec");
        channel.setCommand(command);
        channel.connect();

        BufferedReader reader = new BufferedReader(new InputStreamReader(channel.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        channel.disconnect();
        session.disconnect();

    }

}