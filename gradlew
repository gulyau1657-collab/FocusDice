#!/bin/sh

set -e

APP_HOME=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)
WRAPPER_JAR="$APP_HOME/gradle/wrapper/gradle-wrapper.jar"
WRAPPER_URL="https://raw.githubusercontent.com/gradle/gradle/v8.7.0/gradle/wrapper/gradle-wrapper.jar"

if [ ! -f "$WRAPPER_JAR" ]; then
  echo "gradle-wrapper.jar not found; downloading bootstrap jar..."
  mkdir -p "$APP_HOME/gradle/wrapper"
  if command -v curl >/dev/null 2>&1; then
    curl -fsSL "$WRAPPER_URL" -o "$WRAPPER_JAR"
  elif command -v wget >/dev/null 2>&1; then
    wget -q "$WRAPPER_URL" -O "$WRAPPER_JAR"
  else
    echo "Error: neither curl nor wget is available to download gradle-wrapper.jar" >&2
    exit 1
  fi
fi

exec java -Dorg.gradle.appname=gradlew -classpath "$WRAPPER_JAR" org.gradle.wrapper.GradleWrapperMain "$@"
