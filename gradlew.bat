@ECHO OFF
SET DIR=%~dp0
SET WRAPPER_JAR=%DIR%gradle\wrapper\gradle-wrapper.jar
IF NOT EXIST "%WRAPPER_JAR%" (
  ECHO gradle-wrapper.jar is missing. Open this project on GitHub Actions or Android Studio on a machine with internet, or place gradle-wrapper.jar into gradle\wrapper\ manually.
  EXIT /B 1
)
java -Dorg.gradle.appname=gradlew -classpath "%WRAPPER_JAR%" org.gradle.wrapper.GradleWrapperMain %*
