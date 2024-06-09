@java -Xmx512m -Xincgc -jar l1jserver.jar
@rem or you can use the following way:
@rem @java -Xmx512m -Xincgc -XX:+UseParallelGC 
-XX:MaxGCPauseMillis=100 -XX:ParallelGCThreads=2 
-XX:MaxTenuringThreshold=10 -cp l1jserver.jar l1j.server.Server

cls
ServerStart.bat